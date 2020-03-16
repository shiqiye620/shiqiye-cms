package com.shiqiye.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiqiye.cms.bean.Category;
import com.shiqiye.cms.bean.Channel;
import com.shiqiye.cms.dao.ChannelMapper;
import com.shiqiye.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private ChannelMapper channelMapper;
	@Override
	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return channelMapper.selects();
	}
	
	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return channelMapper.selectsByChannelId(channelId);
	}

}
