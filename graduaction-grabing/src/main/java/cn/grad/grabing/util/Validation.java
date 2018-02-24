package cn.grad.grabing.util;

import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Validation<T> {

	public static boolean isNull(Object obj) {
		return obj != null ? false : true;
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

	public static <T> boolean isListEmpty(List<T> datas) {
		return datas.size() <= 0 ? true : false;
	}

	public static boolean isStrIgnoreCaseEquals(String str1, String str2) {
		return str1.trim().equalsIgnoreCase(str2.trim());
	}

	public static String enValueConfigPath(String webSitesPath) {
		return webSitesPath.contains("classpath:") ? webSitesPath.trim().replace("classpath:", "/")
				: webSitesPath.trim();
	}

	public static boolean isPropertiesEmpty(Set<Object> keySet) {
		return keySet.isEmpty();
	}

	public static boolean isStrNotEmpty(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static boolean isObjNotNull(Object obj) {
		return null != obj;
	}

	public static boolean isListNotEmpty(List list){
		return list!=null&&list.size()>0;
	}

}
