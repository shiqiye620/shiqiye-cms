package com.shiqiye.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.Collect;
import com.shiqiye.cms.dao.CollectMapper;
import com.shiqiye.cms.service.CollectService;
import com.shiqiye.cms.util.CMSException;
import com.shiqiye.common.utils.StringUtil;
@Service
public class CollectServiceImpl implements CollectService{
	@Resource
	private CollectMapper collectMapper;
	@Override
	public int insert(Collect collect) {
		// TODO Auto-generated method stub
		if(StringUtil.isHttpUrl(collect.getUrl()))
		throw new CMSException("不是合法的url");
		return collectMapper.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.delete(id);
	}
	
	@Override
	public PageInfo<Collect> selects(Integer userId, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, pageSize);
		List<Collect> list = collectMapper.selects(userId);
		return new PageInfo<Collect>(list);
	}

	@Override
	public Collect selectByTitleAndUserId(String title, Integer userId) {
		// TODO Auto-generated method stub
		return collectMapper.selectByTitleAndUserId(title, userId);
	}

	
	
}
