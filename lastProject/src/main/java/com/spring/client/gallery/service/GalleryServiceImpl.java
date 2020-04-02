package com.spring.client.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.client.gallery.dao.GalleryDAO;
import com.spring.client.gallery.vo.GalleryVO;
import com.spring.common.file.FileUploadUtil;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class GalleryServiceImpl implements GalleryService{
	@Setter(onMethod_ = @Autowired)
	private GalleryDAO galleryDao;
	
	// 갤러리 글목록 구현
	@Override
	public String galleryList(GalleryVO gvo) {
		List<GalleryVO> list = null;
		ObjectMapper mapper = new ObjectMapper();
		String listData = "";
		try {
			  int g_count = galleryDao.galleryListCnt(gvo);
              int pageNum = (gvo.getPageNum()==0? 1: gvo.getPageNum());
              int amount = (gvo.getAmount()==0? 0 : gvo.getAmount());
                 
              log.info("g_count = " + g_count + " / pageNum = "+pageNum +" / amount = "+ amount);
              list = galleryDao.galleryList(gvo);
              if(!list.isEmpty()) {
                       list.get(0).setG_count(g_count);
                       list.get(0).setPageNum(pageNum);
                       list.get(0).setAmount(amount);
              }
              
              listData = mapper.writeValueAsString(list);
              log.info(listData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return listData;
	}

	// 갤러리 글 상세보기
	@Override
	public String galleryDetail(GalleryVO gvo) {
		String data = "";
		GalleryVO vo = galleryDao.galleryDetail(gvo);
		try {
			ObjectMapper mapper = new ObjectMapper();
			data = mapper.writeValueAsString(vo);
//			logger.info(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	// 갤러리 글 등록
	@Override
	public int galleryInsert(GalleryVO gvo) {
		int result = 0;
		try {
			if(gvo.getFile() != null) {
				String fileName = FileUploadUtil.fileUpload(gvo.getFile(), "gallery");
				gvo.setG_file(fileName);
				
				String thumbName = FileUploadUtil.makeThumbnail(fileName);
				gvo.setG_thumb(thumbName);
			}
			result = galleryDao.galleryInsert(gvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	//갤러리 글 수정
	@Override
	public int galleryUpdate(GalleryVO gvo) {
		int result = 0;
		
		try {
			if(gvo.getFile() !=null) {
				if(!gvo.getG_file().isEmpty()) {
					FileUploadUtil.fileDelete(gvo.getG_file());
					FileUploadUtil.fileDelete(gvo.getG_thumb());
				}
				String fileName = FileUploadUtil.fileUpload(gvo.getFile(), "gallery");
				gvo.setG_file(fileName);
				
				String thumbName = FileUploadUtil.makeThumbnail(fileName);
				gvo.setG_thumb(thumbName);
			}else {
				gvo.setG_file("");
				gvo.setG_thumb("");
			}
			gvo.setG_pwd((gvo.getG_pwd() == null) ? "" : gvo.getG_pwd());
			
			result = galleryDao.galleryUpdate(gvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}

	//갤러리 글 삭제
	@Override
	public int galleryDelete(GalleryVO gvo) {
		int result = 0;
		
		try {
			GalleryVO vo = galleryDao.galleryDetail(gvo);
			
			FileUploadUtil.fileDelete(vo.getG_file());
			FileUploadUtil.fileDelete(vo.getG_thumb());
			
			result = galleryDao.galleryDelete(gvo.getG_num());
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}
	
	// 비밀번호 체크
	@Override
	public int pwdConfirm(GalleryVO gvo) {
		int result = 0;
		result = galleryDao.pwdConfirm(gvo);
		
		return result;
	}
}
