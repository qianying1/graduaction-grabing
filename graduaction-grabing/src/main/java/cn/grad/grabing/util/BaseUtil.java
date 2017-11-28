package cn.grad.grabing.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseUtil {

	public final Log log = LogFactory.getLog(this.getClass());
	@Autowired
	protected Mapper dozer;

}
