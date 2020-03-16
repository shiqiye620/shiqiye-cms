package com.shiqiye.cms.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: Category 
 * @Description: 栏目的子分类
 * @author: ASUS
 * @date: 2020年3月3日 下午6:07:23
 */
public class Category implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//主键
	private String name;//栏目名称
	private Integer channelId;//所属栏目ID
	private Integer sorted;//排序
	
	private Channel channel;//文章栏目

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getSorted() {
		return sorted;
	}

	public void setSorted(Integer sorted) {
		this.sorted = sorted;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	
	
}
