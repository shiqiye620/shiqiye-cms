package com.shiqiye.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.Article;
import com.shiqiye.cms.bean.Comment;

public interface CommentService {
	/**
	 * 
	 * @Title: insert 
	 * @Description: TODO
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 根据文章查寻文章评论
	 * @return
	 * @return: List<Comment>
	 */
	PageInfo<Comment> selects(Article article,Integer page,Integer pageSize);

	/**
	 * 
	 * @Title: selects 
	 * @Description: 按照文章评论数量排序
	 * @return
	 * @return: List<Comment>
	 */
	PageInfo<Article> selectsByCommentNum(Integer page, Integer pageSize);
}
