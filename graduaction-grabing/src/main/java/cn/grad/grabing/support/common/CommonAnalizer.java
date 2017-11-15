package cn.grad.grabing.support.common;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.grad.grabing.util.Validation;

public class CommonAnalizer {

	private String userAgent = null;
	private int timeout = 0;

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	private final String defaultUserAgent = "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/56.0";

	public Document getPageByUrl(String url) throws IOException {
		if (Validation.isStringNull(url) || Validation.isStringEmpty(url))
			return null;
		return Jsoup.connect(url).userAgent(userAgent != null ? userAgent : defaultUserAgent)
				.timeout(timeout != 0 ? timeout : 3000).get();
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
