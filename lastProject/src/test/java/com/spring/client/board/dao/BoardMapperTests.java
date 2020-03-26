package com.spring.client.board.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.client.board.vo.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardDAO bdao;
	
	@Test
	public void testBoardList() {
		BoardVO bvo = new BoardVO();
		bvo.setPageNum(2);
		bvo.setAmount(10);
		
		//검색 조건 부여
//		bvo.setSearch("b_title");
//		bvo.setKeyword("08");
		
//		bdao.boardList(bvo).forEach(board -> log.info(board));
		List<BoardVO> list = bdao.boardList(bvo);
		for (BoardVO vo : list) {
			log.info(vo);
		}
		
	}
	
	@Test
	public void testBoardListCnt() {
		BoardVO bvo = new BoardVO();
		//검색 조건 부여
//		bvo.setSearch("b_title");
//		bvo.setKeyword("08");
		
		int cnt = bdao.boardListCnt(bvo);
		log.info(cnt);
	}
	
//	@Test
//	public void testBoardInsert(){
//		BoardVO bvo = new BoardVO();
//		bvo.setB_name("김철수");
//		bvo.setB_title("김철수철수");
//		bvo.setB_content("아임 철수");
//		bvo.setB_pwd("1234");
//		
//		log.info(bdao.boardInsert(bvo));
//	}
	
	@Test
	public void testBoardDetail() {
		BoardVO bvo = new BoardVO();
		bvo.setB_num(5);
		
		log.info(bdao.boardDetail(bvo));
	}
	
	@Test
	public void testPwdConfirm() {
		BoardVO bvo = new BoardVO();
		bvo.setB_num(9);
		bvo.setB_pwd("1234");
		
		log.info(bdao.pwdConfirm(bvo));
	}
	
	@Test
	public void testBoardDelete() {
		BoardVO bvo = new BoardVO();
		bvo.setB_num(9);
		
		log.info(bdao.boardDelete(bvo.getB_num()));
	}
	
	@Test
	public void testBoardUpdate() {
		BoardVO bvo = new BoardVO();
		bvo.setB_num(10);
		bvo.setB_title("김처얼수");
		bvo.setB_content("집집");
		bvo.setB_pwd("1234");
		
		log.info(bdao.boardUpdate(bvo));
	}
}
