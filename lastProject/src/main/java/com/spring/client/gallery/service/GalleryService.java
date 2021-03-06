package com.spring.client.gallery.service;

import com.spring.client.gallery.vo.GalleryVO;

public interface GalleryService {
	public String galleryList(GalleryVO gvo);
	public String galleryDetail(GalleryVO gvo);
	public int galleryInsert(GalleryVO gvo);
	public int galleryUpdate(GalleryVO gvo);
	public int galleryDelete(GalleryVO gvo);
	public int pwdConfirm(GalleryVO gvo);
}
