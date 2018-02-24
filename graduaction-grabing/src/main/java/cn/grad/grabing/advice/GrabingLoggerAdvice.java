package cn.grad.grabing.advice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.grad.grabing.entity.NavNode;
import cn.grad.grabing.entity.Navigation;
import cn.grad.grabing.util.Validation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class GrabingLoggerAdvice {
	private final Log log = LogFactory.getLog(this.getClass());

	@Before("execution(* cn.grad.grabing.support.*.*Analizer.*(..))")
	public void beforeGrabing(JoinPoint point) {
		log.info("before grabing: " + point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
	}

	@After("execution(* cn.grad.grabing.support.*.*Analizer.*(..))")
	public void afterGrabing(JoinPoint point) {
		log.info(
				"after grabing: " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(* cn.grad.grabing.support.*.*Analizer.*(..))", returning = "returnValue")
	public void afterGrabingReturn(JoinPoint point, Object returnValue) {
		log.info("after grabing return: " + point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
		if (returnValue instanceof List || returnValue instanceof ArrayList || returnValue instanceof LinkedList) {
			@SuppressWarnings("rawtypes")
			List value = (List) returnValue;
			log.info("the contents size is: " + value.size() + " of after grabing return values");
		} else if(returnValue instanceof Navigation){
			Navigation nav=(Navigation) returnValue;
			List<NavNode> els=nav.getNavNodeList();
			log.info("the contents size is: " + (Validation.isObjNotNull(els)?els.size():0) + " of after grabing return values");
		}else {
			log.info("the contents is: "+returnValue+" of after grabing return values");
		}
		log.info("=====================================================================================================");
		log.info("=====================================================================================================");
		log.info("\n\n\n\n\n");
	}
}
