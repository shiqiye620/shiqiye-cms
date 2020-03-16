package com.shiqiye.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiqiye.cms.bean.User;
import com.shiqiye.cms.dao.UserMapper;
import com.shiqiye.cms.service.UserService;
import com.shiqiye.cms.util.CMSException;
import com.shiqiye.cms.util.Md5Util;
import com.shiqiye.common.utils.StringUtil;
@Service
public class UserServiceImpl implements UserService {
	@Resource
	UserMapper userMapper;
	
	@Override
	public PageInfo<User> selects(User user,Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> list = userMapper.selects(user);
		// TODO Auto-generated method stub
		return new PageInfo<User>(list);
	}
	
	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}
	
	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		if(!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		if(!(user.getUsername().length()>=2&& user.getUsername().length()<=10))
			throw new CMSException("用户名的长度为2-10位");
		User findUser = this.selectByUsername(user.getUsername());
		if(null!=findUser) {
			throw new CMSException("用户名已被注册");
		}
		
		if(!StringUtil.hasText(user.getPassword()))
			throw new CMSException("密码不能为空");
		if(!(user.getPassword().length()>=6&& user.getPassword().length()<=10))
			throw new CMSException("密码的长度为6-10位");
		
		if(!StringUtil.hasText(user.getRepassword()))
			throw new CMSException("确认密码不能为空");
		if(!user.getPassword().equals(user.getRepassword()))
			throw new CMSException("两次密码不一致");
		//进行加密处理
		user.setPassword(Md5Util.encode(user.getPassword()));
		//用户的注册信息
		user.setCreated(new Date());
		user.setNickname(user.getUsername());
		user.setLocked("0");//默认用户状态为正常
		return userMapper.insert(user);
	}
	
	@Override
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectByUsername(username);
	}
	

	@Override
	public User login(User user) {
		//1.校验用户名不能为空
		if(!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		//2.检查用户名是否存在
		User u=this.selectByUsername(user.getUsername());
		if(null==u) {
			throw new CMSException("该用户不存在");
		}
		//3.比较密码是否存在 数据库存储的是加密后的密码
		//对登陆的密码进行加密 在和数据库的密码进行比较
		if(!Md5Util.encode(user.getPassword()).equals(u.getPassword())) {
			throw new CMSException("密码不正确,请重新登陆");
		}
		if(u.getLocked().equals("1"))
			throw new CMSException("当前账户被禁用,不能登录");
		return u;
	}
	
	
}
