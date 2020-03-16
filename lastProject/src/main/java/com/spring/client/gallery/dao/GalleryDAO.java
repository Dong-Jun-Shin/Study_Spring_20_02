package com.spring.client.gallery.dao;

import java.util.List;

import com.spring.client.gallery.vo.GalleryVO;

public interface GalleryDAO {
	public List<GalleryVO> galleryList(GalleryVO gvo);
	public int galleryInsert(GalleryVO gvo);
}
