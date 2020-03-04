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
//		bdao.boardList(bvo).forEach(board -> log.info(board));
		List<BoardVO> list = bdao.boardList(bvo);
		for (BoardVO vo : list) {
			log.info(vo);
		}
		
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
}
