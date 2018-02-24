package cn.grad.grabing.helper.acfun.index;

import cn.grad.grabing.dao.helper.GrabLibDaoHelper;
import cn.grad.grabing.entity.HrefLib;
import cn.grad.grabing.helper.DocumentInitailizer;
import cn.grad.grabing.helper.acfun.SectionUtil;
import cn.grad.grabing.util.BaseLogger;
import cn.grad.grabing.util.StrPropertiesMapper;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 娱乐节点业务层辅助工具
 */
@Component
public class EntertainmentServiceHelper extends BaseLogger{

    @Autowired
    private GrabLibDaoHelper grabLibDaoHelper;

    @Resource(name="acfunWebClient")
    WebClient webClient;
    @Resource(name="acfunWebRequest")
    WebRequest webRequest;

    /**
     * 爬取的网址
     */
    private URL webUrl;

    /**
     * 分析娱乐节点
     *
     * @param doc
     */
    public void analizeEntertainmentSection(Page page,Document doc) {
        System.out.println(page.getWebResponse());
        Element section = SectionUtil.getSectionByName(SectionUtil.ENTERTAINMENT, doc);
        /**
         * 分析左边模块
         */
        Element columnLeft = section.getElementsByClass("column-left").first();
        //头部
        Element header = columnLeft.getElementsByClass("area-header").first();
        ananlizeEntertainmentHeader(header);
        //主体视频部分
        Element areaMain = columnLeft.getElementsByTag("div").first();
        analizeEntertainmentAreaMain(areaMain);
        /**
         * 分析右边模块
         */
        Element columnRight = section.getElementsByClass("column-right").first();
        Element rankList = columnRight.getElementsByTag("section").first();
        //娱乐排行榜头部
        Element headerEl = rankList.getElementsByTag("header").first();
        analizeEntertainmentRankListHeader(header);
        //娱乐排行榜列表
        Element entertainRankList = rankList.getElementsByTag("header").first();
        analizeEntertainmentRankingList(entertainRankList);
        //娱乐排行榜脚部
        Element entertainmentFooter = rankList.getElementsByTag("footer").first();
        analizeEntertainmentListFooter(entertainmentFooter);
    }

    //-----------------------------------------------------娱乐模块左模块-----------------------------------------------------//

    /**
     * 分析娱乐模块头部
     *
     * @param header
     */
    private void ananlizeEntertainmentHeader(Element header) {
        Element titleEl = header.getElementsByTag("h3").first();
        Element aEl = titleEl.getElementsByTag("b").first().getElementsByTag("a").first();
        String href = aEl.attr("abs:href");
        String name = aEl.text();
        HrefLib hrefLib = new HrefLib();
        hrefLib.setName(name);
        hrefLib.setWebSiteAddr(href);
        hrefLib.setWebsite(StrPropertiesMapper.ACFUN);
        List<HrefLib> inserts = new ArrayList<>();
        inserts.add(hrefLib);

        Element moreAEl = header.getElementsByTag("a").first();
        HrefLib moreHrefLib = new HrefLib();
        String aStr = moreAEl.attr("abs:href");
        moreHrefLib.setWebsite("acfun");
        moreHrefLib.setWebSiteAddr(aStr);
        moreHrefLib.setName("更多");
        inserts.add(moreHrefLib);

        Element recommendEl = header.getElementsByClass("area-recommend").first();
        Element recommend1El = recommendEl.getElementsByTag("span").first();
        String rec1AStr = recommend1El.getElementsByTag("a").first().attr("abs:href");
        String rec1Title = recommend1El.getElementsByTag("a").first().text();
        HrefLib rec1HrefLib = new HrefLib();
        rec1HrefLib.setName(rec1Title);
        rec1HrefLib.setWebSiteAddr(rec1AStr);
        rec1HrefLib.setWebsite("acfun");
        inserts.add(rec1HrefLib);

        Element recommend2El = recommendEl.getElementsByTag("span").get(1);
        String rec2AStr = recommend2El.getElementsByTag("a").first().attr("abs:href");
        String rec2Title = recommend2El.getElementsByTag("a").first().text();
        HrefLib rec2HrefLib = new HrefLib();
        rec2HrefLib.setName(rec2Title);
        rec2HrefLib.setWebSiteAddr(rec2AStr);
        rec2HrefLib.setWebsite("acfun");
        inserts.add(rec2HrefLib);

        grabLibDaoHelper.inserts(inserts);

//        Element btnEl = header.getElementsByTag("div").first();
    }

    private void ananlizeEntertainmentHeaderBtn(){
        DocumentInitailizer.initWebClientOptions(webClient.getOptions());
        DocumentInitailizer.initWebRequestUrl(getWebUrl());
    }

    /**
     * 分析娱乐模块主要视频部分
     *
     * @param areaMain
     */
    private void analizeEntertainmentAreaMain(Element areaMain) {

    }
    //-----------------------------------------------------娱乐模块左模块-----------------------------------------------------//

    //-----------------------------------------------------娱乐模块右模块-----------------------------------------------------//

    /**
     * 分析娱乐模块头部
     *
     * @param rankListHeader
     */
    private void analizeEntertainmentRankListHeader(Element rankListHeader) {

    }

    /**
     * 分析娱乐模块娱乐排行榜
     *
     * @param rankingList
     */
    private void analizeEntertainmentRankingList(Element rankingList) {

    }

    /**
     * 分析娱乐模块脚部
     *
     * @param rankListFooter
     */
    private void analizeEntertainmentListFooter(Element rankListFooter) {

    }
    //-----------------------------------------------------娱乐模块右模块-----------------------------------------------------//

    public URL getWebUrl() {
        return webUrl;
    }

    @Value("#{websites['acfun']}")
    public void setWebUrl(String webUrl) {
        URL url=null;
        try{
            url=new URL(webUrl);
        }catch (Exception e){
            e.printStackTrace();
            log.error("fail to create a Url object nest: "+e);
        }
        this.webUrl = url;
    }
}
