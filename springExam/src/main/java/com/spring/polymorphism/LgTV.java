package com.spring.polymorphism;

import javax.annotation.Resource;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
//	@Autowired
//	@Qualifier("apple")
	@Resource(name="apple")
	private Speaker speaker;
	private int price;
	
	public void initMethod() {
		System.out.println("lg tv 객체 초기화 작업 처리.");
	}
	
	public void destroyMethod() {
		System.out.println("lg tv 객체 삭제");
	}
	
	public LgTV() {
		System.out.println("lg tv 객체 생성");
	}
	
	// Setter Injection을 사용 시 선언
//	public void setSpeaker(Speaker speaker) {
//		this.speaker = speaker;
//	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void powerOn() {
		System.out.println("LgTV---전원을 켠다. (가격 : " + price + "원)");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV---전원을 끈다.");
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
}

