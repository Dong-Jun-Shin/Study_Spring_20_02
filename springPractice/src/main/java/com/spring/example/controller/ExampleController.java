package com.spring.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.example.domain.ExampleVO;

import lombok.extern.log4j.Log4j;

// @ResponseBody + @Controller의 결합 (기본으로 모든 메서드가 @ResponseBody 반환을 가짐)
@RestController
@RequestMapping("/example/*")
@Log4j
public class ExampleController {
	
	// produces는 반환되는 문서 타입으로 text/plain(단순 텍스트)와 인코딩 설정 (미설정 시, text/html이 기본) 
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	@GetMapping(value = "/getExample", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ExampleVO getSample() {
		return new ExampleVO(1, "홍길동", "010-1234-9087");
	}
	
	@GetMapping(value = "/getExample2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ExampleVO getSample2() {
		return new ExampleVO(2, "김철수", "010-2474-0873");
	}

	@GetMapping(value = "/getExample3", produces = MediaType.APPLICATION_XML_VALUE)
	public ExampleVO getSample3() {
		return new ExampleVO(3, "홍길수", "010-9875-1234");
	}
	
	@GetMapping(value = "/getExample4")
	public ExampleVO getSample4() {
		return new ExampleVO(4, "김철동", "010-2736-6798");
	}
	
	
	// List 타입 반환
	@GetMapping(value = "/getList")
	public List<ExampleVO> getList(){
		List<ExampleVO> list = new ArrayList<>();
		list.add(new ExampleVO(3, "이진희", "010-1295-4510"));
		list.add(new ExampleVO(4, "박철희", "010-3492-6711"));
		
		return list;
	}
	
	// Map 타입 반환
	@GetMapping(value = "/getMap")
	public Map<String, ExampleVO> getMap(){
		Map<String, ExampleVO> map = new HashMap<>();
		map.put("First",  new ExampleVO(5, "이진수", "010-2356-3390"));
		
		return map;
	}
		
}
