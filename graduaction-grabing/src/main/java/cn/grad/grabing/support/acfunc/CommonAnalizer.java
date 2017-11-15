package cn.grad.grabing.support.acfunc;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.grad.grabing.entityutil.acfunc.VideoSection;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.Validation;

public class CommonAnalizer extends BaseUtil {

	/**
	 * 分析带有视频节点的li
	 * 
	 * @param els
	 * @return
	 */
	public List<VideoSection> analizeVideoLi(Elements els) {
		List<VideoSection> results = new ArrayList<>();
		if (!Validation.isEmpty(els)) {
			Element carousel = els.get(0);
			if (Validation.isNull(carousel)) {
				log.warn("the carousel is empty of index page");
				return null;
			}
			Elements lis = carousel.getElementsByTag("li");
			if (Validation.isObjNull(lis) || Validation.isEmpty(lis)) {
				log.warn("the carousel don`t have conent");
				return null;
			}
			for (Element li : lis) {
				if (Validation.isNull(li))
					continue;
				VideoSection carou = new VideoSection();
				String link = li.getElementsByTag("a").get(0).attr("abs:href");
				if (!link.contains("acfun"))
					continue;
				carou.setLink(link);
				carou.setImage(li.getElementsByTag("img").get(0).attr("abs:src"));
				results.add(carou);
			}
			return results;
		}
		return null;
	}

	/**
	 * 分析a标签节点中的视频信息
	 * 
	 * @param aLinks
	 * @return
	 */
	public List<VideoSection> analizeASection(Elements aLinks) {
		List<VideoSection> results = new ArrayList<>();
		if (Validation.isEmpty(aLinks))
			return null;
		for (Element el : aLinks) {
			if (Validation.isNotContainsStr(el, "acfun"))
				continue;
			VideoSection video = new VideoSection();
			Elements imgs = el.getElementsByTag("img");
			Element img = null;
			if (!Validation.isEmpty(imgs))
				img = imgs.get(0);
			if (!Validation.isNull(img))
				video.setImage(img.attr("abs:src"));
			video.setLink(el.attr("abs:href"));
			results.add(video);
		}
		return results;
	}

	/**
	 * 分析a标签节点中的视频信息
	 * 
	 * @param aLinks
	 * @return
	 */
	public List<VideoSection> analizeASection(Element... aLinks) {
		List<VideoSection> results = new ArrayList<>();
		if (Validation.isEmpty(aLinks))
			return null;
		for (Element el : aLinks) {
			if (Validation.isNotContainsStr(el, "acfun"))
				continue;
			VideoSection video = new VideoSection();
			Element img = el.getElementsByTag("img").get(0);
			if (!Validation.isNull(img))
				video.setImage(img.attr("abs:src"));
			video.setLink(el.attr("abs:href"));
			results.add(video);
		}
		return results;
	}
}
