package cn.grad.grabing.service.impl;

import cn.grad.grabing.util.StrPropertiesMapper;
import org.springframework.stereotype.Service;

import cn.grad.grabing.service.IqiyiGrabService;
import cn.grad.grabing.util.BaseUtil;

@Service("iqiyiGrabServiceImpl")
public class IqiyiGrabServiceImpl extends BaseUtil implements IqiyiGrabService {

	/*private final String configuationPath = StrPropertiesMapper.CONFIGURATION_BASE_PATH + StrPropertiesMapper.IQIYI
			+ ".properties";*/
	/**
	 * 爱奇艺爬虫入口
	 */
	@Override
	public void beginGrabing() {

	}

}
