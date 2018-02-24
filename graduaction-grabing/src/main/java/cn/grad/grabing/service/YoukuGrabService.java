package cn.grad.grabing.service;

import org.jsoup.Connection;

public interface YoukuGrabService {

    /**
     * 开始对目标地址进行数据抓取
     */
    void beginGrabing();

    Connection initBeforeGrabing(String targetValue);
}
