package cn.grad.grabing.service;

import org.jsoup.Connection;

public interface IqiyiGrabService {

	void beginGrabing();

	Connection initBeforeGrabing(String targetValue);

}
