package com.spring.admin.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.admin.board.service.AdminBoardService;
import com.spring.client.board.vo.BoardVO;

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
}
