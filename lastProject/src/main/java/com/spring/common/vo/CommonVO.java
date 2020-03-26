package com.spring.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//생성자를 직접 만들기 위해 @Data 주석
//@Data
@ToString
@Setter
@Getter
public class CommonVO {
	//조건 검색 시 사용할 필드(검색대상, 검색단어)
	private String search = "";
	private String keyword = "";
	
	//날짜 검색시 사용할 필드(시작일, 종료일)
    private String start_date = "";
    private String end_date = "";
    
    //페이지 처리시 사용할 필드(페이지 번호, 단위 데이터 양) 
    private int pageNum = 0;
    private int amount = 0;
    
    public CommonVO(){
    	//기본값은 1페이지 시작, 10개씩
    	this(1, 10);
    }
    
    public CommonVO(int pageNum, int amount) {
    	this.pageNum = pageNum;
    	this.amount = amount;
    }
}
