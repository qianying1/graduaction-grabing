package cn.grad.grabing.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PrepareGrabingAdvice {
	private final Log log = LogFactory.getLog(this.getClass());

	@Before("execution(* cn.grad.grabing.helper.*.*Helper.begin*(..))")
	public void beforePrepare(JoinPoint point) {
		log.info("begin grabing a website from : " + point.getSignature().getDeclaringTypeName());
	}

	@After("execution(* cn.grad.grabing.helper.*.*Helper.begin*(..))")
	public void afterPrepare(JoinPoint point) {
		log.info("after grabing a website from : " + point.getSignature().getDeclaringTypeName());
		log.info("\n\n");
	}
}
