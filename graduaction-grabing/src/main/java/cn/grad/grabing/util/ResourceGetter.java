package cn.grad.grabing.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Resource
public class ResourceGetter extends BaseUtil {

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
		if (Validation.isStrIgnoreCaseEquals(StrPropertiesMapper.HEADER, sectionName))
			return doc;
		switch (sectionName) {
		case StrPropertiesMapper.CAROUSEL:
			sectionNum = 0;
			break;
		case StrPropertiesMapper.MONKEYRECOMMEND:
			sectionNum = 1;
			break;
		case StrPropertiesMapper.BANANAANDARTICLE:
			sectionNum = 2;
			break;
		case StrPropertiesMapper.ENTERTAINMENT:
			sectionNum = 3;
			break;
		case StrPropertiesMapper.DRAMASERIES:
			sectionNum = 4;
			break;
		case StrPropertiesMapper.GAME:
			sectionNum = 5;
			break;
		case StrPropertiesMapper.ANIMATION:
			sectionNum = 6;
			break;
		case StrPropertiesMapper.DANCERKANOJO:
			sectionNum = 7;
			break;
		case StrPropertiesMapper.FISHESPOOL:
			sectionNum = 8;
			break;
		case StrPropertiesMapper.SCIENCEANDTECHNO:
			sectionNum = 9;
			break;
		case StrPropertiesMapper.SPORT:
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

	public Document getDocumentByUrl(String configuationPath, Properties configPro) {

		return null;
	}

	public Document getDocumentByUrl(String configuationPath) {
		return getDocumentByUrl(configuationPath, new HashMap<String, Object>());
	}
}
