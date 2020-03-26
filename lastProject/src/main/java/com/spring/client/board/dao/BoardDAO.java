package com.spring.client.board.dao;

import java.util.List;

import com.spring.client.board.vo.BoardVO;

public interface BoardDAO {
	//게시판 데이터  처리
	public List<BoardVO> boardList(BoardVO bvo);
	public int boardInsert(BoardVO bvo);
	public BoardVO boardDetail(BoardVO bvo);
	public int pwdConfirm(BoardVO bvo);
	public int boardDelete(int b_num);
	public int boardUpdate(BoardVO bvo);
	
	//페이징 처리
	public int boardListCnt(BoardVO bvo);
}
