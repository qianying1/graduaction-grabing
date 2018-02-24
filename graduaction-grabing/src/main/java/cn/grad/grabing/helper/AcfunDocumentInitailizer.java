package cn.grad.grabing.helper;

import cn.grad.grabing.util.BaseLogger;
import cn.grad.grabing.util.Validation;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.apache.commons.lang.BooleanUtils;
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
public class AcfunDocumentInitailizer extends BaseLogger {

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
     * 代理端口
     */
    private Integer proxyPort;

    /**
     * 目标链接地址
     */
    private String url;

    /**
     * http请求字符编码
     */
    private String charset;

    /**
     * 代理地址
     */
    private String proxyHost;

    /**
     * 是否允许cookie传输
     */
    private Boolean cookieEnable;

    /**
     * 使用初始化参数进行初始化连接器
     *
     * @param conn
     */
    public void analizeConfigurationAndUseInConn(Connection conn) {
        if (StringUtils.isNotBlank(getUserAgent())) {
            conn.userAgent(getUserAgent());
        }
        if (!ObjectUtils.equals(getTimeout(), null)) {
            conn.timeout(getTimeout());
        }
        if (StringUtils.isNotBlank(getCookieKey()) && StringUtils.isNotBlank(getCookieValue())) {
            conn.cookie(getCookieKey(), getCookieValue());
        }
        if (StringUtils.isNotBlank(getDataKey()) && StringUtils.isNotBlank(getDataValue())) {
            conn.data(getDataKey(), getDataValue());
        }
        if (!ObjectUtils.equals(getIgnoreContentType(), null)) {
            conn.ignoreContentType(getIgnoreContentType());
        }
        if (!ObjectUtils.equals(getFollowRedirects(), null)) {
            conn.followRedirects(getFollowRedirects());
        }
        if (!ObjectUtils.equals(getIgnoreHttpErrors(), null)) {
            conn.ignoreHttpErrors(getIgnoreHttpErrors());
        }
        if (!ObjectUtils.equals(getMaxBodySize(), null) && getMaxBodySize().compareTo(0) > 0) {
            conn.maxBodySize(getMaxBodySize());
        }
        if (StringUtils.isNotBlank(getReferrer())) {
            conn.referrer(getReferrer());
        }
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

    public void initWebClient(WebClient webClient) {
        if (!ObjectUtils.equals(getTimeout(), null)) {
            webClient.getOptions().setTimeout(getTimeout());
        }
        if (StringUtils.isNotBlank(getUrl()) && StringUtils.isNotBlank(getCookieKey()) && StringUtils.isNotBlank(getCookieValue())) {
            webClient.getCookieManager().addCookie(new Cookie(getUrl(), getCookieKey(), getCookieValue()));
        }
        if(BooleanUtils.isTrue(getCookieEnable())){
            webClient.getCookieManager().setCookiesEnabled(getCookieEnable());
        }
        if(BooleanUtils.isTrue(getCssEnabled())){
            webClient.getOptions().setCssEnabled(getCssEnabled());
        }
        if(BooleanUtils.isTrue(getFollowRedirects())){
            webClient.getOptions().setRedirectEnabled(getFollowRedirects());
        }
        if(BooleanUtils.isTrue(getThrowExceptionOnFailingStatusCode())){
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(getThrowExceptionOnFailingStatusCode());
        }
        if(BooleanUtils.isTrue(getJavaScriptEnabled())){
            webClient.getOptions().setJavaScriptEnabled(getJavaScriptEnabled());
        }
    }

    public void initWebRequest(WebRequest request) throws Exception {
        if (!ObjectUtils.equals(getUserAgent(), null)) {
            request.getAdditionalHeaders().put("User-Agent", getUserAgent());
        }
        if (!ObjectUtils.equals(getReferrer(), null)) {
            request.getAdditionalHeaders().put("Referer", getReferrer());
        }
        if (!ObjectUtils.equals(getCharset(), null)) {
            request.setCharset(getCharset());
        }
        if (!ObjectUtils.equals(getProxyHost(), null)) {
            request.setProxyHost(getProxyHost());
        }
        if (!ObjectUtils.equals(getProxyPort(), null)) {
            request.setProxyPort(getProxyPort());
        }
        if (StringUtils.isNotBlank(getUrl())) {
            request.setUrl(new URL(getUrl()));
        }

    }

    /**
     * 获取是否允许cookie传输
     *
     * @return
     */
    public Boolean getCookieEnable() {
        return cookieEnable;
    }

    /**
     * 设置是否允许cookie传输
     *
     * @param cookieEnable
     */
    @Value("#{acfunConfigs['webClient.cookieManager.cookiesEnabled']}")
    public void setCookieEnable(Boolean cookieEnable) {
        this.cookieEnable = cookieEnable;
    }

    /**
     * 获取目标地址
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置目标地址
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取代理端口
     *
     * @return
     */
    public Integer getProxyPort() {
        return proxyPort;
    }

    /**
     * 设置代理端口
     * @param proxyPort
     */
    @Value("#{acfunConfigs['webRequest.proxyPort']}")
    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * 获取代理地址
     *
     * @return
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * 设置代理地址
     *
     * @param proxyHost
     */
    @Value("#{acfunConfigs['webRequest.proxyHost']}")
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * 获取请求编码
     *
     * @return
     */
    public String getCharset() {
        return charset;
    }

    /**
     * 设置请求编码
     *
     * @param charset
     */
    @Value("#{acfunConfigs['webRequest.charset']}")
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * 设置请求超时时间
     *
     * @param timeout
     */
    @Value("#{acfunConfigs['webClient.timeout']}")
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 获取请求超时时间
     *
     * @return
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * 获取浏览器代理
     *
     * @return
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 设置浏览器代理
     *
     * @param userAgent
     */
    @Value("#{acfunConfigs['userAgent']}")
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 获取cookie键
     *
     * @return
     */
    public String getCookieKey() {
        return cookieKey;
    }

    /**
     * 设置cookie键
     *
     * @param cookieKey
     */
    @Value("#{acfunConfigs['cookieKey']}")
    public void setCookieKey(String cookieKey) {
        this.cookieKey = cookieKey;
    }

    /**
     * 获取cookie值
     *
     * @return
     */
    public String getCookieValue() {
        return cookieValue;
    }

    /**
     * 设置cookie值
     *
     * @param cookieValue
     */
    @Value("#{acfunConfigs['cookieValue']}")
    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    /**
     * 获取需要传递的参数键
     *
     * @return
     */
    public String getDataKey() {
        return dataKey;
    }

    /**
     * 设置需要传递的参数键
     *
     * @param dataKey
     */
    @Value("#{acfunConfigs['dataKey']}")
    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /**
     * 获取需要传递的参数值
     *
     * @return
     */
    public String getDataValue() {
        return dataValue;
    }

    /**
     * 设置需要传递的参数值
     *
     * @param dataValue
     */
    @Value("#{acfunConfigs['dataValue']}")
    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    /**
     * 获取是否忽略请求内容类型
     *
     * @return
     */
    public Boolean getIgnoreContentType() {
        return ignoreContentType;
    }

    /**
     * 设置是否忽略请求内容类型
     *
     * @param ignoreContentType
     */
    @Value("#{acfunConfigs['ignoreContentType']}")
    public void setIgnoreContentType(Boolean ignoreContentType) {
        this.ignoreContentType = ignoreContentType;
    }

    /**
     * 获取是否跟随页面跳转
     *
     * @return
     */
    public Boolean getFollowRedirects() {
        return followRedirects;
    }

    /**
     * 设置是否跟随跳转
     *
     * @param followRedirects
     */
    @Value("#{acfunConfigs['followRedirects']}")
    public void setFollowRedirects(Boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    /**
     * 获取是否忽略http头错误
     *
     * @return
     */
    public Boolean getIgnoreHttpErrors() {
        return ignoreHttpErrors;
    }

    /**
     * 设置是否忽略http头错误
     *
     * @param ignoreHttpErrors
     */
    @Value("#{acfunConfigs['ignoreHttpErrors']}")
    public void setIgnoreHttpErrors(Boolean ignoreHttpErrors) {
        this.ignoreHttpErrors = ignoreHttpErrors;
    }

    /**
     * 获取目标页面最大页面容量
     *
     * @return
     */
    public Integer getMaxBodySize() {
        return maxBodySize;
    }

    /**
     * 设置目标页面最大容量
     *
     * @param maxBodySize
     */
    @Value("#{acfunConfigs['maxBodySize']}")
    public void setMaxBodySize(Integer maxBodySize) {
        this.maxBodySize = maxBodySize;
    }

    /**
     * 获取目标页面访问者
     *
     * @return
     */
    public String getReferrer() {
        return referrer;
    }

    /**
     * 设置目标页面访问者
     *
     * @param referrer
     */
    @Value("#{acfunConfigs['referrer']}")
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
    @Value("#{acfunConfigs['webClient.options.javaScriptEnabled']}")
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
    @Value("#{acfunConfigs['webClient.options.cssEnabled']}")
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
    @Value("#{acfunConfigs['webClient.options.throwExceptionOnFailingStatusCode']}")
    public void setThrowExceptionOnFailingStatusCode(Boolean throwExceptionOnFailingStatusCode) {
        this.throwExceptionOnFailingStatusCode = throwExceptionOnFailingStatusCode;
    }

    public Boolean getThrowExceptionOnScriptError() {
        return throwExceptionOnScriptError;
    }

    @Value("#{acfunConfigs['webClient.options.throwExceptionOnScriptError']}")
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
