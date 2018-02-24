package cn.grad.grabing.service.impl;

import cn.grad.grabing.util.StrPropertiesMapper;
import org.springframework.stereotype.Service;

import cn.grad.grabing.service.TudouGrabService;
import cn.grad.grabing.util.BaseUtil;

@Service("tudouGrabServiceImpl")
public class TudouGrabServiceImpl extends BaseUtil implements TudouGrabService {

	private final String configuationPath = StrPropertiesMapper.CONFIGURATION_BASE_PATH + StrPropertiesMapper.TUDOU
			+ ".properties";
	/**
	 * 土豆爬虫入口
	 */
	@Override
	public void beginGrabing() {

	}

}
