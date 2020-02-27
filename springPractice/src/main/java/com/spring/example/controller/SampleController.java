package com.spring.example.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.example.domain.SampleDTO;
import com.spring.example.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

// 요청 - http://localhost:8080/sample/
@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController{
	
	// 맵핑 정보만 비교
	@RequestMapping("")
	public void basic() {
		log.info("basic...............");
	}
	
	// 맵핑 정보와 메서드 방식까지 비교
	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get...............");
	}
	
	// 오로지 Get 방식과 Url만 비교한 후 호출
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get.........");
	}
	
	// String 활용
	@GetMapping("/exam01")
	public String exam01(SampleDTO dto, Model model) {
		log.info("" + dto);
		model.addAttribute("dto", dto);
		
		return "exam01";
	}

	// @ModelAttribute 활용
	public String exam01(@ModelAttribute SampleDTO dto) {
		log.info("" + dto);
//		model.addAttribute("dto", dto);
		
		return "exam01";
	}
	
	// ModelAndView 활용
//	@GetMapping("/exam01")
//	public ModelAndView exam01(SampleDTO dto, ModelAndView mav) {
//		log.info("" + dto);
//		mav.addObject("dto", dto);
//		mav.setViewName("exam01");
//		return mav;
//	}
	
	// @RequestParam() 활용
	@GetMapping("/exam02")
	public String exam02(@RequestParam("name") String name, int age, Model model) {
		log.info("name: " + name);
		log.info("age: " + age);
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "exam02";
	}
	
	// GetMapping() 활용
	@GetMapping("/exam02List")
	public String exam02List(@RequestParam("language") ArrayList<String> language, Model model) {
		log.info("language: " + language.toString());
		for (String lang : language) {
			log.info("language values: " + lang);
		}
		
		model.addAttribute("language", language);
		
		return "exam02List";
	}
	
	@GetMapping("/exam02Array")
	public String exam02Array(@RequestParam("hobby") String hobby, Model model) {
		log.info("array hobby: " + hobby.toString());
		
		model.addAttribute("hobby", hobby);
		
		return "exam02Array";
	}
	
	
	// URL에 쿼리스트링 요청 시 '[]'는 '%5B %5D'를 사용
	// sample/exam02Bean?list%5B0%5D.name=길동&list%5B0%5D.age=19&list%5B1%5D.name=철수&list%5B1%5D.age=14
	
	// Model을 활용
//	@GetMapping("/exam02Bean")
//	public String exam02Bean(SampleDTOList list, Model model) {
//		log.info("list dtoList: " + list);
//		
//		model.addAttribute("sampleDTOList", list);
//		
//		return "exam02Bean";
//	}

	// @ModelAttribute[("sampleDTOList")] 활용, 매개변수 지정을 안하는 경우, 기본적으로 클래스의 앞글자가 소문자로 되어 속성 지정
	@GetMapping("/exam02Bean")
	public String exam02Bean(@ModelAttribute SampleDTOList list) {
		log.info("list dtoList: " + list);
		
		
		return "exam02Bean";
	}
	
	// sample/exam04?name=길동&age=13&page=3
	@GetMapping("/exam04")
	public String exam04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return"sample/exam04";
	}
	
	@GetMapping("/exam05")
	public @ResponseBody SampleDTO exam05() {
		log.info("/exam05........");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
}
				
