package com.spring.admin.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.client.board.vo.BoardVO;

public interface AdminBoardService {
	public List<BoardVO> boardList(BoardVO bvo);
}
