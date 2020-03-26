package com.spring.client.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.client.board.service.BoardService;
import com.spring.client.board.vo.BoardVO;
import com.spring.common.vo.PageDTO;

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
	
		// 전체 레코드 수 조회
		int total = boardService.boardListCnt(bvo);
		
		// 페이징 처리 (CommonVO 자리에 하위 클래스를 전달)
		model.addAttribute("pageMaker", new PageDTO(total, bvo));
		
		return "board/boardList";
	}
	
	@RequestMapping(value="/writeForm")
	public String writeForm(@ModelAttribute("data") BoardVO bvo) {
		log.info("writeForm 호출 성공");
		
		return "board/writeForm";
	}
	
	/**
	 * 게시물 등록
	 * 
	 * @param bvo
	 * @param model
	 * @return String
	 */
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

	/**
	 * 비밀번호 확인
	 * 
	 * @param bvo
	 * @return int로 result를 0 또는 1을 리턴할 수도 있고, 
	 * 		   String으로 result 값을 "성공 or 실패, 일치 or 불일치" 등을 리턴할 수도 있다. (현재 문자열 리턴)
	 * 
	 * 참고 : @ResponseBody는 전달된 뷰를 통해서 출력하는 것이 아니라 HTTP Response Body에 직접 출력하는
	 *       produces 속성은 지정한 미디어 타입과 관련된 응답을 생성하는데 사용한 실제 content 타입을 보장
	 */
	@ResponseBody
	@RequestMapping(value = "/pwdConfirm", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public String pwdConfirm(BoardVO bvo) {
		log.info("pwdConfirm 호출 성공");
		String value = "";
		
		//아래 변수에는 입력 성공에 대한 상태값 저장(1 or 0)
		int result = boardService.pwdConfirm(bvo);
		
		if(result == 1) 
			value="SUCCESS";
		else
			value="FAIL";
		
		log.info("result = " + result);
		
		return value;
	}
	
	/**
	 * 글 수정 폼으로 이동
	 * 
	 * @param bvo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateForm")
	public String updateForm(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("updateForm 호출 성공");
		
		BoardVO updateData = boardService.updateForm(bvo);
		model.addAttribute("updateData", updateData);
		
		return "board/updateForm";
	}
	
	/**
	 * 글 수정
	 * @param bvo
	 * @param ras
	 * @return 
	 * 
	 * 참고 : 
	 * RedirectAttributes 객체는 리다이렉트 시점(return "redirect:/경로")에 한번만 사용되는 데이터를 전송할 수 있는 
	 * addFlashAttribute()라는 기능을 지원한다.
	 * 
	 * addFlashAttribute() 메서드는 브라우저까지 전송되기는 하지만, URI 상에는 보이지 않는 숨겨진 데이터의 형태로 전달
	 */
	@RequestMapping(value = "/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(BoardVO bvo, RedirectAttributes ras) {
		log.info("boardUpdate 호출 성공");
		
		int result=0;
		String url="";
		
		result = boardService.boardUpdate(bvo);
		ras.addFlashAttribute("data", bvo);
		
		if(result == 1) {
			//아래 url은 수정 후 상세 페이지로 이동 (redirect:를 이용해서 get으로 전송하지만 파라미터 노출을 방지)
//			url="/board/boardDetail?b_num=" + bvo.getB_num();
			url="/board/boardDetail";
		}else {
//			url="/board/updateForm?b_num=" + bvo.getB_num();
			url="/board/updateForm";
		}
		
		return "redirect:" + url;
	}
	
	/**
	 * 글 삭제
	 */
	@RequestMapping(value = "/boardDelete")
	public String boardDelete(@ModelAttribute("data") BoardVO bvo, RedirectAttributes ras) {
		log.info("boardDelete 호출 성공");
		log.info("글번호 확인 : " + bvo);
		
		//아래 변수에는 입력 성공에 대한 상태값 담습니다.(1 or 0)
		int result = 0;
		String url = "";
		
		result = boardService.boardDelete(bvo.getB_num());
		
		// 성공 여부를 가리기 위해 임시적으로 값을 전달할 때 RedirectAttributes, addFlashAttribute() 사용
		// addAttribute()는 URL 뒤에 값을 붙여서 유지
		// addFlashAttribute()는 일회성으로 세션 후 재지정 요청이 들어오면 값은 사라진다.
		
		//속성명은 받을 대상의 첫글자 소문자 이름
		//문제 발생 시, 다시 돌아갈 페이지에 객체를 전달하는 구문
		ras.addFlashAttribute("boardVO", bvo);
		
		if(result == 1) {
			url="/board/boardList";
		}else {
			url="/board/boardDetail";
		}
		
		return "redirect:" + url;
	}
	
	/**
	 * 글삭제전 댓글 개수 구현하기
	 * @param b_num
	 * @return String
	 */
    @ResponseBody
    @RequestMapping(value="/replyCnt")
    public String replyCnt(@RequestParam("b_num") int b_num) {
       log.info("replyCnt 호출 성공");
       
       int result = 0;
       result = boardService.replyCnt(b_num);

       return String.valueOf(result);
    }
}
