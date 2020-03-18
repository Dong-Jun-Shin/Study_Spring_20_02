package com.spring.client.gallery.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.client.gallery.vo.GalleryVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class GalleryMapperTests {
	@Setter(onMethod_ = @Autowired)
	private GalleryDAO galleryDao;
	
	@Test
	public void testGalleryList() {
		GalleryVO gvo = new GalleryVO();
		galleryDao.galleryList(gvo).forEach(list -> log.info(list));
	}

	@Test
	public void testGalleryDetail() {
		GalleryVO gvo = new GalleryVO();
		gvo.setG_num(7);
		log.info(galleryDao.galleryDetail(gvo).toString());
	}

	@Test
	public void testGalleryInsert() {
		GalleryVO gvo = new GalleryVO();
		gvo.setG_name("test");
		gvo.setG_subject("test");
		gvo.setG_content("test");
		gvo.setG_thumb("test");
		gvo.setG_file("test");
		gvo.setG_pwd("test");

		log.info(galleryDao.galleryInsert(gvo));
	}
	
	@Test
	public void testGalleryUpdate() {
		GalleryVO gvo = new GalleryVO();
		gvo.setG_num(14);
		gvo.setG_subject("update test");
		gvo.setG_content("update test");
		gvo.setG_thumb("update test");
		gvo.setG_file("update test");
		gvo.setG_pwd("update test");
		log.info(galleryDao.galleryUpdate(gvo));
	}

	@Test
	public void testGalleryDelete() {
		GalleryVO gvo = new GalleryVO();
		gvo.setG_num(1);
		log.info(galleryDao.galleryDelete(gvo.getG_num()));
	}
	
	@Test
	public void testPwdConfirm() {
		GalleryVO gvo = new GalleryVO();
		gvo.setG_num(7);
		gvo.setG_pwd("123");
		log.info(galleryDao.galleryDetail(gvo).toString());
	}
}
