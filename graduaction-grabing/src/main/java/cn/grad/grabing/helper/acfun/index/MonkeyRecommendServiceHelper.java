package cn.grad.grabing.helper.acfun.index;

import cn.grad.grabing.dao.helper.VideoDaoHelper;
import cn.grad.grabing.entity.Author;
import cn.grad.grabing.entity.BaseVideoNode;
import cn.grad.grabing.entity.MaskVideoNode;
import cn.grad.grabing.helper.acfun.SectionUtil;
import cn.grad.grabing.util.DateUtils;
import cn.grad.grabing.util.NumberFormater;
import cn.grad.grabing.util.Validation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MonkeyRecommendServiceHelper {
    @Autowired
    private VideoDaoHelper videoDaoHelper;
    /**
     * 分析猴子推荐节点头
     * @param sectionHeader
     */
    private void analizeMonkeyRecommendHeader(Element sectionHeader){}

    /**
     * 分析猴子节点
     *
     * @param doc
     */
    public void analizeMonkeyRecommendSection(Document doc){
        Element monkeyRecommend = SectionUtil.getSectionByName(SectionUtil.MONKEYRECOMMEND, doc);
        if (Validation.isObjNotNull(monkeyRecommend) && Validation.isListNotEmpty(monkeyRecommend.getElementsByTag("header")))
            analizeMonkeyRecommendHeader(monkeyRecommend.getElementsByTag("header").first());
        if (Validation.isObjNotNull(monkeyRecommend) && Validation.isListNotEmpty(monkeyRecommend.getElementsByClass("area-main"))){
            Element areaMain=monkeyRecommend.getElementsByClass("area-main").first();
            Element columnLeft=areaMain.getElementsByClass("column-left").first();
//            Element columnRight=areaMain.getElementsByClass("column-right").first();
            List<MaskVideoNode> videos=new ArrayList<>();
            /**
             * analize column left
             */
            Elements figures=columnLeft.getElementsByTag("figure");
            for(Element figure:figures){
                Element aEl=figure.getElementsByTag("a").first();
                Element timeEl=aEl.getElementsByTag("time").first();
                Element imgEl=aEl.getElementsByTag("img").first();
                Element titleEl=figure.getElementsByTag("figcaption").first();
                Element upEl=titleEl.getElementsByTag("p").first();
                MaskVideoNode videoNode=new MaskVideoNode();
                videoNode.setHref(aEl.attr("abs:href"));
                String upStr=upEl.getElementsByTag("a").first().text().replace("UP:","");
                String upIndexHref=upEl.getElementsByTag("a").first().attr("abs:href");
                String aStr=titleEl.getElementsByTag("a").first().attr("title").trim();
//                System.out.println("aStr: ============================================="+aStr);
                String title=aStr.substring(0,aStr.indexOf("UP:"));
//                String upStr=aStr.substring(aStr.indexOf("UP:")+3,aStr.indexOf("-")-4);
                String createStr=aStr.substring(aStr.indexOf("发布于")+3,aStr.indexOf("点击:"));
                String viewsStr=aStr.substring(aStr.indexOf("点击:")+3,aStr.indexOf("评论:"));
                String comments=aStr.substring(aStr.indexOf("评论:"),aStr.length());
                videoNode.setTitle(title);
                videoNode.setCreateDate(DateUtils.parse(createStr));
//                System.out.println("views: "+viewsStr);
                videoNode.setViews(Long.valueOf(NumberFormater.pareseNumberStr(viewsStr)));
                videoNode.setComments(Long.valueOf(NumberFormater.pareseNumberStr(comments)));
                videoNode.setLogo(imgEl.attr("abs:src"));
                videoNode.setTimes(timeEl.text());
                videoNode.setLocation("mokeySection of acfun");
                Author author=new Author();
                author.setName(upStr);
                author.setIndexHref(upIndexHref);
                videoNode.setAuthor(author);
                videos.add(videoNode);
            }
            /**
             * analize column right Acfun专题
             */
            /*Element aEl=columnRight.getElementsByTag("a").first();
            Element imgEl=aEl.getElementsByTag("img").first();
            Element titleEl=columnRight.getElementsByTag("div").first();
            BaseVideoNode videoNode=new BaseVideoNode();
            videoNode.setLogo(imgEl.attr("abs:src"));
            videoNode.setHref(aEl.attr("abs:href"));
            videoNode.setTitle(titleEl.attr("m-name"));
            videos.add(videoNode);*/
            videoDaoHelper.insertVideos(videos,true);
        }
    }

}
