package cn.grad.grabing.support.acfunc;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import cn.grad.grabing.entityutil.acfunc.BananaCrunchies;
import cn.grad.grabing.entityutil.acfunc.Dancerkanojo;
import cn.grad.grabing.entityutil.acfunc.DramaSeries;
import cn.grad.grabing.entityutil.acfunc.Entertainment;
import cn.grad.grabing.entityutil.acfunc.FishesPool;
import cn.grad.grabing.entityutil.acfunc.Game;
import cn.grad.grabing.entityutil.acfunc.Music;
import cn.grad.grabing.entityutil.acfunc.ScienceAndTechno;
import cn.grad.grabing.entityutil.acfunc.SecondlyYuan;
import cn.grad.grabing.entityutil.acfunc.Sport;
import cn.grad.grabing.entityutil.acfunc.VideoSection;
import cn.grad.grabing.util.Validation;

@Component
public class IndexAnalizer extends CommonAnalizer {

	/**
	 * 分析页面头部获取链接列表
	 * 
	 * @param header
	 * @return
	 */
	public List<String> analizeHeaderForUrls(Element header) {
		if (Validation.isNull(header) || Validation.isEmpty(header.getElementsByTag("nav"))) {
			log.error("the header content is empty");
			return null;
		}
		Element nav = header.getElementById("nav");
		Elements ahrefs = nav.select("a");
		if (Validation.isEmpty(ahrefs)) {
			log.error("the header don`t have super link");
			return null;
		}
		List<String> urls = new ArrayList<String>();
		for (Element ahref : ahrefs) {
			String url = ahref.attr("abs:href");
			if (null != url && !"".equals(url)) {
				urls.add(url);
			}
		}
		return urls;
	}

	/**
	 * 分析页面中带有轮播的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeCarouselSection(Element section) {
		List<VideoSection> result = new ArrayList<>();
		result.addAll(analizeCarouInCarouselSection(section));
		result.addAll(analizeOthersInCarouselSection(section));
		return result;
	}

	// 分析除轮播之外的其他各小视频
	private List<VideoSection> analizeOthersInCarouselSection(Element el) {
		Elements carousels = el.getElementsByClass("slider-right-x6");
		return analizeVideoLi(carousels);
	}

	// 分析轮播模块
	private List<VideoSection> analizeCarouInCarouselSection(Element el) {
		Elements carousels = el.getElementsByClass("slider-wrap");
		return analizeVideoLi(carousels);
	}

	/**
	 * 分析页面中猴子推荐的section节点
	 * 
	 * @param section
	 * @return
	 */
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

	/**
	 * 分析页面中香蕉榜中的section节点
	 * 
	 * @param section
	 * @return
	 */
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

	/**
	 * 分析页面中娱乐版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeEntertainmentSection(Element section) {
		List<VideoSection> results = new ArrayList<>();
		results = analizeASection(section.getElementsByTag("a"));
		if (Validation.isListEmpty(results)) {
			log.debug("the entertainment section don`t have any contents");
		}
		return results;
	}

	/**
	 * 分析页面中番剧版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeDramaSeriesSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中游戏版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeGameSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中动画版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeAnimationSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中二次元版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeSecondYuanSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中音乐版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeMusicSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中舞蹈@彼女版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeDancerKanojoSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中科技版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeScienceAndTechnoSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中鱼塘版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeFishesPoolSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中体育版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeSportSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * 分析页面中临时添加的版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<VideoSection> analizeTempSection(Element section) {
		return commondHandler(section);
	}

	/**
	 * section节点的共同处理器
	 */
	private List<VideoSection> commondHandler(Element section) {
		List<VideoSection> results = new ArrayList<>();
		results = analizeASection(section.getElementsByTag("a"));
		if (Validation.isListEmpty(results)) {
			log.debug("the entertainment section don`t have any contents");
		}
		return results;
	}

	/**
	 * 分析导航栏上的节点获取链接地址列表
	 * 
	 * @param nav
	 * @return
	 */
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

}
