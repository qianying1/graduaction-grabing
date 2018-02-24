package cn.grad.grabing.support.acfunc;

import cn.grad.grabing.entity.*;
import cn.grad.grabing.util.NumberFormater;
import cn.grad.grabing.util.Validation;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页分析器
 */
@Component
public class IndexAnalizer extends CommonAnalizer {

	/**
	 * 分析页面头部获取链接列表
	 * 
	 * @param header
	 * @return
	 */
	public void analizeHeaderForUrls(Element header, Navigation navigation) {
		if (Validation.isNull(header) || Validation.isListEmpty(header.getElementsByTag("nav"))) {
			log.error("the header content is empty of acfun index page");
			return;
		}
		Element nav = header.getElementById("nav");
		Elements ahrefs = nav.select("a");
		if (Validation.isListEmpty(ahrefs)) {
			log.error("the header don`t have super link of acfun index page");
			return;
		}
		navigation.setLocation(Navigation.location.top);
		for (Element ahref : ahrefs) {
			String url = ahref.attr("abs:href");
			String name = ahref.text();
			NavNode el = new NavNode();
			if (Validation.isStrNotEmpty(url)) {
				el.setAhref(url);
			}
			if (Validation.isStrNotEmpty(name)) {
				el.setName(name);
			}
			navigation.getNavNodeList().add(el);
		}
	}
	/**
	 * 分析页面中带有轮播的section节点
	 * 
	 * @param section
	 * @return
	 */
	public void analizeCarouselSection(Element section, CarouseSection carouseSection) {
		if(Validation.isObjNull(carouseSection)){
			carouseSection=new CarouseSection();
		}
		try {
			analizeCarousel(section,carouseSection);
			analizeOthers(section,carouseSection);
		}catch (Exception e){
			e.printStackTrace();
			log.error("Error on grabbing carousel section of acfun!",e);
		}
	}

	/**
	 * 分析轮播节点六小图部分
	 *
	 * @param el
	 * @param carouseSection
	 * @return
	 */
	private void analizeOthers(Element el,CarouseSection carouseSection) {
		Elements x6s = el.getElementsByClass("slider-right-x6");
		if (Validation.isObjNotNull(x6s)&&Validation.isListNotEmpty(x6s)) {
			Element x6El = x6s.get(0);
			if (Validation.isNull(x6El)) {
				log.error("the x6 videos is null in carousel section of index page!");
				return;
			}
			Elements lis = x6El.getElementsByTag("li");
			if (Validation.isObjNull(lis) || Validation.isListEmpty(lis)) {
				log.error("the x6 videos is null in carousel section of index page!");
				return;
			}
			List<MaskVideoNode> results = new ArrayList<>();
			for (Element li : lis) {
				if (Validation.isNull(li))
					continue;
				MaskVideoNode carou = new MaskVideoNode();
				Element hrefEl=li.getElementsByTag("a").get(0);
				String link = hrefEl.attr("abs:href");
				if (!link.contains("acfun"))
					continue;
				carou.setHref(link);
				Element imgEl=li.getElementsByTag("img").get(0);
				carou.setLogo(imgEl.attr("abs:src"));
				Element maskEl=li.getElementsByClass("mask").get(0);
				Element titleEl=maskEl.getElementsByTag("b").get(0);
				Element upEl=maskEl.getElementsByTag("span").get(0);
				Element viewEl=maskEl.getElementsByTag("span").get(1);
				Element viewsEl=viewEl.getElementsByTag("i").get(0);
				Element masksEl=viewEl.getElementsByTag("i").get(1);
				carou.setTitle(titleEl.text());
				carou.setUpMan(upEl.text());
				String views=viewsEl.text();
				if(views.contains("万")){
					views=views.replace("万","000");
					views=views.replace(".","");
				}
				carou.setViews(Long.valueOf(NumberFormater.pareseNumberStr(views)));
				carou.setMasks(Long.valueOf(NumberFormater.pareseNumberStr(masksEl.text())));
				carou.setLocation("carouselSection of acfun");
				results.add(carou);
			}
			carouseSection.getMaskVideoNodes().addAll(results);
		}
	}

	/**
	 * 分析轮播节点轮播图部分
	 * @param el
	 * @param carouseSection
	 */
	protected void analizeCarousel(Element el,CarouseSection carouseSection) throws Exception{
		Elements carousels = el.getElementsByClass("slider-wrap");
		if (Validation.isObjNotNull(carousels)&&Validation.isListNotEmpty(carousels)) {
			Element carousel = carousels.get(0);
			if (Validation.isNull(carousel)) {
				log.error("the carousel is empty in carousel section of index page");
				return;
			}
			Elements lis = carousel.getElementsByTag("li");
			if (Validation.isObjNull(lis) || Validation.isListEmpty(lis)) {
				log.error("the carousel in carousel section of index page don`t have conent");
				return;
			}
			List<MaskVideoNode> results = new ArrayList<>();
			for (Element li : lis) {
				if (Validation.isNull(li))
					continue;
				MaskVideoNode carou = new MaskVideoNode();
				Element aEl=li.getElementsByTag("a").get(0);
				String link = aEl.attr("abs:href");
				if (!link.contains("acfun"))
					continue;
				carou.setHref(link);
				Element imgEl=li.getElementsByTag("img").get(0);
				carou.setLogo(imgEl.attr("abs:src"));
				Element titleEl=li.getElementsByClass("slider-title").get(0);
				carou.setTitle(titleEl.getElementsByTag("b").first().text());
				carou.setLocation("carouselSection of acfun");
				results.add(carou);
			}
			carouseSection.getMaskVideoNodes().addAll(results);
		}else{
			log.error("fail to grabbing data from carousel section of acfun!");
			return;
		}
	}

	/**
	 * 分析页面中猴子推荐的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeMonkeySection(Element section) {
		List<VideoSection> videos = new ArrayList<>();
		if (Validation.isNull(section)) {
			log.debug("the monkeyRecommend don`t have any contents");
			return null;
		}
		Elements div = section.getElementsByClass("area-main");
		if (Validation.isEmpty(div)) {
			log.debug("the monkeyRecommend don`t have any contents");
			return null;
		}
		Element contents = div.get(0);
		if (Validation.isNull(contents)) {
			log.debug("the monkeyRecommend don`t have any contents");
			return null;
		}
		Elements links = contents.getElementsByTag("a");
		if (Validation.isEmpty(links)) {
			log.debug("the monkeyRecommend don`t have any contents");
			return null;
		}
		List<VideoSection> results = analizeASection(links);
		if (!Validation.isObjNull(results) && !Validation.isListEmpty(results))
			videos.addAll(results);
		return videos;
	}

	*//**
	 * 分析页面中香蕉榜中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeBananaSection(Element section) {
		Elements leftContents = section.getElementsByClass("column-left");
		Elements rightContents = section.getElementsByClass("column-right");
		List<VideoSection> leftResult = analizeColumnContents(leftContents, "left contents", "banana");
		List<VideoSection> rightResult = analizeColumnContents(rightContents, "right contents", "banana");
		List<VideoSection> results = new ArrayList<>();
		if (leftResult != null)
			results.addAll(leftResult);
		if (rightContents != null)
			results.addAll(rightResult);
		return results;
	}

	private List<VideoSection> analizeColumnContents(Elements els, String contentFlag, String sectionFalg) {
		if (Validation.isObjNull(els) || Validation.isEmpty(els)) {
			log.debug("the " + contentFlag + " of " + sectionFalg + " section don`t have contents");
			return null;
		}
		Element el = els.get(0);
		return analizeASection(el.getElementsByTag("a"));
	}

	*//**
	 * 分析页面中娱乐版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeEntertainmentSection(Element section) {
		List<VideoSection> results = new ArrayList<>();
		results = analizeASection(section.getElementsByTag("a"));
		if (Validation.isListEmpty(results)) {
			log.debug("the entertainment section don`t have any contents");
		}
		return results;
	}

	*//**
	 * 分析页面中番剧版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeDramaSeriesSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中游戏版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeGameSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中动画版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeAnimationSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中二次元版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeSecondYuanSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中音乐版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeMusicSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中舞蹈@彼女版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeDancerKanojoSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中科技版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeScienceAndTechnoSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中鱼塘版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeFishesPoolSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中体育版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeSportSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * 分析页面中临时添加的版块中的section节点
	 * 
	 * @param section
	 * @return
	 *//*
	public List<VideoSection> analizeTempSection(Element section) {
		return commondHandler(section);
	}

	*//**
	 * section节点的共同处理器
	 *//*
	private List<VideoSection> commondHandler(Element section) {
		List<VideoSection> results = new ArrayList<>();
		results = analizeASection(section.getElementsByTag("a"));
		if (Validation.isListEmpty(results)) {
			log.debug("the entertainment section don`t have any contents");
		}
		return results;
	}

	*//**
	 * 分析导航栏上的节点获取链接地址列表
	 * 
	 * @param nav
	 * @return
	 *//*
	public List<String> analizeNavForUrls(Element nav) {

		return null;
	}

	public List<VideoSection> ananlizePageForLinks(Element page) {
		List<VideoSection> results = new ArrayList<>();
		results = analizeASection(page.getElementsByTag("a"));
		if (Validation.isListEmpty(results)) {
			log.debug("don`t have any link of acfun index page");
			return null;
		}
		return results;
	}

	public void testing() {
		System.out.println("indexAnalizer testing......");
	}*/

}
