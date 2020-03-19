package com.spring.admin.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.client.board.dao.BoardDAO;
import com.spring.client.board.vo.BoardVO;

import lombok.Setter;

@Service
public class AdminBoardServiceImpl implements AdminBoardService{

	@Setter(onMethod_ = @Autowired)
	private BoardDAO bdao;
	
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		List<BoardVO> aList = null;
		
		aList = bdao.boardList(bvo);
		return aList;
	}
}
