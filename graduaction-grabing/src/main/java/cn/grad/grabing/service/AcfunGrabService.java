package cn.grad.grabing.service;

import org.jsoup.Connection;

public interface AcfunGrabService{


	void beginGrabing();

	Connection initBeforeGrabing(String targetValue);

	void initJsoupDocumentConnection(Connection connection);

	void initHtmlUnitWebClient();

	void initHtmlUnitWebRequest();
}
