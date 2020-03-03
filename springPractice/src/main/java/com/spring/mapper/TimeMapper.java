package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.spring.example.domain.SubjectVO;

public interface TimeMapper {
	
	@Select("SELECT SYSDATE FROM DUAL")
	public String getTime();

	public String getTime2();
	
	public String getSubjectName(String s_num);
	
	public List<SubjectVO> getSubjects(int no);
	
	public int insertSubject(SubjectVO svo);
}
