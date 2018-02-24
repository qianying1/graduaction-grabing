package cn.grad.grabing.helper.acfun.index;

import cn.grad.grabing.dao.helper.VideoDaoHelper;
import cn.grad.grabing.dao.mapper.VideoAuthorMapper;
import cn.grad.grabing.entity.Author;
import cn.grad.grabing.entity.BaseVideoNode;
import cn.grad.grabing.entity.MaskVideoNode;
import cn.grad.grabing.helper.acfun.SectionUtil;
import cn.grad.grabing.util.BaseLogger;
import cn.grad.grabing.util.DateUtils;
import cn.grad.grabing.util.NumberFormater;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分析首页中的香蕉榜节点
 */
@Component
public class BananaServiceHelper extends BaseLogger {

    @Autowired
    private VideoDaoHelper videoDaoHelper;

    /**
     * 进行香蕉榜爬取分析
     *
     * @param doc
     */
    public void analizeBananaSection(Document doc) {
        Element bananaSection = SectionUtil.getSectionByName(SectionUtil.BANANAANDARTICLE, doc);
        Element columnLeft = bananaSection.getElementsByClass("column-left").first();
        Element columnRight = bananaSection.getElementsByClass("column-right").first();
        /**
         * 分析香蕉榜右部，文章分区
         */
        analizeColumnRight(columnRight);

        Element headerEl = columnLeft.getElementsByTag("header").first();
        Element mainEl = columnLeft.getElementsByTag("div").first();

        /**
         * 分析香蕉榜头部，标题分区
         */
        analizeColumnHeader(headerEl);
        /**
         * 分析香蕉榜左部的主要分区，视频分区
         */
        analizeColumnLeft(mainEl);
    }

    /**
     * 香蕉榜左部，主要的视频分区
     *
     * @param mainEl
     */
    private void analizeColumnLeft(Element mainEl) {
        Elements figures = mainEl.getElementsByTag("figure");
        List<MaskVideoNode> videoNodeList = new ArrayList<>();
        for (Element figure : figures) {
            Element aEl = figure.getElementsByTag("a").first();
            Element imgEl = aEl.getElementsByTag("img").first();
            Element timeEl = aEl.getElementsByTag("time").first();
            String href = aEl.attr("abs:href");
            String image = imgEl.attr("abs:src");
            String times = timeEl.text();
            Element blockTitleEl = figure.getElementsByTag("figcaption").first();
            Element titleAEl = blockTitleEl.getElementsByTag("b").first().getElementsByTag("a").first();
            String title = titleAEl.text();
            String titleStr = titleAEl.attr("title");
            titleStr = titleStr.trim();
            String upMan = titleStr.substring(titleStr.indexOf("UP:") + 3, titleStr.indexOf("发布于"));
            String createTime = titleStr.substring(titleStr.indexOf("发布于") + 3, titleStr.indexOf("点击"));
            Long views;
            try {
                views = Long.valueOf(NumberFormater.pareseNumberStr(titleStr.substring(titleStr.indexOf("点击") + 2, titleStr.indexOf("评论"))));
            } catch (Exception e) {
                log.error("fail to grab a views data from acfun banana section nest exception is: "+e);
                views = Long.valueOf(0);
            }
            Long comments;
            try {
                comments = Long.valueOf(NumberFormater.pareseNumberStr(titleStr.substring(titleStr.indexOf("评论") + 2, titleStr.length())));
            } catch (Exception e) {
                log.error("fail to grab a comments data from acfun banana section nest exception is:"+e);
                comments = Long.valueOf(0);
            }
            Element authorEl = blockTitleEl.getElementsByTag("em").first().getElementsByTag("a").first();
            String indexHref = authorEl.attr("abs:href");
            upMan = upMan == null ? authorEl.text().replace("UP:", "") : upMan;
            Element bananaEl = blockTitleEl.getElementsByTag("span").first();
            Long bananas = Long.valueOf(NumberFormater.pareseNumberStr(bananaEl.text()));

            Author author = new Author();
            author.setIndexHref(indexHref);
            author.setName(upMan);
            author.setCreateDate(new Date());

            MaskVideoNode videoNode = new MaskVideoNode();
            videoNode.setTimes(times);
            videoNode.setAuthor(author);
            videoNode.setComments(comments);
            videoNode.setViews(views);
            videoNode.setUpMan(upMan);
            videoNode.setBananas(bananas);
            videoNode.setHref(href);
            videoNode.setLogo(image);
            videoNode.setTitle(title);
            videoNode.setCreateDate(DateUtils.parse(createTime));
            videoNode.setLocation("bananaSection of acfun");
            videoNodeList.add(videoNode);
        }
        videoDaoHelper.insertVideos(videoNodeList, true);
    }

    /**
     * 分析香蕉榜右部 文章分区
     *
     * @param headerEl
     */
    private void analizeColumnRight(Element headerEl) {

    }

    /**
     * 分析香蕉榜视频分区头部
     *
     * @param headerEl
     */
    private void analizeColumnHeader(Element headerEl) {

    }
}
