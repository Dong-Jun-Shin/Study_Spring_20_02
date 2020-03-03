package com.spring.client.board.vo;

import lombok.Data;

@Data
public class BoardVO {
	private int b_num		=0;	//글번호
	private String b_name	="";//작성자
	private String b_title	="";//제목
	private String b_content="";//내용
	private String b_date;		//작성일
	private String b_pwd	="";//비밀번호
	private String r_cnt	="";//댓글개수
}
