package com.shiqiye.cms.service;

import java.util.List;

import com.shiqiye.cms.bean.Category;
import com.shiqiye.cms.bean.Channel;

public interface ChannelService {
	/**
	 * 
	 * @Title: selets 
	 * @Description: 查询所有的栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目查分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);
}
