package com.spring.client.gallery.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.client.gallery.service.GalleryService;
import com.spring.client.gallery.vo.GalleryVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/gallery/*")
@Log4j
@AllArgsConstructor
public class GalleryController {
	private GalleryService galleryService;
	
	/* 
	 * 모든 요청을 바인딩할 때, @InitBinder를 이용해서 자동으로 메서드를 호출하고 변환을 처리할 수 있다. 
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(MultipartFile.class, "file", new StringTrimmerEditor(true));
	}
	
//	@InitBinder				//모든 요청 바인딩 시, 호출 
//	@InitBinder("event") 	//특정 객체(모델, 값) 바인딩 시, 호출
//	public void initBinder(WebDataBinder webDataBinder) { 
//		//받고 싶지 않은 필드를 설정할수있다. 
//		webDataBinder.setDisallowedFields("id"); 
//		webDataBinder.addValidators(new EventValidator()); 
//	} 
	
	
	/**
	 * 갤러리 페이지 호출
	 * 
	 * @param gvo
	 * @return String
	 */
	@GetMapping("/galleryList")
	public String galleryList(@ModelAttribute("data") GalleryVO gvo) {
		log.info("galleryList 호출 성공");
		
		return "gallery/galleryList";
	}
	
	/**
	 * 갤러리 목록 구현하기
	 * @param gvo
	 * @return String
	 */
	@ResponseBody
	@GetMapping(value="/galleryData", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public String galleryData(GalleryVO gvo) {
		log.info("galleryData 호출 성공");
		log.info("gvo : " + gvo);
		
		String listData = galleryService.galleryList(gvo);
		
		return listData;
	}
	
	/**
	 * 갤러리 글 상세보기 구현
	 * @param gvo
	 * @return String
	 */
	@ResponseBody
	@GetMapping(value="/galleryDetail", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public String galleryDetail(@ModelAttribute GalleryVO gvo) {
		log.info("galleryDetail 호출 성공");
		
		String detail = galleryService.galleryDetail(gvo);
		return detail;
	}
	
	/**
	 * 갤러리 글 등록하기 (POST 방식)
	 * @param gvo
	 * @return String
	 */
	@ResponseBody
	@PostMapping(value="/galleryInsert", produces="text/plain; charset=UTF-8")
	public String galleryInsert(GalleryVO gvo) {
		log.info("galleryInsert 호출 성공");
		
		log.info("file name : " + gvo.getFile().getOriginalFilename());
		String value = "";
		int result = 0;
		
		result = galleryService.galleryInsert(gvo);
		if(result==1) {
			value = "성공";
		}else {
			value = "실패";
		}
		
		return value;
	}
	
	/**
	 * 비밀번호 체크
	 * @param gvo
	 * @return String
	 */
	@ResponseBody
	@PostMapping(value="/pwdConfirm", produces = MediaType.TEXT_PLAIN_VALUE)
	public String pwdConfirm(@ModelAttribute GalleryVO gvo) {
		log.info("pwdConfirm 호출 성공");
		
		// 아래 변수에는 입력 성공에 대한 상태값 저장(1 or 0)
		int result = 0;
		result = galleryService.pwdConfirm(gvo);
		log.info("result = " + result);
		
		return String.valueOf(result);
	}
	
	/**
	 * 갤러리 글 수정
	 * @param gvo
	 * @return String
	 */
	@ResponseBody
	@PostMapping(value="/galleryUpdate", produces = "text/plain; charset=UTF-8")
	public String galleryUpdate(@ModelAttribute GalleryVO gvo) {
		log.info("galleryUpdate 호출 성공");

		String result = "";
		int resultData = galleryService.galleryUpdate(gvo);
		
		if(resultData == 1) {
			result = "성공";
		}else {
			result = "실패";
		}
		
		return result;
	}
	
	/**
	 * 갤러리 글 삭제
	 * @param gvo
	 * @return String
	 */
	@ResponseBody
	@PostMapping(value="/galleryDelete", produces = "text/plain; charset=UTF-8")
	public String galleryDelete(@ModelAttribute GalleryVO gvo) {
		log.info("galleryDelete 호출 성공");
		
		String result = "";
		int resultData = galleryService.galleryDelete(gvo);

		if(resultData == 1) {
			result = "성공";
		}else {
			result = "실패";
		}
		return result;
	}
}
