package cn.edu.scnu.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scnu.entity.User;
import cn.edu.scnu.mapper.UserMapper;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User searchUser(String userId) {	
		return userMapper.searchUser(userId);
	}
	
	@Override
	public Boolean checkUsername(String username) {
		User us = userMapper.checkUserName(username);
		if(us !=null){
			return true;
		}else
			return false;
		
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userMapper.login(user);
	}

	
	@Override
	public int register(User user) {
		return userMapper.register(user);
	}

	@Override
	public int modifyUserInfo(User user) {
		return userMapper.modifyUserInfo(user);
		
	}

	@Override
	public int delete(String userId) {
		return userMapper.deleteUser(userId);
	}

	@Override
	public List<User> findAllUser(int curr,int limit) {
		 
		return userMapper.findAllUser(curr,limit);
		
	}
	
	
}
