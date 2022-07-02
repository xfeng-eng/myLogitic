package cn.edu.scnu.mapper;

import java.util.HashMap;
import java.util.List;


import cn.edu.scnu.entity.Orders;

public interface OrderMapper {
	//添加运单，直接添加一个新的运单
	public int addOrder(Orders newOrder);
	//老板查看所有运单
	public List<Orders> findAllOrder();
	//根据微信openId显示个人所有运单
	public List<Orders> findOrderByOpenId(String openId);	
	//删除运单,根据运单的oId删除运单
	public int delOrder(Integer oId);
	//取消运单,修改运单的state
	public int CanlOrder(Integer oId);
	//修改运单,只允许修改运单状态和是否支付
	public int updateOrder(Orders newOrder);
	//编辑运单，修改全部内容
	public int editOrder(Orders newOrder);
	//根据oId,查找单个运单
	public Orders findOrder(Integer oId);
	//根据下单时间placedTime，查找当天的所有运单
	public List<Orders> findOrderByPlacedTime(HashMap <String,Object> map);

}
