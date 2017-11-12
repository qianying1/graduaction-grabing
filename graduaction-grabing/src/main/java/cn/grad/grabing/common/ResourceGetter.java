package cn.grad.grabing.common;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Resource
public class ResourceGetter extends BaseUtil {

	public static final String CAROUSEL = "carousel";
	public static final String MONKEYRECOMMEND = "monkeyrecommend";
	public static final String BANANAANDARTICLE = "bananaandarticle";
	public static final String ENTERTAINMENT = "entertainment";
	public static final String DRAMASERIES = "dramaseries";
	public static final String GAME = "game";
	public static final String ANIMATION = "animation";
	public static final String MUSIC = "music";
	public static final String DANCERKANOJO = "dancerkanojo";
	public static final String FISHESPOOL = "fishespool";
	public static final String SCIENCEANDTECHNO = "scienceandtechno";
	public static final String SPORT = "sport";
	public static final String TEMPLATE = "template";
	public static final String HEADER = "header";

	public Document getDocumentByUrl(String url, Map<String, Object> params) {
		if (Validation.isStringNull(url) || Validation.isStringEmpty(url))
			return null;
		Document doc = null;
		try {
			Connection connection = Jsoup.connect(url);
			connection = addConnectionParams(connection, params);
			doc = connection.get();
			if (doc == null)
				doc = connection.post();
		} catch (IOException e) {
			log.error("Maybe network is too bad to connect the target or the connection is broken by some factors", e);
		}
		return doc;
	}

	private Connection addConnectionParams(Connection connection, Map<String, Object> params) {
		if (Validation.isObjNull(params) || params.isEmpty())
			return connection;
		if (!Validation.isObjNull(params.get("userAgent")))
			connection.userAgent((String) params.get("userAgent"));

		if (!Validation.isObjNull(params.get("timeout")) && params.get("timeout") instanceof Integer)
			connection.timeout((int) params.get("timeout"));

		if (!Validation.isObjNull(params.get("contentType"))
				&& !Validation.isStringEmpty((String) params.get("contentType")))
			connection.header("Content-Type", (String) params.get("contentType"));

		return connection;
	}

	public Element getDocumentSectionByName(String sectionName, String url, Map<String, Object> params) {
		int sectionNum = -1;
		Document doc = getDocumentByUrl(url, params);
		if (Validation.isNull(doc)) {
			log.error("can`t getting a page from uri: " + url);
			return null;
		}
		if (Validation.isStrIgnoreCaseEquals(ResourceGetter.HEADER, sectionName))
			return doc;
		switch (sectionName) {
		case ResourceGetter.CAROUSEL:
			sectionNum = 0;
			break;
		case ResourceGetter.MONKEYRECOMMEND:
			sectionNum = 1;
			break;
		case ResourceGetter.BANANAANDARTICLE:
			sectionNum = 2;
			break;
		case ResourceGetter.ENTERTAINMENT:
			sectionNum = 3;
			break;
		case ResourceGetter.DRAMASERIES:
			sectionNum = 4;
			break;
		case ResourceGetter.GAME:
			sectionNum = 5;
			break;
		case ResourceGetter.ANIMATION:
			sectionNum = 6;
			break;
		case ResourceGetter.DANCERKANOJO:
			sectionNum = 7;
			break;
		case ResourceGetter.FISHESPOOL:
			sectionNum = 8;
			break;
		case ResourceGetter.SCIENCEANDTECHNO:
			sectionNum = 9;
			break;
		case ResourceGetter.SPORT:
			sectionNum = 10;
			break;
		default:
			break;
		}
		Element result = doc.getElementById("main").getElementsByTag("section").get(sectionNum);
		if (Validation.isNull(result)) {
			log.error("can not to get " + sectionName + " section");
			return null;
		}
		return result;
	}
}
