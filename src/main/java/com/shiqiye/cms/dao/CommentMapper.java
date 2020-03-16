package com.shiqiye.cms.dao;

import java.util.List;

import com.shiqiye.cms.bean.Article;
import com.shiqiye.cms.bean.Comment;

public interface CommentMapper {
	
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
	List<Comment> selects(Article article);
	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: 让评论数增加1
	 * @param articleId
	 * @return
	 * @return: int
	 */
	int updateArticle(Integer articleId);
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 按照文章评论数量排序
	 * @return
	 * @return: List<Comment>
	 */
	List<Article> selectsByCommentNum();
}
