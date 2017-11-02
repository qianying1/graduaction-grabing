package cn.grad.grabing.support.acfunc;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import cn.grad.grabing.common.Validation;
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
		for (Element link : links) {
			if (Validation.isObjNull(link) || Validation.isNotContainsStr(link, "acfun"))
				continue;
			VideoSection video = new VideoSection();
			Element img = !Validation.isEmpty(link.getElementsByTag("img")) ? link.getElementsByTag("img").get(0)
					: null;
			if (!Validation.isNull(img))
				video.setImage(img.attr("abs:src"));
			video.setLink(link.attr("abs:href"));
			videos.add(video);
		}
		return videos;
	}

	/**
	 * 分析页面中香蕉榜中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<BananaCrunchies> analizeBananaSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中娱乐版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<Entertainment> analizeEntertainmentSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中番剧版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<DramaSeries> analizeDramaSeriesSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中游戏版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<Game> analizeGameSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中二次元版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<SecondlyYuan> analizeSecondlyYuanSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中音乐版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<Music> analizeMusicSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中舞蹈@彼女版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<Dancerkanojo> analizeDancerKanojoSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中科技版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<ScienceAndTechno> analizeScienceAndTechnoSection(Element section) {
		return null;
	}

	/**
	 * 分析页面中鱼塘版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<FishesPool> analizeFishesPoolSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中体育版块中的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<Sport> analizeSportSection(Element section) {

		return null;
	}

	/**
	 * section节点的共同处理器
	 */
	private void commondHandler() {

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

}
