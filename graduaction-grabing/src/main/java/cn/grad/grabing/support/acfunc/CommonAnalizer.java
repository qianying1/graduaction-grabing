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
