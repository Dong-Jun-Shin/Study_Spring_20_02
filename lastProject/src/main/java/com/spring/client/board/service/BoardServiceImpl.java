package com.spring.client.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.client.board.dao.BoardDAO;
import com.spring.client.board.vo.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_ = @Autowired)
	private BoardDAO boardDAO;

	//글 목록 조회
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		List<BoardVO> list = null;
		list = boardDAO.boardList(bvo);
		
		return list;
	}

	//글 등록
	@Override
	public int boardInsert(BoardVO bvo) {
		int resultData = 0;
		resultData = boardDAO.boardInsert(bvo);
		
		return resultData;
	}

	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		BoardVO detail = boardDAO.boardDetail(bvo);
		if(detail != null) {
			String changeTag = detail.getB_content().toString().replaceAll("\n", "<br />");
			detail.setB_content(changeTag);
		}
		return detail;
	}
}
