package cn.grad.grabing.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Component;

/**
 * htmlunit页面爬取工具类
 */
@Component
public class HtmlUnitUtils {

    /**
     * 初始化浏览器模拟器
     *
     * @param browserVersion 模拟浏览器的版本
     * @return
     */
    public static WebClient initualWebClient(BrowserVersion browserVersion){
        WebClient client=new WebClient(
                ObjectUtils.equals(browserVersion,null)
                        ?BrowserVersion.getDefault()
                        :browserVersion);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(true);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        return client;
    }


}
