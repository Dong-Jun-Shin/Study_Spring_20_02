package com.spring.common.excel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.log4j.Log4j;
import net.sf.jxls.transformer.XLSTransformer;

@Log4j
public class ListExcelView extends AbstractView{

	/**
	 * 참고 : "Content-disposition : attachment"은 브라우저 인식 파일 확장자를 포함하여 모든확장자의 파일들에 대해
	 * 다운로드시 무조건 "파일 다운로드" 대화상자를 보여주도록 하는 헤더 속성이라 할 수 있다.
	 * @param model
	 * @param request
	 * @throws FileNotFoundException 
	 */
	
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//엑셀 파일로 다운로드를 위한 response 객체 세팅
		//엑셀 파일명 설정 (파일 이름_yyyyMMdd.xlsx)
		
		//Header -> Content-Disposition : attachment; fileName="파일 이름_yyyyMMdd.xlsx"
		response.setHeader("Content-Disposition",  "attachment; fileName=\"" + model.get("file_name") + "_" 
				+ new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".xlsx" + "\"");
		//ContentType -> application/x-msexcel; charset=UTF-8
		response.setContentType("application/x-msexcel; charset=UTF-8");
		
		/**
		 * 참고 : jXLS은 엑셀 파일 포맷의 템플릿을 이용하여
		 * 		엑셀 파일을 손쉽게 생성하기 위한 패키지
		 * 		(jxLS은 템플릿을 기반으로 최종 엑셀 파일을 생성)
		 */
		String docRoot = request.getSession().getServletContext().getRealPath("/WEB-INF/excel/");
		log.info("경로 체크(docRoot) : " + docRoot);
		
		//미리 만들어둔 엑셀 템플릿 파일에 대한 InputStream 생성 ("(/WEB-INF/excel/에 대한 실제 경로)... + .../template)
		InputStream is = new BufferedInputStream(new FileInputStream(docRoot + model.get("template")));
		
		XLSTransformer trans = new XLSTransformer();
		Workbook workbook = trans.transformXLS(is, model);
		is.close();
		
		workbook.write(response.getOutputStream());
	}
}
