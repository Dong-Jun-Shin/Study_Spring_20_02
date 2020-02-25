package com.spring.polymorphism;

import org.springframework.stereotype.Component;

@Component("sony")
public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("Sony Speaker 객체 생성");
	}

	@Override
	public void volumeUp() {
		System.out.println("Sony Speaker 소리를 키운다.");
	}

	@Override
	public void volumeDown() {
		System.out.println("Sony Speaker 소리를 줄인다.");
	}

}
