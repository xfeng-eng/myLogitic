package cn.edu.scnu.service;

import java.util.List;

import cn.edu.scnu.entity.User;

public interface UserService {
	//用户登录功能，返回User类对象 
	public User login(User user);
	
	//检查用户是否已被注册 
	public Boolean checkUsername(String username);
	
	//注册用户
	public int register(User user);
	//查找用户信息
	public User searchUser(String userId);
	//修改员工信息
	public int modifyUserInfo(User user);
	//删除员工信息
	public int delete(String userId);

	public List<User> findAllUser(int curr,int limit);
	

	
	
}
