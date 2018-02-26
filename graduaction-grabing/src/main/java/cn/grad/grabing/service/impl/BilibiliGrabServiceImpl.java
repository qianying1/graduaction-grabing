package cn.grad.grabing.service.impl;

import cn.grad.grabing.common.HtmlUnitWebClient;
import cn.grad.grabing.common.JsoupCrawling;
import cn.grad.grabing.helper.BilibiliDocumentInitailizer;
import cn.grad.grabing.service.BiliBiliGrabService;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.StrPropertiesMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("bilibiliGrabServiceImpl")
public class BilibiliGrabServiceImpl extends BaseUtil implements BiliBiliGrabService {

    private final String configuationPath = StrPropertiesMapper.CONFIGURATION_BASE_PATH + StrPropertiesMapper.BILIBILI
            + ".properties";
    @Autowired
    private BilibiliDocumentInitailizer bilibiliDocumentInitailizer;
    /*@Resource(name = "bilibiliWebClient")
    private WebClient webClient;*/
    @Resource(name = "bilibiliWebRequest")
    private WebRequest webRequest;
    @Resource(name = "jsoupCrawling")
    private JsoupCrawling jsoupCrawling;
    @Resource(name = "htmlUnitWebClient")
    private HtmlUnitWebClient htmlUnitWebClient;
    //htmlUnit抓取工具的初始化
    private Map<String, Object> htmlUnitInitParams;

    /**
     * 超时时间
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

    private BilibiliGrabServiceImpl() {
        if (!ObjectUtils.equals(getTimeout(), null)) {
            htmlUnitInitParams.put("timeout",getTimeout());
        }
        if (StringUtils.isNotBlank(getUrl()) && StringUtils.isNotBlank(getCookieKey()) && StringUtils.isNotBlank(getCookieValue())) {
//            webClient.getCookieManager().addCookie(new Cookie(getUrl(), getCookieKey(), getCookieValue()));
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

    /**
     * b站爬虫入口
     */
    @Override
    public void beginGrabing(Connection jsoupConn) {
        try {
            //jsoup
            initializer(jsoupConn);

            //htmlunit
            htmlUnitWebClient.initializer(htmlUnitWebClient, htmlUnitInitParams);
        } catch (Exception e) {
            log.error("fail to initialize bilibili jsoup connection nest exception is: " + e, e);
        }
        grabbing();
    }

    /**
     * 开始抓取信息
     */
    private void grabbing() {

    }

    /**
     * 初始化
     *
     * @param jsoupConn
     */
    private void initializer(Connection jsoupConn) throws Exception {
        //jsoup
        bilibiliDocumentInitailizer.analizeConfigurationAndUseInConn(jsoupConn);
        this.setConn(jsoupConn);

        //htmlunit
        bilibiliDocumentInitailizer.initWebClient(webClient);
        bilibiliDocumentInitailizer.initWebRequest(webRequest);
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
     *
     * @param proxyPort
     */
    @Value("#{bilibiliConfigs['webRequest.proxyPort']}")
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
    @Value("#{bilibiliConfigs['webRequest.proxyHost']}")
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
    @Value("#{bilibiliConfigs['webRequest.charset']}")
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * 设置请求超时时间
     *
     * @param timeout
     */
    @Value("#{bilibiliConfigs['webClient.timeout']}")
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
    @Value("#{bilibiliConfigs['userAgent']}")
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
    @Value("#{bilibiliConfigs['cookieKey']}")
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
    @Value("#{bilibiliConfigs['cookieValue']}")
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
    @Value("#{bilibiliConfigs['dataKey']}")
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
    @Value("#{bilibiliConfigs['dataValue']}")
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
    @Value("#{bilibiliConfigs['ignoreContentType']}")
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
    @Value("#{bilibiliConfigs['followRedirects']}")
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
    @Value("#{bilibiliConfigs['ignoreHttpErrors']}")
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
    @Value("#{bilibiliConfigs['maxBodySize']}")
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
    @Value("#{bilibiliConfigs['referrer']}")
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
    @Value("#{bilibiliConfigs['webClient.options.javaScriptEnabled']}")
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
    @Value("#{bilibiliConfigs['webClient.options.cssEnabled']}")
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
    @Value("#{bilibiliConfigs['webClient.options.throwExceptionOnFailingStatusCode']}")
    public void setThrowExceptionOnFailingStatusCode(Boolean throwExceptionOnFailingStatusCode) {
        this.throwExceptionOnFailingStatusCode = throwExceptionOnFailingStatusCode;
    }

    public Boolean getThrowExceptionOnScriptError() {
        return throwExceptionOnScriptError;
    }

    @Value("#{bilibiliConfigs['webClient.options.throwExceptionOnScriptError']}")
    public void setThrowExceptionOnScriptError(Boolean throwExceptionOnScriptError) {
        this.throwExceptionOnScriptError = throwExceptionOnScriptError;
    }

}
