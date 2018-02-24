package cn.grad.grabing.common;

import cn.grad.grabing.util.DateUtils;
import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * html unit浏览器模拟工具
 */
@Component("htmlUnitWebClient")
public class HtmlUnitWebClient extends WebClient{

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
     * 初始化浏览器
     */
    public void initializer(HtmlUnitWebClient webClient, Map<String, Object> params) throws Exception {
        if (params.isEmpty()) return;
        Class clientBean = webClient.getClass();
        Method[] beanMethods = clientBean.getMethods();
        for (Method method : beanMethods) {
            String methodName = method.getName();
            if (methodName.startsWith("set")) {
                Class[] cc = method.getParameterTypes();
                if (cc.length == 1) {
                    String type = cc[0].getName();    //parameter type
                    //property name
                    String prop = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                    //set property value
                    if (!params.keySet().contains(prop))
                        continue;
                    switch (type) {
                        case "java.lang.String":
                            method.invoke(webClient, params.get(prop).toString());
                            break;
                        case "int":
                            method.invoke(webClient, params.get(prop));
                            break;
                        case "java.lang.Integer":
                            method.invoke(webClient, Integer.valueOf(params.get(prop).toString()));
                            break;
                        case "long":
                            method.invoke(webClient, Long.valueOf(params.get(prop).toString()));
                            break;
                        case "java.lang.Long":
                            method.invoke(webClient, Long.valueOf(params.get(prop).toString()));
                            break;
                        case "boolean":
                            method.invoke(webClient, Boolean.valueOf(params.get(prop).toString()));
                            break;
                        case "java.lang.Boolean":
                            method.invoke(webClient, Boolean.valueOf(params.get(prop).toString()));
                            break;
                        case "java.util.Date":
                            method.invoke(webClient, DateUtils.parse(params.get(prop).toString()));
                            break;
                        default:
                            method.invoke(webClient, params.get(prop));
                            break;
                    }
                }
            }
        }
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getUserAgent() {
        return userAgent;
    }

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

    public Boolean getJavaScriptEnabled() {
        return javaScriptEnabled;
    }

    public void setJavaScriptEnabled(Boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }

    public Boolean getCssEnabled() {
        return cssEnabled;
    }

    public void setCssEnabled(Boolean cssEnabled) {
        this.cssEnabled = cssEnabled;
    }

    public Boolean getThrowExceptionOnFailingStatusCode() {
        return throwExceptionOnFailingStatusCode;
    }

    public void setThrowExceptionOnFailingStatusCode(Boolean throwExceptionOnFailingStatusCode) {
        this.throwExceptionOnFailingStatusCode = throwExceptionOnFailingStatusCode;
    }

    public Boolean getThrowExceptionOnScriptError() {
        return throwExceptionOnScriptError;
    }

    public void setThrowExceptionOnScriptError(Boolean throwExceptionOnScriptError) {
        this.throwExceptionOnScriptError = throwExceptionOnScriptError;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Boolean getCookieEnable() {
        return cookieEnable;
    }

    public void setCookieEnable(Boolean cookieEnable) {
        this.cookieEnable = cookieEnable;
    }
}
