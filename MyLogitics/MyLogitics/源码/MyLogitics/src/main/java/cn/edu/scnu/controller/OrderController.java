package cn.edu.scnu.controller;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.edu.scnu.entity.Orders;
import cn.edu.scnu.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



//Rest return回去的可能是个json
@CrossOrigin
@RestController
@RequestMapping("/order")
@Api(tags ="运单相关的api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//编辑整个运单
	@RequestMapping(value="/editOrder",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="编辑整个运单",notes="编辑整个运单")
	public ModelAndView editOrder(@RequestBody Orders newOrder){
		//返回的数据
		System.out.println("1");
		Map<String,Object> map = new HashMap<String,Object>();

       

		
		if(orderService.editOrder(newOrder)>0){
			map.put("code", "200");
			map.put("message", "成功");
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}else{
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);		
		}
	}
	

	//添加运单，直接添加一个新的运单
	@RequestMapping(value="/addOrder",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="添加运单",notes="添加运单，直接添加一个新的运单")
	public ModelAndView addOrder(@RequestBody Orders newOrder){
		Date placedTime  =new Date();
		newOrder.setPlacedTime(placedTime);
		String state = "Placed"; 
		newOrder.setState(state);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(orderService.addOrder(newOrder)>0){
			map.put("code", "200");
			map.put("message", "成功");
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}else{
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);		
		}
	}
	
	//修改运单的state
	@RequestMapping(value="/updateOrder",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="修改运单状态",notes="修改运单状态")
	public ModelAndView updateOrder(@RequestParam("oId") Integer oId){		
		//获取完整的信息
		Orders newOrder = findOrder2(oId);
		orderService.setNewOrder(newOrder);
		
		//orderService.changeState()=1表示成功切换状态，0表示失败或者收货时，未付款
		Map<String,Object> map = new HashMap<String,Object>();
		if(orderService.changeState()>0){
			map.put("code", "200");
			map.put("message", "成功");
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}else{
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);		
		}
	}
	
	//取消运单，修改运单的state
	@RequestMapping(value="/updateOrderToCancelled",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="取消运单",notes="取消运单")
	public ModelAndView updateOrderToCancelled(@RequestParam("oId") Integer oId){
		//response.setHeader("Access-Control-Allow-Origin","*");
		//获取完整的信息
		Orders newOrder = findOrder2(oId);
		orderService.setNewOrder(newOrder);
		
		//orderService.changeState()=1表示成功切换状态，0表示失败或者收货时，未付款
		Map<String,Object> map = new HashMap<String,Object>();
		if(orderService.CancellOrder()>0){
			map.put("code", "200");
			map.put("message", "成功");
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}else{
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);		
		}
	}
	
	

	
	
	
	
	//根据下单时间placedTime，查找时间段的所有运单
	@RequestMapping(value="/findOrderByPlacedTime",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="查找时间段的所有运单",notes="查找时间段的所有运单")
	public ModelAndView findOrderByPlacedTime(@RequestParam("beginTimeStr") String beginTimeStr,@RequestParam("endTimeStr") String endTimeStr) throws ParseException{
		//去除前端jsonp传递过来的字符自带的双引号
		String beginTimeStr2=beginTimeStr.replace("\"","");
		String endTimeStr2=endTimeStr.replace("\"","");
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginTime = sf.parse(beginTimeStr2);
		Date endTime = sf.parse(endTimeStr2);
		//新建map1存储两个时间端
		HashMap <String,Object> map1=new HashMap<String,Object>();
		map1.put("beginTime",beginTime);
		map1.put("endTime",endTime);
		
		List<Orders> orderList = orderService.findOrderByPlacedTime(map1);
		//返回的数据
		Map<String,Object> map2 = new HashMap<String,Object>();
		if(null == orderList || orderList.size() ==0){
			map2.put("code", "403");
			map2.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map2);	
		}else{
			map2.put("code", "200");
			map2.put("message", "成功");
			map2.put("orderList",orderList);
			return new ModelAndView(new MappingJackson2JsonView(),map2);
	
		}
	}
	
	//查看所有运单
	@RequestMapping(value="/findAllOrder",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="查看所有运单",notes="查看所有运单")
	public  ModelAndView findAllOrder(){
	
		List<Orders> allOrderList = orderService.findAllOrder();
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == allOrderList || allOrderList.size() ==0){
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);		
		}else{
			map.put("code", "200");
			map.put("message", "成功");
			map.put("allOrderList",allOrderList);
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}
	}
	

	
	//显示个人运单
	@RequestMapping(value="/findOrderByOpenId",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="显示个人运单",notes="显示个人运单")
	public  ModelAndView findOrderByOpenId(@RequestParam("openId") String openId) {
		List<Orders> orderList = orderService.findOrderByOpenId(openId);
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == orderList || orderList.size() ==0){
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);		
		}else{
			map.put("code", "200");
			map.put("message", "成功");
			map.put("orderList",orderList);
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}
	}
	
	
	
	//删除运单唯一oid,根据运单的oid删除运单
	@RequestMapping(value="/delOrder",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="删除运单",notes="根据oId删除运单")
	public ModelAndView delOrder(@RequestParam("oId") Integer oId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(orderService.delOrder(oId)>0){
			map.put("code", "200");
			map.put("message", "成功");
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}else{
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);		
		}
	}
	

	
	//查找单个运单,根据运单号oId,返回完整的运单
	@RequestMapping(value="/findOrder",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value="查找单个运单",notes="查找单个运单")
	public ModelAndView findOrder(@RequestParam("oId") Integer oId) {
		// 这里设置为任意域请求都能获取到数据
		Orders order = orderService.findOrder(oId);		
		Map<String,Object> map = new HashMap<String,Object>();
		if(order.getoId()>0){
			map.put("code", "200");
			map.put("message", "成功");
			map.put("Order",order);
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}else{
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);		
		}
	}

	
	//查找运单方法
	public Orders findOrder2(Integer oId) {
		return orderService.findOrder(oId);
	}
	

	
}
