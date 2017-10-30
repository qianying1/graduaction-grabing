package cn.grad.grabing.support.acfunc;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.grad.grabing.common.BaseUtil;
import cn.grad.grabing.common.Validation;
import cn.grad.grabing.entityutil.acfunc.BananaCrunchies;
import cn.grad.grabing.entityutil.acfunc.Carousel;
import cn.grad.grabing.entityutil.acfunc.Dancerkanojo;
import cn.grad.grabing.entityutil.acfunc.DramaSeries;
import cn.grad.grabing.entityutil.acfunc.Entertainment;
import cn.grad.grabing.entityutil.acfunc.FishesPool;
import cn.grad.grabing.entityutil.acfunc.Game;
import cn.grad.grabing.entityutil.acfunc.MonkeyRecommend;
import cn.grad.grabing.entityutil.acfunc.Music;
import cn.grad.grabing.entityutil.acfunc.ScienceAndTechno;
import cn.grad.grabing.entityutil.acfunc.SecondlyYuan;
import cn.grad.grabing.entityutil.acfunc.Sport;

public class IndexAnalizer extends BaseUtil {

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
	public List<Carousel> analizeCarouselSection(Element section) {

		return null;
	}

	/**
	 * 分析页面中猴子推荐的section节点
	 * 
	 * @param section
	 * @return
	 */
	public List<MonkeyRecommend> analizeMonkeySection(Element section) {

		return null;
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
