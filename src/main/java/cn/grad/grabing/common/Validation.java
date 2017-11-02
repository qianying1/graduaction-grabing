package cn.grad.grabing.common;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.grad.grabing.entityutil.acfunc.VideoSection;

public class Validation {

	public static boolean isNull(Element el) {
		return el != null ? false : true;
	}

	public static boolean isEmpty(Elements elements) {
		return elements.size() <= 0 ? true : false;
	}

	public static boolean isStringEmpty(String str) {
		return str.trim().equals("") ? true : false;
	}

	public static boolean isStringNull(String str) {
		return str == null ? true : false;
	}

	public static boolean isObjNull(Object obj) {
		return obj == null ? true : false;
	}

	public static boolean isNotContainsStr(Element el, String str) {
		if (isNull(el))
			return true;
		if (isStringNull(str) || isStringEmpty(str))
			return true;
		return !el.attr("abs:href").contains(str);
	}

	public static boolean isEmpty(Element[] els) {
		return els.length <= 0 ? true : false;
	}

	public static boolean isListEmpty(List<VideoSection> datas) {
		return datas.size() <= 0 ? true : false;
	}

}
