package com.shiqiye.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.Article;
import com.shiqiye.cms.bean.User;
import com.shiqiye.cms.service.ArticleService;
import com.shiqiye.cms.service.UserService;
/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员中心
 * @author: ASUS
 * @date: 2020年3月6日 上午10:00:52
 */
@Controller
@RequestMapping("admin")
public class AdminController {
	@Resource
	private ArticleService articleService;
	
	@Resource
	private UserService userService;
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入管理中心界面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value= {"","/","index"})
	public String index() {
		return "admin/index";
	}
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 进入文章审核列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model m,Article article,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "7")Integer pageSize) {
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		m.addAttribute("info", info);
		m.addAttribute("article", article);
		return "admin/articles";
	}
	
	/**
	 * 
	 * @Title: articelDetail 
	 * @Description: 单个文章内容
	 * @param id
	 * @return
	 * @return: Article
	 */
	@ResponseBody
	@RequestMapping("articelDetail")
	public Article articelDetail(Integer id) {
		return articleService.select(id);
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改文章内容
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("update")
	public boolean update(Article article) {
		return articleService.update(article)>0;
	}
	
	@RequestMapping("users")
	public String users(Model model,User user,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize) {
		PageInfo<User> info=userService.selects(user, page, pageSize);
		model.addAttribute("info", info);
		return "admin/users";
		
	}
	
	/**
	 * 
	 * @Title: updateUser 
	 * @Description: 更新用户信息
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping("updateUser")
	public boolean updateUser(User user) {
		return userService.update(user)>0;
	}
}
