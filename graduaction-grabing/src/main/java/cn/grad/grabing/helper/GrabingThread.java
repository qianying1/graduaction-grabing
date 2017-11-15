package cn.grad.grabing.helper;

import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.StrPropertiesMapper;
import cn.grad.grabing.util.Validation;

public class GrabingThread extends BaseUtil implements Runnable {

	private String targetName;
	private String targetValue;

	@Override
	public void run() {
		if (Validation.isStringNull(getTargetName()) || Validation.isStringEmpty(getTargetName())) {
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
				beginLetvGrabing();
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
		}
	}

	/**
	 * a站爬虫入口
	 */
	private void beginAcfunGrabing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * b站爬虫入口
	 */
	private void beginBilibiliGrabing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 斗鱼爬虫入口
	 */
	private void beginDouyuGrabing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 乐视爬虫入口
	 */
	private void beginLetvGrabing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 皮皮电影爬虫入口
	 */
	private void beginPptvGrabing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 爱奇艺爬虫入口
	 */
	private void beginIqiyiGrabing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 搜狐爬虫入口
	 */
	private void beginSohuGrabing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 土豆爬虫入口
	 */
	private void beginTudouGrabing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 优酷爬虫入口
	 */
	private void beginYoukuGrabing() {
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
