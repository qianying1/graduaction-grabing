package cn.grad.grabing.helper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.grad.grabing.service.AcfunGrabService;
import cn.grad.grabing.service.BiliBiliGrabService;
import cn.grad.grabing.service.DouyuGrabService;
import cn.grad.grabing.service.IqiyiGrabService;
import cn.grad.grabing.service.LeshiGrabService;
import cn.grad.grabing.service.PptvGrabService;
import cn.grad.grabing.service.SohuGrabService;
import cn.grad.grabing.service.TudouGrabService;
import cn.grad.grabing.service.YoukuGrabService;
import cn.grad.grabing.service.impl.AcfunGrabServiceImpl;
import cn.grad.grabing.service.impl.BilibiliGrabServiceImpl;
import cn.grad.grabing.service.impl.DouyuGrabServiceImpl;
import cn.grad.grabing.service.impl.IqiyiGrabServiceImpl;
import cn.grad.grabing.service.impl.LeshiGrabServiceImpl;
import cn.grad.grabing.service.impl.PptvGrabServiceImpl;
import cn.grad.grabing.service.impl.SohuGrabServiceImpl;
import cn.grad.grabing.service.impl.TudouGrabServiceImpl;
import cn.grad.grabing.service.impl.YoukuGrabServiceImpl;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.StrPropertiesMapper;
import cn.grad.grabing.util.Validation;

public class GrabingThread extends BaseUtil implements Runnable {

	private String targetName;
	private String targetValue;
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:spring/spring-mybatis.xml");
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
//		while (true) {
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
//		}
	}

	private void beginYoukuGrabing() {
		youkuGrabServiceImpl = (YoukuGrabServiceImpl) context.getBean("youkuGrabServiceImpl");
		youkuGrabServiceImpl.initBeforeGrabing(targetValue);
		youkuGrabServiceImpl.beginYoukuGrabing();
	}

	private void beginTudouGrabing() {
		tudouGrabServiceImpl = (TudouGrabServiceImpl) context.getBean("tudouGrabServiceImpl");
		tudouGrabServiceImpl.initBeforeGrabing(targetValue);
		tudouGrabServiceImpl.beginTudouGrabing();
	}

	private void beginSohuGrabing() {
		sohuGrabServiceImpl = (SohuGrabServiceImpl) context.getBean("sohuGrabServiceImpl");
		sohuGrabServiceImpl.initBeforeGrabing(targetValue);
		sohuGrabServiceImpl.beginSohuGrabing();
	}

	private void beginIqiyiGrabing() {
		iqiyiGrabServiceImpl = (IqiyiGrabServiceImpl) context.getBean("iqiyiGrabServiceImpl");
		iqiyiGrabServiceImpl.initBeforeGrabing(targetValue);
		iqiyiGrabServiceImpl.beginIqiyiGrabing();
	}

	private void beginPptvGrabing() {
		pptvGrabServiceImpl = (PptvGrabServiceImpl) context.getBean("pptvGrabServiceImpl");
		pptvGrabServiceImpl.initBeforeGrabing(targetValue);
		pptvGrabServiceImpl.beginPptvGrabing();
	}

	private void beginLeShiGrabing() {
		leshiGrabServiceImpl = (LeshiGrabServiceImpl) context.getBean("leshiGrabServiceImpl");
		leshiGrabServiceImpl.initBeforeGrabing(targetValue);
		leshiGrabServiceImpl.beginLetvGrabing();
	}

	private void beginBilibiliGrabing() {
		biliBiliGrabServiceImpl = (BilibiliGrabServiceImpl) context.getBean("bilibiliGrabServiceImpl");
		biliBiliGrabServiceImpl.initBeforeGrabing(targetValue);
		biliBiliGrabServiceImpl.beginBilibiliGrabing();
	}

	private void beginAcfunGrabing() {
		acfunGrabServiceImpl = (AcfunGrabServiceImpl) context.getBean("acfunGrabServiceImpl");
		acfunGrabServiceImpl.initBeforeGrabing(targetValue);
		acfunGrabServiceImpl.beginAcfunGrabing();
	}
	
	/**
	 * 斗鱼爬虫入口
	 */
	private void beginDouyuGrabing() {
		douyuGrabServiceImpl=(DouyuGrabServiceImpl)context.getBean("douyuGrabServiceImpl");
		douyuGrabServiceImpl.initBeforeGrabing(targetValue);
		douyuGrabServiceImpl.beginDouyuGrabing();
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
