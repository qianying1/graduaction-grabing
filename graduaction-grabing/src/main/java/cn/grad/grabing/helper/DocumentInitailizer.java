package cn.grad.grabing.helper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.Validation;

@Resource
public class DocumentInitailizer extends BaseUtil {

	private Connection conn;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

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

	

	public Document getDocumentByConn(Connection conn) {
		if (Validation.isObjNull(conn) && Validation.isObjNull(this.conn))
			return null;
		Document doc = null;
		try {
			doc = conn.get();
			if (Validation.isObjNull(doc))
				doc = conn.post();
		} catch (IOException e) {
			log.error("catch an error when getting html document from connection", e);
		}
		return doc;
	}

	public Document getDocumentByUrl(String configuationPath) {
		return getDocumentByUrl(configuationPath, new HashMap<String, Object>());
	}

	public Connection initConnectionByUrl(String url, Properties configPro) {
		if (Validation.isStringEmpty(url) || Validation.isObjNull(configPro) || configPro.isEmpty())
			return null;
		Connection conn = Jsoup.connect(url);
		if (Validation.isObjNull(conn))
			return null;
		Enumeration<Object> params = configPro.keys();
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			if (paramName.contains("header")) {
				conn.header(paramName.replace("header.", ""), configPro.getProperty(paramName));
				continue;
			}
			conn = analizeConfigurationAndUseInConn(conn, paramName, configPro.getProperty(paramName));
		}
		return conn;
	}

	private Connection analizeConfigurationAndUseInConn(Connection conn, String paramName, String paramValue) {
		if (paramName.contains("userAgent"))
			conn.userAgent(paramValue);
		else if (paramName.contains("timeout"))
			conn.timeout(Integer.valueOf(paramValue));
		else if (paramName.contains("cookie"))
			conn.cookie(paramName, paramValue);
		else if (paramName.contains("data"))
			conn.data(paramName, paramValue);
		else if (paramName.contains("ignoreContentType"))
			conn.ignoreContentType(Boolean.valueOf(paramValue));
		else if (paramName.contains("followRedirects"))
			conn.followRedirects(Boolean.valueOf(paramValue));
		else if (paramName.contains("ignoreHttpErrors"))
			conn.ignoreHttpErrors(Boolean.valueOf(paramValue));
		else if (paramName.contains("maxBodySize"))
			conn.maxBodySize(Integer.valueOf(paramValue));
		else if (paramName.contains("referrer"))
			conn.referrer(paramValue);
		return conn;
	}

	public Connection initConnectionByUrlOnly(String url) {
		if (Validation.isStringEmpty(url))
			return null;
		Connection conn = Jsoup.connect(url);
		if (Validation.isObjNull(conn))
			return null;
		return conn;
	}
}
