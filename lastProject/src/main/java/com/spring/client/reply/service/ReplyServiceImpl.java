package com.spring.client.reply.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.client.reply.dao.ReplyDAO;
import com.spring.client.reply.vo.ReplyVO;
import lombok.Setter;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Setter(onMethod_ = @Autowired)
	private ReplyDAO replyDao;

	// 글목록 구현
	@Override
	public List<ReplyVO> replyList(Integer b_num) {
		List<ReplyVO> list = null;
		list = replyDao.replyList(b_num);
		return list;
	}

	// 글입력 구현
	@Override
	public int replyInsert(ReplyVO rvo) {
		int result = 0;
		try {
			result = replyDao.replyInsert(rvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	// 비밀번호 체크
	@Override
	public int pwdConfirm(ReplyVO rvo) {
		int result = 0;
		result = replyDao.pwdConfirm(rvo);
		return result;
	}

	// 수정 전 레코드 조회
	@Override
	public ReplyVO replySelect(Integer r_num) {
		ReplyVO rvo = null;
		rvo = replyDao.replySelect(r_num);
		return rvo;
	}

	// 글수정 구현
	@Override
	public int replyUpdate(ReplyVO rvo) {
		int result = 0;
		try {
			result = replyDao.replyUpdate(rvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// 글삭제 구현
	@Override
	public int replyDelete(Integer r_num) {
		int result = 0;
		try {
			result = replyDao.replyDelete(r_num);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
}
