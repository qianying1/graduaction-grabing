package cn.grad.grabing.helper;

import cn.grad.grabing.service.*;
import cn.grad.grabing.service.impl.*;
import cn.grad.grabing.util.ApplicationContextUtil;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.StrPropertiesMapper;
import cn.grad.grabing.util.Validation;
import org.jsoup.Connection;

public class GrabingThread extends BaseUtil implements Runnable {

    private String targetName;
    private String targetValue;
    private AcfunGrabService acfunGrabServiceImpl;
    private BiliBiliGrabService biliBiliGrabServiceImpl;
    private LeshiGrabService leshiGrabServiceImpl;
    private PptvGrabService pptvGrabServiceImpl;
    private IqiyiGrabService iqiyiGrabServiceImpl;
    private SohuGrabService sohuGrabServiceImpl;
    private TudouGrabService tudouGrabServiceImpl;
    private YoukuGrabService youkuGrabServiceImpl;
    private DouyuGrabService douyuGrabServiceImpl;


    @Override
    public void run() {
        if (Validation.isStringNull(getTargetName()) || Validation.isStringNull(getTargetValue())
                || Validation.isStringEmpty(getTargetName()) || Validation.isStringEmpty(getTargetValue())) {
            log.warn("target is empty...");
        }
        while (true) {
            switch (targetName) {
                case StrPropertiesMapper.ACFUN:
                    beginAcfunGrabing();
                    break;
                case StrPropertiesMapper.BILIBILI:
                    beginBilibiliGrabing();
                    break;
                case StrPropertiesMapper.DOUYU:
                    beginDouyuGrabing();
                    break;
                case StrPropertiesMapper.LETV:
                    beginLeShiGrabing();
                    break;
                case StrPropertiesMapper.PPTV:
                    beginPptvGrabing();
                    break;
                case StrPropertiesMapper.IQIYI:
                    beginIqiyiGrabing();
                    break;
                case StrPropertiesMapper.SOHU:
                    beginSohuGrabing();
                    break;
                case StrPropertiesMapper.TUDOU:
                    beginTudouGrabing();
                    break;
                case StrPropertiesMapper.YOUKU:
                    beginYoukuGrabing();
                    break;
                default:
                    log.error("unavailable target name: " + targetName + " and the uri is: " + targetValue);
                    break;
            }
            try {
                /**
                 * 暂停1天
                 */
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                log.error("the thread is being broken in sleep on grabing for " + targetName);
            }
        }
    }

    /**
     * 准备进行a站的数据抓取
     */
    private void beginAcfunGrabing() {
        acfunGrabServiceImpl = (AcfunGrabServiceImpl) ApplicationContextUtil.getBean("acfunGrabServiceImpl");
        Connection connection = acfunGrabServiceImpl.initBeforeGrabing(targetValue);
        acfunGrabServiceImpl.initJsoupDocumentConnection(connection);
        acfunGrabServiceImpl.initHtmlUnitWebClient();
        acfunGrabServiceImpl.initHtmlUnitWebRequest();
        acfunGrabServiceImpl.beginGrabing();
    }

    /**
     * 准备进行B站的数据抓取
     */
    private void beginBilibiliGrabing() {
        biliBiliGrabServiceImpl = (BilibiliGrabServiceImpl) ApplicationContextUtil.getBean("bilibiliGrabServiceImpl");
        Connection connection = biliBiliGrabServiceImpl.initBeforeGrabing(targetValue);
        biliBiliGrabServiceImpl.beginGrabing(connection);
    }

    /**
     * 准备进行优酷网站的数据抓取
     */
    private void beginYoukuGrabing() {
        youkuGrabServiceImpl = (YoukuGrabServiceImpl) ApplicationContextUtil.getBean("youkuGrabServiceImpl");
        youkuGrabServiceImpl.initBeforeGrabing(targetValue);
        youkuGrabServiceImpl.beginGrabing();
    }

    /**
     * 准备进行土豆网站的数据抓取
     */
    private void beginTudouGrabing() {
        tudouGrabServiceImpl = (TudouGrabServiceImpl) ApplicationContextUtil.getBean("tudouGrabServiceImpl");
        tudouGrabServiceImpl.initBeforeGrabing(targetValue);
        tudouGrabServiceImpl.beginGrabing();
    }

    /**
     * 准备进行搜狐网站的数据抓取
     */
    private void beginSohuGrabing() {
        sohuGrabServiceImpl = (SohuGrabServiceImpl) ApplicationContextUtil.getBean("sohuGrabServiceImpl");
        sohuGrabServiceImpl.initBeforeGrabing(targetValue);
        sohuGrabServiceImpl.beginGrabing();
    }

    /**
     * 准备进行爱奇艺网站的数据抓取
     */
    private void beginIqiyiGrabing() {
        iqiyiGrabServiceImpl = (IqiyiGrabServiceImpl) ApplicationContextUtil.getBean("iqiyiGrabServiceImpl");
        iqiyiGrabServiceImpl.initBeforeGrabing(targetValue);
        iqiyiGrabServiceImpl.beginGrabing();
    }

    /**
     * 准备进行皮皮电影网站的数据抓取
     */
    private void beginPptvGrabing() {
        pptvGrabServiceImpl = (PptvGrabServiceImpl) ApplicationContextUtil.getBean("pptvGrabServiceImpl");
        pptvGrabServiceImpl.initBeforeGrabing(targetValue);
        pptvGrabServiceImpl.beginGrabing();
    }

    /**
     * 准备进行乐视网站的数据抓取
     */
    private void beginLeShiGrabing() {
        leshiGrabServiceImpl = (LeshiGrabServiceImpl) ApplicationContextUtil.getBean("leshiGrabServiceImpl");
        leshiGrabServiceImpl.initBeforeGrabing(targetValue);
        leshiGrabServiceImpl.beginGrabing();
    }

    /**
     * 准备进行斗鱼网站的数据抓取
     */
    private void beginDouyuGrabing() {
        douyuGrabServiceImpl = (DouyuGrabServiceImpl) ApplicationContextUtil.getBean("douyuGrabServiceImpl");
        douyuGrabServiceImpl.initBeforeGrabing(targetValue);
        douyuGrabServiceImpl.beginGrabing();
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

}
