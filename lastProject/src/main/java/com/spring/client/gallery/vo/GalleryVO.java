package com.spring.client.gallery.vo;

import org.springframework.web.multipart.MultipartFile;

import com.spring.common.vo.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GalleryVO extends CommonVO{
	private int g_num;
	private String g_nmae;
	private String g_subject;
	private String g_content;
	private MultipartFile file; //업로드할 파일만큼 선언한다.
	private String g_thumb;
	private String g_file;
	private String g_pwd;
	private String g_date;
	private int g_count;
}
