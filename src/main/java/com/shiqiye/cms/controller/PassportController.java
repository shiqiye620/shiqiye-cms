package com.shiqiye.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiqiye.cms.bean.User;
import com.shiqiye.cms.service.UserService;
import com.shiqiye.cms.util.CMSException;
import com.shiqiye.cms.util.Result;
/**
 * 
 * @ClassName: PassportController 
 * @Description: 系统注册登录入口
 * @author: ASUS
 * @date: 2020年3月11日 上午10:54:37
 */
@RequestMapping("passport")
@Controller
public class PassportController {
	@Resource
	private UserService userService;
	/**
	 * 
	 * @Title: reg 
	 * @Description: 去注册
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}
	/**
	 * 
	 * @Title: reg 
	 * @Description: 执行注册
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@PostMapping("reg")
	@ResponseBody
	public Result<User> reg(User user) {
		Result<User> result = new Result<User>();
		try {
			if(userService.insert(user)>0) {//如果注册成功
				result.setCode(200);
				result.setMsg("注册成功");
			}
		} catch (CMSException e) {//注册失败 自定义异常
			result.setCode(300);
			result.setMsg("注册失败"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();//将异常消息打印
			result.setCode(500);//注册失败,不可预知异常
			result.setMsg("注册失败,系统出现不可预知异常,请联系管理员");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 去登录-普通用户
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 执行登录
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@PostMapping("login")
	@ResponseBody
	public Result<User> login(User formUser,Model model,HttpSession session) {
		Result<User> result = new Result<User>();
		try {
			//去登录,如果成功返回用户基本信息
			User user = userService.login(formUser);
			if(null!=user) {
				result.setCode(200);
				result.setMsg("登录成功");
				if(user.getRole()==0) {
					session.setAttribute("user",user);//登录成功把用户存贷session
				}else {
					session.setAttribute("admin",user);//登录成功把用户存贷session
				}
			}
		} catch (CMSException e) {//登录失败 自定义异常
			result.setCode(300);
			result.setMsg("登录失败"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();//将异常消息打印
			result.setCode(500);//登录失败,不可预知异常
			result.setMsg("登录失败,系统出现不可预知异常,请联系管理员");
		}
		return result;
	}
	
	@GetMapping("loginout")
	public String loginout(HttpSession session) {
		//清除session
		session.invalidate();
		return "redirect:/";//回到系统首页
	}
	/**
	 * 
	 * @Title: checkName 
	 * @Description: 检查注册用户是否存在
	 * @param username
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("checkName")
	public boolean checkName(String username) {
		return userService.selectByUsername(username)==null;
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 去登录-管理员
	 * @return
	 * @return: String
	 */
	@GetMapping("admin/login")
	public String adminLogin() {
		return "passport/adminLogin";
	}
}
