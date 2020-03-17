package com.shiqiye.cms.controller;
/**
 * 
 * @ClassName: IndexController 
 * @Description: 今日头条主页面
 * @author: ASUS
 * @date: 2020年3月9日 上午11:24:05
 */

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.Article;
import com.shiqiye.cms.bean.Category;
import com.shiqiye.cms.bean.Channel;
import com.shiqiye.cms.bean.Collect;
import com.shiqiye.cms.bean.Comment;
import com.shiqiye.cms.bean.Slide;
import com.shiqiye.cms.bean.User;
import com.shiqiye.cms.dao.CollectMapper;
import com.shiqiye.cms.service.ArticleService;
import com.shiqiye.cms.service.ChannelService;
import com.shiqiye.cms.service.CommentService;
import com.shiqiye.cms.service.SlideService;

@Controller
public class IndexController {
	@Resource
	private ChannelService channelService;
	@Resource
	private ArticleService articleService;
	@Resource
	private SlideService slideService;
	@Resource
	private CommentService commentService;
	@Resource
	private CollectMapper collectMapper;
	@RequestMapping(value = { "", "/", "index" })
	public String list(Model m, Article article,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		//封装查询条件
		article.setStatus(1);//只显示审核过的文章
		article.setDeleted(0);//只显示未删除
		m.addAttribute("article", article);
		// 查询左侧栏目
		List<Channel> channels = channelService.selects();
		m.addAttribute("channels", channels);
		// 如果栏目ID 不为空则查查其下所有的分类
		if (article.getChannelId() != null) {
			List<Category> categorys = channelService.selectsByChannelId(article.getChannelId());
			m.addAttribute("categorys", categorys);
		}
		
		if(article.getChannelId()==null) {
			article.setHot(1);
			List<Slide> slides = slideService.selects();
			m.addAttribute("slides", slides);
		}
		//查询所有文章支持分页
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		m.addAttribute("info", info);
		
		//在右侧显示最新十篇文章
		Article article2 = new Article();
		article2.setStatus(1);
		article2.setDeleted(0);
		PageInfo<Article> lastArticles = articleService.selects(article2, 1, 10);
		m.addAttribute("lastArticles", lastArticles);
		return "index/index";
	}
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("articleDetail")
	public String articleDetail(HttpSession session,Model m,Integer id,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize) {
		Article article = articleService.select(id);
		m.addAttribute("article", article);
		//查寻出当前文章评论信息
		PageInfo<Comment> info = commentService.selects(article, page, pageSize);
		//查询所有文章评论
		PageInfo<Article> info2 = commentService.selectsByCommentNum(1, 10);
		//查询该文章是否被收藏过
		User user = (User) session.getAttribute("user");
		
		Collect collect=null;
		if(null!=user) {//如果用户已经登录,则查询用户是否已经被收藏过
			collect=collectMapper.selectByTitleAndUserId(article.getTitle(), user.getId());
		}
		
		m.addAttribute("collect", collect);
		m.addAttribute("info", info);
		m.addAttribute("info2", info2);
		return "index/articleDetail";
	}
	
	/**
	 * 
	 * @Title: addComment 
	 * @Description: 增加评论
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("addComment")
	public boolean addComment(Comment comment,Integer articleId,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null==user)
			return false;//没有登录的用户不能评论
		comment.setUserId(user.getId());
		comment.setArticleId(articleId);
		comment.setCreated(new Date());
		return commentService.insert(comment)>0;
		
	}
	
	/**
	 * 
	 * @Title: collect 
	 * @Description: 增加收藏
	 * @param comment
	 * @param articleId
	 * @param session
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("collect")
	public boolean collect(Collect collect,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null==user)
			return false;//没有登录的用户不能收藏
		collect.setUser(user);
		collect.setCreated(new Date());
		return collectMapper.insert(collect)>0;
	}
	
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 删除文章
	 * @param collect
	 * @param session
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("deleteCollect")
	public boolean deleteCollect(Integer id) {
		return collectMapper.delete(id)>0;
	}
	
	@ResponseBody
	@RequestMapping("updateHits")
	public boolean updateHits(Integer id) {
		//文章点击量修改
		return articleService.updateHits(id)>0;
	}
}
