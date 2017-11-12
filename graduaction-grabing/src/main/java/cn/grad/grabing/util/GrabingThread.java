package cn.grad.grabing.util;

import org.apache.bcel.generic.GETFIELD;
import org.apache.xml.utils.StopParseException;

import cn.grad.grabing.common.BaseUtil;
import cn.grad.grabing.common.Validation;

public class GrabingThread extends BaseUtil implements Runnable {

	private String target;

	@Override
	public void run() {
		if(Validation.isStringNull(getTarget())||Validation.isStringEmpty(getTarget())){
			log.warn("target is empty...");
			
		}
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
