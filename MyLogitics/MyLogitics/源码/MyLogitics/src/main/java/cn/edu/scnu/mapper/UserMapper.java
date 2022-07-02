package cn.edu.scnu.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.scnu.entity.User;

public interface UserMapper {
	//查找用户信息
	User searchUser(String userId);
	//注册检查用户名是否重复
	User checkUserName(String username);
	//员工，地方负责人，老板
	User login(User user);
	//员工，地方负责人注册
	int register(User user);
	//老板》地方负责人》员工  修改用户信息
	int modifyUserInfo(User user);
	//老板》地方负责人》员工 删除用户
	int deleteUser(String userId);
	//查找所有用户
	List<User> findAllUser(@Param("curr") int curr,@Param("limit") int limit);
	
	
	
	
	

	

}
