package cn.grad.grabing.common;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

}
