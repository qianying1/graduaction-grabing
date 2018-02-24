package cn.grad.grabing.service;

import org.jsoup.Connection;

public interface SohuGrabService {


	void beginGrabing();

	Connection initBeforeGrabing(String targetValue);
}
