package com.spring.client.board.vo;

import com.spring.common.vo.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * @EqualsAndHashCode(callSuper = true)
 * @Data 사용해서 메소드 자동 생성 시 부모 클래스의 필드까지 감안할지 안 할지에 대해서 설정 필요
 * callSuper = true(부모까지 감안) || false(자신만 감안)(기본값)
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BoardVO extends CommonVO{
	private int b_num		=0;	//글번호
	private String b_name	="";//작성자
	private String b_title	="";//제목
	private String b_content="";//내용
	private String b_date;		//작성일
	private String b_pwd	="";//비밀번호
	private String r_cnt	="";//댓글개수
}
