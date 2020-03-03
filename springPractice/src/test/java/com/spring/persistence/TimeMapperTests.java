package com.spring.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.example.domain.SubjectVO;
import com.spring.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests{
	
	@Setter(onMethod_=@Autowired)
	private TimeMapper timeMapper;
	
	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());
		log.info("getTime() 메서드 실행");
		log.info(timeMapper.getTime());
		log.info("--------------------------");
		
		log.info("getTime2() 메서드 실행");
		log.info(timeMapper.getTime2());
		log.info("--------------------------");
		
		log.info("getSubjectName() 메서드 실행");
		log.info(timeMapper.getSubjectName("02"));
		log.info("--------------------------");
		
		log.info("getSubjectName2() 메서드 실행");
		log.info(timeMapper.getSubjects(2));
		log.info("--------------------------");
		
//		List<SubjectVO> list = timeMapper.getSubjects(2);
		for (SubjectVO svo : timeMapper.getSubjects(2)) {
			log.info(svo);
		}
		//람다식 - timeMapper.getSubjects(2).forEach(subject -> log.info(subject));
		
		SubjectVO svo = new SubjectVO();
		// 직접 부여
//		svo.setS_num("06");
//		svo.setS_name("호텔경영학과");
		
		// 학과번호 자동부여
		svo.setS_name("통계학과");

		log.info("insertSubject() 메서드 실행");
		log.info(timeMapper.insertSubject(svo)); // 0(실패) or 1(성공) 반환
		log.info("--------------------------");
		
	}
}
