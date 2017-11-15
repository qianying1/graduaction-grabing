package cn.grad.grabing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.grad.grabing.service.AcfunGrabService;
import cn.grad.grabing.support.acfunc.IndexAnalizer;

@Service("acfunGrabService")
public class AcfunGrabServiceImpl implements AcfunGrabService {

	@Autowired
	private IndexAnalizer indexAnalizer;
	/**
	 * a站爬虫入口
	 */
	@Override
	public void beginAcfunGrabing() {
		indexAnalizer.testing();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("acfun grabing>>>>>>");
	}

}
