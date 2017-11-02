package cn.grad.grabing.common;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.javascript.host.Map;

public class ResourceGetter extends BaseUtil {

	public Document getDocumentByUrl(String url, Map params) {
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

	private Connection addConnectionParams(Connection connection, Map params) {
		if (!Validation.isObjNull(params.get("userAgent")))
			connection.userAgent((String) params.get("userAgent"));
		
		if (!Validation.isObjNull(params.get("timeout")) && params.get("timeout") instanceof Integer)
			connection.timeout((int) params.get("timeout"));
		
		if (!Validation.isObjNull(params.get("contentType"))
				&& !Validation.isStringEmpty((String) params.get("contentType")))
			connection.header("Content-Type", (String) params.get("contentType"));
		
		
		return connection;
	}
}
