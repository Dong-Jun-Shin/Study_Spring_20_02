package com.spring.client.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.client.board.dao.BoardDAO;
import com.spring.client.board.vo.BoardVO;
import com.spring.client.reply.dao.ReplyDAO;

import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_ = @Autowired)
	private BoardDAO boardDAO;

    @Setter(onMethod_ = @Autowired)
    private ReplyDAO replyDAO;
	
	//글 목록 조회
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		List<BoardVO> list = null;
		list = boardDAO.boardList(bvo);
		
		return list;
	}

	//전체 게시물 수 조회
	@Override
	public int boardListCnt(BoardVO bvo) {
		return boardDAO.boardListCnt(bvo);
		
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

	@Override
	public int pwdConfirm(BoardVO bvo) {
		int result = 0;
		result = boardDAO.pwdConfirm(bvo);
		
		return result;
	}

	@Override
	public BoardVO updateForm(BoardVO bvo) {
		BoardVO detail = null;
		detail = boardDAO.boardDetail(bvo);
	
		return detail;
	}

	@Override
	public int boardUpdate(BoardVO bvo) {
		int result = 0;
		result = boardDAO.boardUpdate(bvo);
		
		return result;
	}
	
	@Override
	public int boardDelete(int b_num) {
		int result = 0;
		result = boardDAO.boardDelete(b_num);
		
		return result;
	}

	//댓글이 존재하면 댓글수를 반환하고 존재하지 않으면 0을 반환
	@Override
	public int replyCnt(int b_num) {
		int result = 0;
	    result = replyDAO.replyCnt(b_num);

		return result;
	}
}
