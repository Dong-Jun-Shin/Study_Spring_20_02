package com.spring.client.gallery.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	/**
	 * 갤러리 리스트 페이지 호출
	 * 
	 * @param gvo
	 * @return
	 */
	@GetMapping("/galleryList")
	public String galleryList(@ModelAttribute("data") GalleryVO gvo) {
		log.info("galleryList 호출 성공");
		
		return "gallery/galleryList";
	}
	
	/**
	 * 갤러리 목록 구현하기
	 * @param gvo
	 * @return
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
	 * 갤러리 글 등록하기 (POST 방식)
	 * @param gvo
	 * @return
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
}
