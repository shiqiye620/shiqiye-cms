package com.shiqiye.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.Article;

public interface ArticleService {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int insert(Article article);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 文章列表
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);
	
	/**
	 * 
	 * @Title: select
	 * @Description: 单个文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article select(Integer id);
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改文章属性
	 * @param article
	 * @return
	 * @return: int
	 */
	int update(Article article);
	
	/**
	 * 
	 * @Title: updateHits 
	 * @Description: 修改文章点击量
	 * @param article
	 * @return
	 * @return: int
	 */
	int updateHits(Integer id);
}
