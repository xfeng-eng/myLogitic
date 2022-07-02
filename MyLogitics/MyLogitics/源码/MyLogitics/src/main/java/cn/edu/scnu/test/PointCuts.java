package cn.edu.scnu.test;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
	@Pointcut(value = "within(cn.edu.scnu.test1.*)")
	public void aopDemo() {

	}
}