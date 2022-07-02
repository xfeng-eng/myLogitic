package cn.edu.scnu.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.edu.scnu.aop.TokenService;
import cn.edu.scnu.entity.User;
import cn.edu.scnu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api("UserController相关的api")
public class UserController {
	@Autowired
	private UserService userService;	
	@Autowired
	private TokenService tokenService;	
	
	
//	@ApiResponses({ 
//		@ApiResponse(code = 200, message = "成功"),
//		@ApiResponse(code = 403, message = "操作失败"),
//		@ApiResponse(code = 404, message = "请求失败网页不存在"),
//		@ApiResponse(code = 500, message = "服务器未知错误"),
//		@ApiResponse(code = 503, message = "服务器过载或者维护"),
//		
//		})
	//@RequestMapping(value="user/query/point", method = {RequestMethod.POST,RequestMethod.GET})
	
	@ApiOperation(value="查找员工信息",notes = "根据uid查找员工的信息")
	@RequestMapping(value="/searchUser",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchUser(@RequestParam("uId") String userId){
		
		String userId2 = userId.replace("\"", "");
		System.out.println(userId2);
		User user= userService.searchUser(userId2);
		Map<String,Object> map = new HashMap<>();
		if(user!=null){
			map.put("code", "200");
			map.put("message", "根据uid查找员工的信息成功");
			map.put("user", user);
		} else{
			map.put("code", "403");
			map.put("message", "根据uid查找员工的信息失败user为空");	
		}
		
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}

	@ApiOperation(value="检查员工名是否重复",notes = "根据username检查员工名是否重复")
	@RequestMapping(value="/checkUserName",method={RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public ModelAndView checkUserName(String username){
		Map<String,Object> map = new HashMap<>();	
		if(userService.checkUsername(username)){
			map.put("code", "403");
			map.put("message", "员工名已被注册!");			
		}			
		else{
			map.put("code", "200");
			map.put("message", "员工名可以使用");
		}
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}
	
	@ApiOperation(value="员工登录",notes = "根据username和password检查数据库中是否存在员工信息已经是否正确")
	@RequestMapping(value="/login",method={RequestMethod.POST,RequestMethod.GET},produces="application/json")
	@ResponseBody
	public ModelAndView Login(@RequestBody User user,HttpSession session){
		System.out.print(user.getUserName()+":"+user.getPassWord());
		User muser=userService.login(user);
		Map<String,Object> map = new HashMap<>();
		
		if(muser!=null){			
		    String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		    
			session.setAttribute("loginName", muser.getUserName());
			System.out.print(muser.getUserName());
			tokenService.insertToken(user.getUserName(),uuid);
			map.put("code", "200");
			map.put("token", uuid);
			
			map.put("message", "登录成功");			
		}else{
			map.put("code", "403");
			map.put("message", "登录失败,账号或者密码不正确");
					
		}
		return new ModelAndView(new MappingJackson2JsonView(),map);
		
	}
	
	@ApiOperation(value="查找所有用户",notes = "查找所有")
	@RequestMapping(value="/findAllUser",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView findAllUser(@RequestParam("curr") String curr,@RequestParam("limit") String limit){				
		//c=当前页，limit=每页数量
		int c = Integer.parseInt(curr);
		int l = Integer.parseInt(limit);
		c=(c-1)*l;
		//select * from user limit #{curr},#{limit}; 
		List<User> allUser = userService.findAllUser(c,l);
		Map<String,Object> map = new HashMap<>();
		if(allUser!=null){
			map.put("code", "200");
			map.put("message", "分页查找成功");
			map.put("allUserList", allUser);
		}else {
			map.put("code", "403");
			map.put("message", "分页查找失败");
		}
		
		return  new ModelAndView(new MappingJackson2JsonView(),map);		
	
	}
	
	
	
	@ApiOperation(value="修改员工信息",notes = "修改员工信息")
	@RequestMapping(value="/modifyUserInfo",method={RequestMethod.POST,RequestMethod.GET})
	
	public ModelAndView modifyUserInfo(@RequestBody User user){				
		Map<String,Object> map = new HashMap<>();
		System.out.print(user.getUserName());
		if(userService.modifyUserInfo(user)>0){			
			map.put("code", "200");
			map.put("message", "修改员工信息成功");
				
		}else{			
			map.put("code", "403");
			map.put("message", "修改员工信息失败");	
				
		}
		return  new ModelAndView(new MappingJackson2JsonView(),map);	
	}
	
	@ApiOperation(value="添加员工信息",notes = "上级可添加权限低于自身的员工及相关信息（老板》地方负责人》员工）")
	@RequestMapping(value="/register",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView register(@RequestBody User user){
		Map<String,Object> map = new HashMap<>();
		if(userService.register(user)>0){	
			map.put("code", "200");
			map.put("message", "添加员工信息成功");
			
		}else{		
			map.put("code", "403");
			map.put("message", "添加员工信息失败");			
		}
		return  new ModelAndView(new MappingJackson2JsonView(),map);	
	}
	
	@ApiOperation(value="删除员工信息",notes = "上级可删除下级员工账号信息")
	@RequestMapping(value="/delete",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView delete(@RequestParam("userId") String userId){
		
		String userId2=userId.replace("\"", "");
	
		Map<String,Object> map = new HashMap<>();	
		if(userService.delete(userId2)>0){
			map.put("code", "200");
			map.put("message", "员工userId:"+userId2+",信息删除成功!");			
		}			
		else{
			map.put("code", "403");
			map.put("message", "员工userId:"+userId2+",信息删除失败!");
		}
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}
	
	
	//使用httpServletRequest和httpServletResponse来接受get，post请求，
//	@ApiOperation(value="检查员工名是否重复",notes = "根据username检查员工名是否重复")
//	@RequestMapping(value="/checkUserName",method={RequestMethod.POST,RequestMethod.GET})
//	public void checkUserName(HttpServletRequest request,HttpServletResponse response)
//			throws IOException{
//		String username=request.getParameter("username");
//		if(userService.checkUsername(username)){
//			response.getWriter().print("员工名"+username+"已被注册!");
//			System.out.print("已被注册!");
//		}
//			
//		else{
//			response.getWriter().print("恭喜您,"+username+"可以使用!");
//			System.out.print("可以使用!");
//		}
//		
//	}
	
}
