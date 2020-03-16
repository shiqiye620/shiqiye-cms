package com.shiqiye.cms.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: Settings 
 * @Description: 系统配置表
 * @author: ASUS
 * @date: 2020年3月3日 下午6:28:43
 */
public class Settings implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//主键
	private String siteDomain;//域名
	private String siteName;//域名名称
	private Integer article_list_size;//文章分页现实的条目
	private String slide_size;//显示多少个广告
	private String adminUsername;//管理员用户名
	private String adminPassword;//管理员密码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getArticle_list_size() {
		return article_list_size;
	}
	public void setArticle_list_size(Integer article_list_size) {
		this.article_list_size = article_list_size;
	}
	public String getSlide_size() {
		return slide_size;
	}
	public void setSlide_size(String slide_size) {
		this.slide_size = slide_size;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	
}
