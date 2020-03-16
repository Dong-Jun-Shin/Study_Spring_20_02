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
}
