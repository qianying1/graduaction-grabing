package cn.grad.grabing.helper;

import cn.grad.grabing.util.BaseLogger;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.Validation;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * 网页文档爬取工具
 */
@Component
public class DocumentInitailizer extends BaseLogger {

    /**
     * 网络http文档连接器
     */
    private Connection conn;

    /**
     * 连接超时时间
     */
    private int timeout;

    /**
     * 用户代理器
     */
    private String userAgent;

    /**
     * 浏览器cookie key
     */
    private String cookieKey;

    /**
     * 浏览器cookie value
     */
    private String cookieValue;

    /**
     * 需要传递数据的key
     */
    private String dataKey;

    /**
     * 需要传递数据的value
     */
    private String dataValue;

    /**
     * 是否忽略数据类型
     */
    private Boolean ignoreContentType;

    /**
     * 是否接受重定向
     */
    private Boolean followRedirects;

    /**
     * 是否忽略http请求错误
     */
    private Boolean ignoreHttpErrors;

    /**
     * 文档内容容量大小
     */
    private Integer maxBodySize;

    /**
     * 访问者
     */
    private String referrer;

    /**
     * 是否允许javascript脚本执行
     */
    private Boolean javaScriptEnabled;
    /**
     * 是否允许css样式
     */
    private Boolean cssEnabled;
    /**
     * 是否在遇到返回失败响应码时抛出异常
     */
    private Boolean throwExceptionOnFailingStatusCode;
    /**
     * 是否在遇到脚本运行错误时抛出异常
     */
    private Boolean throwExceptionOnScriptError;

    /**
     * 通过url地址获取网页文档
     *
     * @param url
     * @return
     */
    /*public Document getDocumentByUrl(String url) {
        if (Validation.isStringNull(url) || Validation.isStringEmpty(url))
            return null;
        Document doc = null;
        try {
            Connection connection = Jsoup.connect(url);
            analizeConfigurationAndUseInConn(connection);
            doc = connection.get();
            if (doc == null)
                doc = connection.post();
        } catch (IOException e) {
            log.error("Maybe network is too bad to connect the target or the connection is broken by some factors", e);
        }
        return doc;
    }*/

    /**
     * 通过连接器获取网络页面文档
     *
     * @param conn
     * @return
     */
    public Document getDocumentByConn(Connection conn) {
        if (Validation.isObjNull(conn) && Validation.isObjNull(this.conn))
            return null;
        Document doc = null;
        int count = 0;
        boolean ableGrabbed = false;
        while (count <= 8 && !ableGrabbed) {
            try {
                doc = conn.get();
                if (Validation.isObjNull(doc))
                    doc = conn.post();
                ableGrabbed = true;
            } catch (IOException e) {
                count++;
                conn.timeout(timeout);
                log.error("catch an error when getting html document from connection", e);
            }
        }
        return doc;
    }

    /**
     * 通过url地址获取网站页面文档
     *
     * @param configuationPath
     * @return
     */
    /*public Document getDocumentByUrl(String configuationPath) {
        return getDocumentByUrl(configuationPath, new HashMap<String, Object>());
    }*/

    /**
     * 向url地址初始化网络连接器
     *
     * @param url
     * @return
     */
    public Connection initConnectionByUrl(String url) {
        if (Validation.isStringEmpty(url))
            return null;
        Connection conn = Jsoup.connect(url);
        if (Validation.isObjNull(conn))
            return null;
        return conn;
    }

    /**
     * 单纯使用url地址初始化连接器
     *
     * @param url
     * @return
     */
    public Connection initConnectionByUrlOnly(String url) {
        if (Validation.isStringEmpty(url))
            return null;
        Connection conn = Jsoup.connect(url);
        if (Validation.isObjNull(conn))
            return null;
        return conn;
    }

    public static void initWebClientOptions(WebClientOptions options){

    }

    public static void initWebRequestUrl(URL url){

    }

    @Value("#{systemConfigs['webClient.options.timeout']}")
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Value("#{systemConfigs['webRequest.header.User-Agent']}")
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCookieKey() {
        return cookieKey;
    }

    public void setCookieKey(String cookieKey) {
        this.cookieKey = cookieKey;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    //    @Value("#{systemConfigs['']}"})
    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public Boolean getIgnoreContentType() {
        return ignoreContentType;
    }

    public void setIgnoreContentType(Boolean ignoreContentType) {
        this.ignoreContentType = ignoreContentType;
    }

    public Boolean getFollowRedirects() {
        return followRedirects;
    }

    public void setFollowRedirects(Boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    public Boolean getIgnoreHttpErrors() {
        return ignoreHttpErrors;
    }

    public void setIgnoreHttpErrors(Boolean ignoreHttpErrors) {
        this.ignoreHttpErrors = ignoreHttpErrors;
    }

    public Integer getMaxBodySize() {
        return maxBodySize;
    }

    public void setMaxBodySize(Integer maxBodySize) {
        this.maxBodySize = maxBodySize;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    /**
     * 获取是否允许Javascript脚本执行
     *
     * @return
     */
    public Boolean getJavaScriptEnabled() {
        return javaScriptEnabled;
    }

    /**
     * 设置是否允许javascript脚本执行
     *
     * @param javaScriptEnabled
     */
    @Value("#{acfunConfigs['acfun.webClient.options.javaScriptEnabled']}")
    public void setJavaScriptEnabled(Boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }

    /**
     * 获取是否允许css样式
     *
     * @return
     */
    public Boolean getCssEnabled() {
        return cssEnabled;
    }

    /**
     * 设置是否允许css样式
     *
     * @param cssEnabled
     */
    @Value("#{acfunConfigs['acfun.webClient.options.cssEnabled']}")
    public void setCssEnabled(Boolean cssEnabled) {
        this.cssEnabled = cssEnabled;
    }

    /**
     * 获取是否遇到失败状态码时抛出异常
     *
     * @return
     */
    public Boolean getThrowExceptionOnFailingStatusCode() {
        return throwExceptionOnFailingStatusCode;
    }

    /**
     * 设置是否遇到失败状态码时抛出异常
     *
     * @param throwExceptionOnFailingStatusCode
     */
    @Value("#{acfunConfigs['acfun.webClient.options.throwExceptionOnFailingStatusCode']}")
    public void setThrowExceptionOnFailingStatusCode(Boolean throwExceptionOnFailingStatusCode) {
        this.throwExceptionOnFailingStatusCode = throwExceptionOnFailingStatusCode;
    }

    public Boolean getThrowExceptionOnScriptError() {
        return throwExceptionOnScriptError;
    }

    public void setThrowExceptionOnScriptError(Boolean throwExceptionOnScriptError) {
        this.throwExceptionOnScriptError = throwExceptionOnScriptError;
    }

    /**
     * 获取连接器
     *
     * @return
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * 设置连接器
     *
     * @param conn
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
