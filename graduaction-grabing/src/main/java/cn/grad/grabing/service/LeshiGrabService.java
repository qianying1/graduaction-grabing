package cn.grad.grabing.service;

import org.jsoup.Connection;

public interface LeshiGrabService {

	void beginGrabing();

	Connection initBeforeGrabing(String targetValue);

}
