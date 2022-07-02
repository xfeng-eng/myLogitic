package cn.edu.scnu.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspect1 {

	@Before(value = "cn.edu.scnu.test.PointCuts.aopDemo()")
	public void before(JoinPoint joinPoint) {
		System.out.println("[Aspect1] before advise");
	}

	@Around(value = "cn.edu.scnu.test.PointCuts.aopDemo()")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[Aspect1] around advise 1");
		pjp.proceed();
		System.out.println("[Aspect1] around advise2");
	}

//	@AfterReturning(value = "test.PointCuts.aopDemo()")
//	public void afterReturning(JoinPoint joinPoint) {
//		System.out.println("[Aspect1] afterReturning advise");
//	}
//
//	@AfterThrowing(value = "test.PointCuts.aopDemo()")
//	public void afterThrowing(JoinPoint joinPoint) {
//		System.out.println("[Aspect1] afterThrowing advise");
//	}
//
//	@After(value = "test.PointCuts.aopDemo()")
//	public void after(JoinPoint joinPoint) {
//		System.out.println("[Aspect1] after advise");
//	}
}