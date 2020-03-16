package com.shiqiye.cms.dao;

import java.util.List;

import javax.print.attribute.standard.Sides;

import com.shiqiye.cms.bean.Slide;

public interface SlideMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询轮播图
	 * @return
	 * @return: List<Slide>
	 */
	List<Slide> selects();
}
