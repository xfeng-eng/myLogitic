package cn.edu.scnu.aop;


//import com.sxk.entity.Token;
//import com.sxk.service.AuthorityService;
//import com.sxk.service.TokenService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.edu.scnu.entity.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stonegeek on 2017/3/4.
 */
@Component
@Aspect
public class AroundTest {
    @Autowired
    TokenService tokenService;
    @Autowired
    AuthorityService authorityService;
    //@Pointcut(value="execution( * cn.edu.scnu.service.* (. .)) ")
  
    @Pointcut(value = "execution(public * cn.edu.scnu.controller.*.*(..)) "
    		+ "&&!execution(public * cn.edu.scnu.controller.UserController.Login(..))"
    		+ "&&!execution(public * cn.edu.scnu.controller.AddressController.*(..))"
    		+ "&&!execution(public * cn.edu.scnu.controller.OrderController.addOrder(..))"
    		+ "&&!execution(public * cn.edu.scnu.controller.OrderController.findOrderByOpenId(..))"
    		+ "&&!execution(public * cn.edu.scnu.controller.OrderController.findOrder(..))"
    		+ "&&!execution(public * cn.edu.scnu.controller.OrderController.updateOrderToCancelled(..))")
    public void testaround(){}
    
    //value = "cn.edu.scnu.test.PointCuts.aopDemo()"
    @Before(value="cn.edu.scnu.aop.AroundTest.testaround()")
    public void testBefore(){
    	System.out.print("test before ok!");
    }
    
    
    @Around("testaround()")
    public Object test(ProceedingJoinPoint jp) throws Throwable{
		
        System.out.println("开始验证Token权限。。。。");
        HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String tokenName=this.getToken(request);
        Map<String, String[]>maps =  request.getParameterMap();


        //String loginName=this.getLoginName(request);
        
        HttpSession   session   =   request.getSession();    
 //   	String loginName = ((User)session.getAttribute("user")).getUserName();  
           

        
    	String	loginName=getLoginName(request);
    	
        System.out.println("sessionId为："+session.getId());
        System.out.println("loginName为："+loginName);
        
        String url=this.getURL(request);
        
        String method=this.getMethod(request);
        System.out.println("Token为："+tokenName);
        System.out.println("URL为："+url);
        System.out.println("Method为："+method);
        //map用来存返回的code和result
        Map<String,Object> map = new HashMap<>();
        //请求非登录
        if(!url.equals("/user/login")){
        	
        	System.out.println("url不是login时");
        	//登录二次验证
        	Token token=tokenService.findByTokenName(loginName,tokenName);
        	//没登录
            if(token==null){
            	
            	System.out.println("Token not exsits");
            	map.put("code", "403");
        		map.put("message", "Token not exsits");
        		return  new ModelAndView(new MappingJackson2JsonView(),map);
            	
            }
            //从数据库查到的token中校验是否过期
            Timestamp creatTime=token.getCreateTime();
            int len=token.getEffectiveTime();
            Timestamp timeNow=new Timestamp(new Date().getTime());
            List<String> allApi=null;
            if((creatTime.getTime()+len*1000*60)>=timeNow.getTime()){
                //验证API权限
            	allApi=authorityService.getAPI(tokenName);
            	
                System.out.println("allApi:"+allApi);
                System.out.println(allApi!=null);
                
                if(allApi!=null&&allApi.contains(url)){
                    System.out.println("Token验证通过！！！");
                    return jp.proceed();
                }else {
                    System.out.println("验证失败！！！");
                    map.put("code", "403");
            		map.put("message", "No authority for this API!!");
                    return new ModelAndView(new MappingJackson2JsonView(),map);
                }
            }else {
                System.out.println("The Token is Timeout");
                map.put("code", "403");
        		map.put("message", "The Token is Timeout!!");
        		return new ModelAndView(new MappingJackson2JsonView(),map);
            }
        }
    	return jp.proceed();
    }
    public String getToken(HttpServletRequest request){
        return request.getHeader("tokenName");
    }
    public String getLoginName(HttpServletRequest request){
    	String code = request.getHeader("loginName");
    	System.out.println(code);
    	if(code!=null){
    		return java.net.URLDecoder.decode(code);
    	}
    	else{
    		return null;
    	}
    }
    public String getURL(HttpServletRequest request){
        return request.getRequestURI();
    }
    public String getMethod(HttpServletRequest request){
        return request.getMethod();
    }
    

}