package cn.grad.grabing.helper;

import cn.grad.grabing.service.AcfunGrabService;
import cn.grad.grabing.service.BiliBiliGrabService;
import cn.grad.grabing.service.IqiyiGrabService;
import cn.grad.grabing.service.LeshiGrabService;
import cn.grad.grabing.service.PptvGrabService;
import cn.grad.grabing.service.SohuGrabService;
import cn.grad.grabing.service.TudouGrabService;
import cn.grad.grabing.service.YoukuGrabService;
import cn.grad.grabing.service.impl.AcfunGrabServiceImpl;
import cn.grad.grabing.service.impl.BilibiliGrabServiceImpl;
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
	private AcfunGrabService acfunGrabService=new AcfunGrabServiceImpl();
	private BiliBiliGrabService biliBiliGrabService=new BilibiliGrabServiceImpl();
	private LeshiGrabService leshiGrabService=new LeshiGrabServiceImpl();
	private PptvGrabService pptvGrabService=new PptvGrabServiceImpl();
	private IqiyiGrabService iqiyiGrabService=new IqiyiGrabServiceImpl();
	private SohuGrabService sohuGrabService=new SohuGrabServiceImpl();
	private TudouGrabService tudouGrabService=new TudouGrabServiceImpl();
	private YoukuGrabService youkuGrabService=new YoukuGrabServiceImpl();

	@Override
	public void run() {
		if (Validation.isStringNull(getTargetName()) || Validation.isStringNull(getTargetValue())
				|| Validation.isStringEmpty(getTargetName()) || Validation.isStringEmpty(getTargetValue())) {
			log.warn("target is empty...");
		}
		while (true) {
			switch (targetName) {
			case StrPropertiesMapper.ACFUN:
				acfunGrabService.beginAcfunGrabing();
				break;
			case StrPropertiesMapper.BILIBILI:
				biliBiliGrabService.beginBilibiliGrabing();
				break;
			case StrPropertiesMapper.DOUYU:
				beginDouyuGrabing();
				break;
			case StrPropertiesMapper.LETV:
				leshiGrabService.beginLetvGrabing();
				break;
			case StrPropertiesMapper.PPTV:
				pptvGrabService.beginPptvGrabing();
				break;
			case StrPropertiesMapper.IQIYI:
				iqiyiGrabService.beginIqiyiGrabing();
				break;
			case StrPropertiesMapper.SOHU:
				sohuGrabService.beginSohuGrabing();
				break;
			case StrPropertiesMapper.TUDOU:
				tudouGrabService.beginTudouGrabing();
				break;
			case StrPropertiesMapper.YOUKU:
				youkuGrabService.beginYoukuGrabing();
				break;
			default:
				log.error("unavailable target name: " + targetName + " and the uri is: " + targetValue);
				break;
			}
		}
	}

	/**
	 * 斗鱼爬虫入口
	 */
	private void beginDouyuGrabing() {
		// TODO Auto-generated method stub

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
