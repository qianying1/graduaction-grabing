package cn.grad.grabing.helper.acfun;

import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.Validation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SectionUtil extends BaseUtil{
    public static final String CAROUSEL = "carousel";
    public static final String MONKEYRECOMMEND = "monkeyrecommend";
    public static final String BANANAANDARTICLE = "bananaandarticle";
    public static final String ENTERTAINMENT = "entertainment";
    public static final String DRAMASERIES = "dramaseries";
    public static final String GAME = "game";
    public static final String ANIMATION = "animation";
    public static final String MUSIC = "music";
    public static final String DANCERKANOJO = "dancerkanojo";
    public static final String FISHESPOOL = "fishespool";
    public static final String SCIENCEANDTECHNO = "scienceandtechno";
    public static final String SPORT = "sport";
    public static final String TEMPLATE = "template";
    public static final String HEADER = "header";

    public static Element getSectionByName(String sectionName, Document document) {
        int sectionNum = -1;
        switch (sectionName) {
            case CAROUSEL:
                sectionNum = 0;
                break;
            case MONKEYRECOMMEND:
                sectionNum = 1;
                break;
            case BANANAANDARTICLE:
                sectionNum = 2;
                break;
            case ENTERTAINMENT:
                return getEntertainmentSection(document);
            case DRAMASERIES:
                sectionNum = 5;
                break;
            case GAME:
                sectionNum = 6;
                break;
            case ANIMATION:
                sectionNum = 7;
                break;
            case DANCERKANOJO:
                sectionNum = 8;
                break;
            case FISHESPOOL:
                sectionNum = 9;
                break;
            case SCIENCEANDTECHNO:
                sectionNum = 10;
                break;
            case SPORT:
                sectionNum = 11;
                break;
            default:
                break;
        }
        Element result = document.getElementById("main").getElementsByTag("section").get(sectionNum);
        if (Validation.isNull(result)) {
            log.error("can not to get " + sectionName + " section");
            return null;
        }
        return result;
    }

    private static Element getEntertainmentSection(Document document){
        return document.getElementsByAttributeValue("b-name","【4】娱乐").first();
    }

}
