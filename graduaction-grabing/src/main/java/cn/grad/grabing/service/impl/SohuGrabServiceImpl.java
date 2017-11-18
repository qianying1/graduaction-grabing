package cn.grad.grabing.service.impl;

import org.springframework.stereotype.Service;

import cn.grad.grabing.service.SohuGrabService;
import cn.grad.grabing.util.BaseUtil;

@Service("sohuGrabServiceImpl")
public class SohuGrabServiceImpl extends BaseUtil implements SohuGrabService {

	/**
	 * 搜狐爬虫入口
	 */
	@Override
	public void beginSohuGrabing() {
	}

	@Override
	public void initBeforeGrabing(String targetValue) {
		// TODO Auto-generated method stub
		
	}

}
