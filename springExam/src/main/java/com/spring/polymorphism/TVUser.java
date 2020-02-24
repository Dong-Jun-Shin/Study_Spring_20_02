package com.spring.polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class TVUser {
	public static void main(String[] args) {
//		/* Factory 패턴으로 구현 (LgTv) */
//		BeanFactory factory = new BeanFactory();
//		TV tv1 = (TV)factory.getBean(args[0]);
//		
//		tv1.powerOn();
//		tv1.powerOff();
//		tv1.volumeUp();
//		tv1.volumeDown();
//		
		/* 애플리케이션 컨테이너를 이용한 빈 주입 (lgTv) */
		// 1. Spring 컨테이너 메모리 적재 및 구동
		AbstractApplicationContext aac = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Spring 컨테이너로부터 필요한 객체를 요청(Lookup)한다.
//		TV tv2 = (TV)aac.getBean("lg");
//		tv2.powerOn();
//		tv2.powerOff();
//		tv2.volumeUp();
//		tv2.volumeDown();
//		
//		TV tv3 = (TV)aac.getBean("samsung");
//		tv3.powerOn();
//		tv3.powerOff();
//		tv3.volumeUp();
//		tv3.volumeDown();

		TV tv4 = (TV)aac.getBean("tv");
		tv4.powerOn();
		tv4.powerOff();
		tv4.volumeUp();
		tv4.volumeDown();
		
		// 3. Spring 컨테이너를 종료한다.
		aac.close();
		
		
	}
}
