package com.shiqiye.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiqiye.cms.bean.Slide;
import com.shiqiye.cms.dao.SlideMapper;
import com.shiqiye.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {
	@Resource
	private SlideMapper slideMapper;
	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return slideMapper.selects();
	}

}
