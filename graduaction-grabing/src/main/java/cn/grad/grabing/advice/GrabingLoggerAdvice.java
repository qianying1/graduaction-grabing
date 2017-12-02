package cn.grad.grabing.advice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import cn.grad.grabing.util.BaseUtil;

@Aspect
public class GrabingLoggerAdvice extends BaseUtil {

	@Before("execution(* cn.grad.grabing.support.*Analizer.*(...))")
	public void beforeGrabing(JoinPoint point) {
		log.info("before grabing: " + point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
	}

	@After("execution(* cn.grad.grabing.support.*Analizer.*(...))")
	public void afterGrabing(JoinPoint point) {
		log.info(
				"after grabing: " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(* cn.grad.grabing.support.*Analizer.*(...))", returning = "returnValue")
	public void afterGrabingReturn(JoinPoint point, Object returnValue) {
		log.info("after grabing return: " + point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
		if (returnValue instanceof List || returnValue instanceof ArrayList || returnValue instanceof LinkedList){
			@SuppressWarnings("rawtypes")
			List value=(List) returnValue;
			log.info("the contents size is: "+value.size()+" of after grabing return values");
		}else{
			log.info("the contents is: "+returnValue+" of after grabing return value");
		}
	}
}
