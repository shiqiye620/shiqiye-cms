package com.shiqiye.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiqiye.cms.bean.Category;
import com.shiqiye.cms.bean.Channel;
import com.shiqiye.cms.service.ChannelService;
/**
 * 
 * @ClassName: ChannelController 
 * @Description: 栏目的控制器
 * @author: ASUS
 * @date: 2020年3月5日 下午5:23:11
 */
@Controller
@RequestMapping("channel")
public class ChannelController {
	@Resource
	private ChannelService channelService;
	/**
	 * 
	 * @Title: channels 
	 * @Description: 联动查询所属栏目
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@RequestMapping("channels")
	public List<Channel> channels(){
		return channelService.selects();
		
	}
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description:根据栏目查询分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@RequestMapping("selectsByChannelId")
	public List<Category> selectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return channelService.selectsByChannelId(channelId);
	}
}
