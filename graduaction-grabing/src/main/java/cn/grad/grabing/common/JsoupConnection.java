package cn.grad.grabing.common;

import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.springframework.stereotype.Component;

/**
 * jsoup连接器
 */
@Component("jsoupConnection")
public class JsoupConnection {

    private HttpConnection httpConnection;

    public JsoupConnection(HttpConnection connection) {
        this.httpConnection = connection;
    }

    public JsoupConnection(String url) {
        this.httpConnection = (HttpConnection) Jsoup.connect(url);
    }
}
