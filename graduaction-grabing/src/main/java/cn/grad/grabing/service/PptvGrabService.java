package cn.grad.grabing.service;

import org.jsoup.Connection;

public interface PptvGrabService {

	void beginGrabing();

	Connection initBeforeGrabing(String targetValue);

}
