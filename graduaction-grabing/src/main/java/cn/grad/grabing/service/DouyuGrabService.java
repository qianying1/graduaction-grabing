package cn.grad.grabing.service;

import org.jsoup.Connection;

public interface DouyuGrabService {

    void beginGrabing();

    Connection initBeforeGrabing(String targetValue);

}
