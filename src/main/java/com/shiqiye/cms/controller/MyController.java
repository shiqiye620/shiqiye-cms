package com.shiqiye.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.Article;
import com.shiqiye.cms.bean.Collect;
import com.shiqiye.cms.bean.User;
import com.shiqiye.cms.dao.CollectMapper;
import com.shiqiye.cms.service.ArticleService;
import com.shiqiye.cms.service.CollectService;

/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: ASUS
 * @date: 2020年3月4日 下午5:35:08
 */
@RequestMapping("my")
@Controller
public class MyController {
	@Resource
	private ArticleService articleService;
	@Resource
	private CollectService collectService;
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入个人中心首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"/","","index"})//提供三种映射
	public String index() {
		return "my/index";
		
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
	 * @Title: articles 
	 * @Description: 我的文章
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,HttpSession session,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		Article article = new Article();
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		model.addAttribute("info", info);
		return "my/articles";
	}
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 我的文章
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(MultipartFile file,Article article,HttpSession session) {
		//文件上传
		if(null!=file && !file.isEmpty()) {
			String path="d:/pic/";
			//文件的原始名字
			String filename = file.getOriginalFilename();
			//为了防止文件重复名字,需要改变文件的名字
			String newfilename = UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			
			File f = new File(path, newfilename);
			
			//把文件写入硬盘
			try {
				file.transferTo(f);
				article.setPicture(newfilename);//文件的名称
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//文章的初始化数据
			User user = (User) session.getAttribute("user");
			
			article.setUserId(user.getId());//发布的用户ID
			article.setCreated(new Date());//默认创建时间
			article.setHits(0);//默认点击量
			article.setDeleted(0);//默认是否删除
			article.setHot(0);//默认非热门
			article.setStatus(0);//默认待审核
		}
		return articleService.insert(article)>0;//增加文章;
	}
	
	/**
	 * 
	 * @Title: collection 
	 * @Description: 收藏文章
	 * @return
	 * @return: String
	 */
	@RequestMapping("collection")
	private String collection(Model m,HttpSession session,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		User user = (User) session.getAttribute("user");
		PageInfo<Collect> info = collectService.selects(user.getId(), page, pageSize);
		m.addAttribute("info",info);
		return "my/collection";
	}
}
