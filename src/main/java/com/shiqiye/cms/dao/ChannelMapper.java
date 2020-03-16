package com.shiqiye.cms.dao;
/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: 栏目
 * @author: ASUS
 * @date: 2020年3月5日 下午5:13:30
 */

import java.util.List;

import com.shiqiye.cms.bean.Category;
import com.shiqiye.cms.bean.Channel;

public interface ChannelMapper {
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
