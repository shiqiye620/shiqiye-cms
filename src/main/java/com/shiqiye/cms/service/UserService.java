package com.shiqiye.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.User;

public interface UserService {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(User user, Integer page, Integer pageSize);
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 注册用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int insert(User user);
	
	/**
	 * 
	 * @Title: selectByUsername 
	 * @Description: 根据用户查询用户是否存在
	 * @param username
	 * @return
	 * @return: User
	 */
	User selectByUsername(String username);
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user);
}
