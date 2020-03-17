package com.spring.common.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
public class FileUploadUtil {
	private static String pathName = "C:\\Users\\user\\Desktop\\GitHub\\uploadStorage\\";
	
	/* 파일 업로드할 폴더 생성 */
	public static void makeDir(String docRoot) {
		File fileDir = new File(docRoot);
		if(fileDir.exists()) {
			return;
		}
		fileDir.mkdirs();
	}
	
	/*
	 * 파일 업로드 메서드
	 * 1. 파일명이 중복시 해결 방법, 2. 원하는 파일명과 폴더 생성, 실제 업로드 하는 것
	 * System.currntTimeMillis()를 사용하거나 128비트의 수인 UUID의 .randomUUID() .toString()을 사용
	 * 표준 형식에서 UUID는 32개의 16진수로 표현되며 총 36개 문자(32개 문자와 4개의 하이픈)로
	 * 되어있는 8-4-4-4-12라는 5개의 그룹을 하이픈으로 구분해서 구성. 이를테면 다음과 같다.
	 * 50e8400-e29b-41d4-a716-44655440000
	 */
	public static String fileUpload(MultipartFile file, String fileName) throws IOException{
		log.info("fileUpload 호출 성공");
		
		String real_name=null;
		// MultipartFile 클래스의 getFile() 메서드로 클라이언트가 업로드한 파일
		String org_name = file.getOriginalFilename();
		log.info("org_name :" + org_name);
		
		// 파일명 변경(중복되지 않게)
		if(org_name != null && (!org_name.equals(""))) {
			real_name = fileName + "_" + System.currentTimeMillis() + "_" + org_name; //저장할 파일
			
			String docRoot = pathName + fileName;
			makeDir(docRoot);
			
			File fileAdd = new File(docRoot + "/" + real_name); //파일 생성후
			log.info("업로드할 파일(fileAdd); : " + fileAdd);
			
			file.transferTo(fileAdd);
		}
		
		return real_name;
	}
	
	/* 다중 파일 업로드 메서드 */
	
	
	/* 파일 삭제 메서드 */
	public static void fileDelete(String fileName) throws IOException{
		log.info("fileDelete 호출 성공");
		boolean result = false;
		String startDirName = "", docRoot = "";
		String dirName = fileName.substring(0, fileName.indexOf("_"));	// 경로 반환
		
		// 썸네일인지, 원본인지에 따른 경로와 파일명 설정
		if(dirName.equals("thumbnail")) {
			startDirName = fileName.substring(dirName.length() + 1, fileName.indexOf("_", dirName.length() + 1));
			docRoot = pathName + startDirName + "\\" + dirName;
		}else {
			docRoot = pathName + dirName;
		}
		
		/* 삭제 시작 */
		// 삭제할 파일 정보(파일 생성 후)
		File fileDelete = new File(docRoot + "/" + fileName);
		log.info("삭제할 파일(fileDelete); : " + fileDelete);
		
		// 경로의 존재 여부와 파일인지 여부 확인
		if(fileDelete.exists() && fileDelete.isFile()) {
			result = fileDelete.delete();
		}
		log.info("파일 삭제 여부(true/false) : " + result);
	}
	
	/* 파일 Thumbnail 생성 메서드 */
	public static String makeThumbnail(String fileName) throws Exception{
		String dirName = fileName.substring(0, fileName.indexOf("_"));
		
		// 이미지가 존재하는 폴더 추출
		String imgPath = pathName + dirName;
		// 추출된 폴더의 실제 경로(물리적 위치: C:\...)
		File fileAdd = new File(imgPath, fileName);
		log.info("원본 이미지 파일(fileAdd); : " + fileAdd);
		
		// 경로와 파일명에 맞는 이미지 로드
		BufferedImage sourceImg = ImageIO.read(fileAdd);
		// 생성할 썸네일용 이미지 정보
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 133);
		
		// resize(대상[BufferedImage 타입], 원본비율, 높이 또는 너비, 크기)
		// 파일 이름 : thumbnail_'fileName'
		// 파일 경로 : C:\...\thumbnail\파일 목록
		String thumbnailName = "thumbnail_" + fileName;
		String docRoot = imgPath + "/thumbnail";
		makeDir(docRoot);
		
		// 파일 이름
		File newFile = new File(docRoot, thumbnailName);
		log.info("업로드할 파일(newFile); : " + newFile);
		
		//.jpg .gif .png ...
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		log.info("확장자(formatName) : " + formatName);
		
		// 썸네일 저장
		ImageIO.write(destImg, formatName, newFile);
		return thumbnailName;
	}
}
