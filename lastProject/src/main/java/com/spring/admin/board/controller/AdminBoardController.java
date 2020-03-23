package com.spring.admin.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.admin.board.service.AdminBoardService;
import com.spring.client.board.vo.BoardVO;
import com.spring.common.excel.ListExcelView;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/admin")
@Log4j
public class AdminBoardController {
	
	@Setter(onMethod_ = @Autowired)
	private AdminBoardService adminBoardService;
	
	/**
	 * 글 목록 페이지 호출
	 * @param bvo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute("data")BoardVO bvo, Model model) {
		log.info("admin boardList 호출 성공");
		
		List<BoardVO> boardList = adminBoardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
		
		return "admin/board/boardList";
	}
	
	/**
	 * 엑셀 다운로드 구현하기
	 * 참고 : ListExcelView 클래스에서 Map타입으로 Model에 접근하게 된다.
	 * 
	 * @param bvo
	 * @return ModelAndView
	 */
	@RequestMapping(value="/board/boardExcel", method=RequestMethod.GET)
	public ModelAndView boardExcel(@ModelAttribute BoardVO bvo) {
		log.info("boardExcel 호출 성공");
		
		List<BoardVO> boardList = adminBoardService.boardList(bvo);
		
		//excel을 가진 view를 생성, List를 활용해서 template으로 전달
		ModelAndView mv = new ModelAndView(new ListExcelView());
		mv.addObject("list", boardList);
		mv.addObject("template", "boardTemplate.xlsx");
		mv.addObject("file_name", "board");
		
		//View를 반환
		return mv;
	}
}
