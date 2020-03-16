package com.shiqiye.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.Article;
import com.shiqiye.cms.bean.Comment;
import com.shiqiye.cms.dao.CommentMapper;
import com.shiqiye.cms.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	@Resource
	private CommentMapper commentMapper;
	@Override
	public int insert(Comment comment) {
		try {
			//增加评论
			commentMapper.insert(comment);
			//让文章评论数量+1
			commentMapper.updateArticle(comment.getArticleId());
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public PageInfo<Comment> selects(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Comment> list = commentMapper.selects(article);
		return new PageInfo<Comment>(list);
	}

	@Override
	public PageInfo<Article> selectsByCommentNum(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, pageSize);
		List<Article> list = commentMapper.selectsByCommentNum();
		return new PageInfo<Article>(list);
	}

}
