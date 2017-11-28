package cn.grad.grabing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.grad.grabing.helper.acfun.IndexHelper;
import cn.grad.grabing.service.AcfunGrabService;
import cn.grad.grabing.util.BaseUtil;

@Service("acfunGrabServiceImpl")
public class AcfunGrabServiceImpl extends BaseUtil implements AcfunGrabService {

	@Autowired
	private IndexHelper indexHelper;

	

	/**
	 * a站爬虫入口
	 */
	@Override
	public void beginAcfunGrabing() {
		indexHelper.beginGrabing();
	}

	@Override
	public void initIndexBeforeGrabing(String targetUri) {
		indexHelper.initBeforeGrabing(targetUri);
	}

	

}
