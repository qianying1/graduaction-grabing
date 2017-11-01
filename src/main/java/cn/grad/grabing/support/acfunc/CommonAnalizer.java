package cn.grad.grabing.support.acfunc;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.grad.grabing.common.BaseUtil;
import cn.grad.grabing.common.Validation;
import cn.grad.grabing.entityutil.acfunc.VideoSection;

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
}
