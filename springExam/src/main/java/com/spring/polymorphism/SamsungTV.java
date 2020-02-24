package com.spring.polymorphism;

public class SamsungTV implements TV{
	private Speaker speaker;
	private int price;
	
	public SamsungTV() {
		System.out.println("samsung tv 기본 생성자 생성");
	}

	public SamsungTV(Speaker speaker) {
		System.out.println("samsung tv 매개변수 1개 생성자 생성");
		this.speaker = speaker;
	}

	public SamsungTV(Speaker speaker, int price) {
		System.out.println("samsung tv 매개변수 2개 생성자 생성");
		this.speaker = speaker;
		this.price = price;
	}

	public void initMethod() {
		System.out.println("samsung tv 객체 초기화 작업 처리.");
	}
	
	public void destroyMethod() {
		System.out.println("samsung tv 객체 삭제");
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV---전원을 켠다. (가격 : " + price + "원)");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV---전원을 끈다.");
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
