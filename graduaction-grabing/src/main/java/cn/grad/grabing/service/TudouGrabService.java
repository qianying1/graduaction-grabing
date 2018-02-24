package cn.grad.grabing.service;

import org.jsoup.Connection;

public interface TudouGrabService {

	void beginGrabing();

	Connection initBeforeGrabing(String targetValue);
}
