package com.spring.client.gallery.dao;

import java.util.List;

import com.spring.client.gallery.vo.GalleryVO;

public interface GalleryDAO {
	public List<GalleryVO> galleryList(GalleryVO gvo);
	public int galleryListCnt(GalleryVO gvo);
	public GalleryVO galleryDetail(GalleryVO gvo);
	public int galleryInsert(GalleryVO gvo);
	public int galleryUpdate(GalleryVO gvo);
	public int galleryDelete(int g_num);
	public int pwdConfirm(GalleryVO gvo);
}
