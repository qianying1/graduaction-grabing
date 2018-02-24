package cn.grad.grabing.service.impl;

import cn.grad.grabing.util.StrPropertiesMapper;
import org.springframework.stereotype.Service;

import cn.grad.grabing.service.PptvGrabService;
import cn.grad.grabing.util.BaseUtil;

@Service("pptvGrabServiceImpl")
public class PptvGrabServiceImpl extends BaseUtil implements PptvGrabService {

	/*private final String configuationPath = StrPropertiesMapper.CONFIGURATION_BASE_PATH + StrPropertiesMapper.PPTV
			+ ".properties";*/
	/**
	 * 皮皮电影爬虫入口
	 */
	@Override
	public void beginGrabing() {
	}

}
