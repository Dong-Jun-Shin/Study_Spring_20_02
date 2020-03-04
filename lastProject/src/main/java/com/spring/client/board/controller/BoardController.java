package com.spring.client.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.client.board.service.BoardService;
import com.spring.client.board.service.BoardServiceImpl;
import com.spring.client.board.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
//BoardService 객체를 주입받기 위해 Setter와 둘 중 하나 사용
@AllArgsConstructor 
public class BoardController {
	
	// lombok의 @Setter로 설정자를 대체하고 
	// 맵핑을 위해 필요한 @Autowired를 onMethod로 같이 생성
    // @Setter(onMethod_ = @Autowired)
	private BoardService boardService;
	
	// @GetMapping("/boardList"), GET 방식으로 맵핑
	// mapping 정보가 같을 때, 용도에 따른 메서드 방식으로 구분
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardList 호출 성공");
		
		// 전체 레코드 조회
		List<BoardVO> boardList = boardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
		
		return "board/boardList";
	}
	
	@RequestMapping(value="/writeForm")
	public String writeForm(@ModelAttribute("data") BoardVO bvo) {
		log.info("writeForm 호출 성공");
		
		return "board/writeForm";
	}

	// @PostMapping("/boardInsert")
	@RequestMapping(value = "/boardInsert", method = RequestMethod.POST)
	public String boardInsert(BoardVO bvo, Model model) {
		log.info("boardInsert 호출 성공");
		
		String url = "";
		int resultData = boardService.boardInsert(bvo);
		
		if(resultData == 1) url = "/board/boardList";
		
		model.addAttribute("resultData", resultData);
		
		// 'redirect:'를 url에 붙여서 맵핑 정보를 다시 호출한다. 
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardInsert 호출 성공");
		log.info("bvo = " + bvo);
		
		BoardVO detail = boardService.boardDetail(bvo);
		model.addAttribute("detail", detail);
		
		return "board/boardDetail";
	}
}
