package com.spring.client.gallery.service;

import com.spring.client.gallery.vo.GalleryVO;

public interface GalleryService {
	public String galleryList(GalleryVO gvo);
	public int galleryInsert(GalleryVO gvo);
}
