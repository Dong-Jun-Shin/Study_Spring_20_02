package com.spring.client.reply.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.client.reply.vo.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Setter(onMethod_ = @Autowired)
	private ReplyDAO rdao;
	
	@Test
	public void testReplyList() {
		int b_num = 12;
		
		List<ReplyVO> list = rdao.replyList(b_num);
		for (ReplyVO vo : list) {
			log.info(vo);
		}
	}
	
//	@Test
//	public void testReplyInsert() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setB_num(12);
//		rvo.setR_name("test");
//		rvo.setR_content("test test");
//		rvo.setR_pwd("1234");
//		
//		log.info(rdao.replyInsert(rvo));
//	}
//	
	@Test
	public void testPwdConfirm() {
		ReplyVO rvo = new ReplyVO();
		rvo.setR_num(21);
		rvo.setR_pwd("1234");
		
		log.info(rdao.pwdConfirm(rvo));
	}
	
	@Test
	public void testReplyUpdate() {
		ReplyVO rvo = new ReplyVO();
		rvo.setB_num(12);
		rvo.setR_num(41);
		rvo.setR_name("update test");
		rvo.setR_content("update test update test");
		rvo.setR_pwd("1234");
		
		log.info(rdao.replyUpdate(rvo));
	}
	
	@Test
	public void testReplyDelete() {
		int r_num = 41;
		
		log.info(rdao.replyDelete(r_num));
		
	}
	
//	@Test
//	public void testReplyChoiceDelete() {
//		int b_num = 22;
//	}
	
	@Test
	public void testReplySelect() {
		int r_num = 21;
		
		log.info(rdao.replySelect(r_num));
	}
	
	@Test
	public void testReplyCnt() {
		int b_num = 12;
		
		log.info(rdao.replyCnt(b_num));
	}
}
