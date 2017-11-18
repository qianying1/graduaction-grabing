package cn.grad.grabing.service.impl;

import org.springframework.stereotype.Service;

import cn.grad.grabing.service.BiliBiliGrabService;
import cn.grad.grabing.util.BaseUtil;

@Service("bilibiliGrabServiceImpl")
public class BilibiliGrabServiceImpl extends BaseUtil implements BiliBiliGrabService {

	/**
	 * b站爬虫入口
	 */
	@Override
	public void beginBilibiliGrabing() {
		
	}

	@Override
	public void initBeforeGrabing(String targetValue) {
		// TODO Auto-generated method stub
		
	}

}
