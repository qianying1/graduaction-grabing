package cn.grad.grabing.service.impl;

import cn.grad.grabing.helper.AcfunDocumentInitailizer;
import cn.grad.grabing.helper.acfun.SectionUtil;
import cn.grad.grabing.helper.acfun.index.*;
import cn.grad.grabing.service.AcfunGrabService;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.StrPropertiesMapper;
import cn.grad.grabing.util.Validation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.WebRequest;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Properties;

@Service("acfunGrabServiceImpl")
public class AcfunGrabServiceImpl extends BaseUtil implements AcfunGrabService {

    @Resource(name = "acfunWebClient")
    private WebClient webClient;
    @Resource(name = "acfunWebRequest")
    private WebRequest webRequest;
    @Autowired
    private MonkeyRecommendServiceHelper monkeyRecommendServiceHelper;
    @Autowired
    private CarouseSectionServiceHelper carouseSectionServiceHelper;
    @Autowired
    private HeaderServiceHelper headerServiceHelper;
    @Autowired
    private BananaServiceHelper bananaServiceHelper;
    @Autowired
    private EntertainmentServiceHelper entertainmentServiceHelper;
    @Autowired
    private AcfunDocumentInitailizer acfunDocumentInitailizer;

    /*private final String configuationPath = StrPropertiesMapper.CONFIGURATION_BASE_PATH + StrPropertiesMapper.ACFUN
            + ".properties";*/

    /**
     * a站爬虫入口
     */
    @Override
    public void beginGrabing() {
        if (!Validation.isObjNull(getConn()))
            setDoc(getDocumentInitailizer().getDocumentByConn(getConn()));
        else
            log.error("could`t gettig html document from url: " + getSeedUri());
        if (Validation.isObjNull(getDoc())) {
            log.error("the document is empty for url: " + getSeedUri());
            return;
        }
        /**
         * 首页
         */
        index();
        /**
         * 进入爬虫模式
         */
        grabingFromReptile(StrPropertiesMapper.ACFUN);
    }

    protected void index() {
        headerServiceHelper.headerGrabbing(getDoc());
        /**
         * 爬取首页主体部分
         */
        carouseSectionServiceHelper.analizeCarouselSection(getDoc());
        /**
         * 猴子推荐
         */
        monkeyRecommendServiceHelper.analizeMonkeyRecommendSection(getDoc());
        /**
         * 香蕉榜
         */
        bananaServiceHelper.analizeBananaSection(getDoc());
        /**
         * 娱乐
         */
        Page page=null;
        try {
            page=webClient.getPage(this.getSeedUri());
        }catch (Exception e){
            e.printStackTrace();
            log.error("fail to get page from acfun url nest exception is: "+e);
        }
        entertainmentServiceHelper.analizeEntertainmentSection(page,getDoc());
    }

    public void initJsoupDocumentConnection(Connection connection) {
        acfunDocumentInitailizer.analizeConfigurationAndUseInConn(connection);
        setConn(connection);
    }

    public void initHtmlUnitWebClient() {
        acfunDocumentInitailizer.initWebClient(webClient);
    }

    public void initHtmlUnitWebRequest() {
        try{
            acfunDocumentInitailizer.initWebRequest(webRequest);
        }catch (Exception e){
            e.printStackTrace();
            log.error("fail to init param in document init method nest exception is : "+e);
        }
    }

}
