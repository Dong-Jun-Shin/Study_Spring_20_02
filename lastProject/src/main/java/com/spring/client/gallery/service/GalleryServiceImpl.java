package com.spring.client.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.client.gallery.dao.GalleryDAO;
import com.spring.client.gallery.vo.GalleryVO;
import com.spring.common.file.FileUploadUtil;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class GalleryServiceImpl implements GalleryService{
	@Setter(onMethod_ = @Autowired)
	private GalleryDAO galleryDao;
	
	// 글목록 구현
	@Override
	public String galleryList(GalleryVO gvo) {
		List<GalleryVO> list = null;
		ObjectMapper mapper = new ObjectMapper();
		String listData = "";
		try {
			list = galleryDao.galleryList(gvo);
			listData = mapper.writeValueAsString(list);
			log.info(listData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return listData;
	}

	// 글 등록
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
}
