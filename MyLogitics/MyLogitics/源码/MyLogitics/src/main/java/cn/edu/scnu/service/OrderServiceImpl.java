package cn.edu.scnu.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scnu.entity.CancelledOrders;
import cn.edu.scnu.entity.DeliveredOrders;
import cn.edu.scnu.entity.LoadedOrders;
import cn.edu.scnu.entity.Orders;
import cn.edu.scnu.entity.PickedOrders;
import cn.edu.scnu.entity.PlacedOrders;
import cn.edu.scnu.entity.ReceivedOrders;
import cn.edu.scnu.mapper.OrderMapper;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	private Orders newOrder;
	
	//添加运单，直接添加一个新的运单
	@Override
	public int addOrder(Orders newOrder){
		return orderMapper.addOrder(newOrder);
	}
	//老板查看所有运单
	@Override
	public List<Orders> findAllOrder(){
		return orderMapper.findAllOrder( );
	}
	//显示个人运单
	@Override
	public List<Orders> findOrderByOpenId(String openId) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderByOpenId(openId);
	}
	//删除运单唯一oid,根据运单的oid删除运单
	@Override
	public int delOrder(Integer oId) {
		// TODO Auto-generated method stub
		int a = orderMapper.delOrder(oId);
		return a;
	}
	//修改运单
	@Override
	public int updateOrder(Orders newOrder){
		int a = orderMapper.updateOrder(newOrder);
		return a;
	}
	//查找运单
	@Override
	public Orders findOrder(Integer oId) {
		// TODO Auto-generated method stub
		return orderMapper.findOrder(oId);
	}
	//根据下单时间placedTime，查找当天的所有运单
	@Override
	public List<Orders> findOrderByPlacedTime(HashMap <String,Object> map){
		return orderMapper.findOrderByPlacedTime(map);
	}
	//编辑运单，修改全部内容
	@Override
	public int editOrder(Orders newOrder) {
		// TODO Auto-generated method stub
		return orderMapper.editOrder(newOrder);
	}
	
	//状态模型，为环境context获得具体state类
	@Override
	public void setNewOrder(Orders newOrder){
		this.newOrder = newOrder;
	}
	//状态模型，改变state类的状态
	@Override
	public int changeState(){
		Integer isPay = newOrder.getIsPay();
		String state = newOrder.getState();
		switch(state){
		case "Placed":
			//创建已下单具体state类，变更运单状态值
			Orders newOrder2 = new PlacedOrders(newOrder);
			newOrder2.ChangeOrdersState();
			//新建下一个状态类
			Orders newOrder3 = new PickedOrders(newOrder2);			
			//修改数据库
			updateOrder(newOrder3);
			return 1;
		case "Picked":
			newOrder2 = new PickedOrders(newOrder);
			newOrder2.ChangeOrdersState();
			newOrder3 = new LoadedOrders(newOrder2);			
			updateOrder(newOrder3);
			return 1;
		case "Loaded":
			newOrder2 = new LoadedOrders(newOrder);
			newOrder2.ChangeOrdersState();
			newOrder3 = new DeliveredOrders(newOrder2);			
			updateOrder(newOrder3);
			return 1;
		case "Delivered":
			//判断是否选择立即支付的方式
			if(isPay==1){
				newOrder2 = new DeliveredOrders(newOrder);
				newOrder2.ChangeOrdersState();
				newOrder3 = new ReceivedOrders(newOrder2);			
				updateOrder(newOrder3);
				return 1;
			}else{//选择了货到付款的方式
				System.out.println("请付款");
				return 0;
			}
		case "Recceived":
			newOrder2 = new ReceivedOrders(newOrder);
			newOrder2.ChangeOrdersState();
			newOrder3 = new ReceivedOrders(newOrder2);			
			updateOrder(newOrder3);
			return 1;
		}
		return 0;
	}
	//状态模式，取消运单
	@Override
	public int CancellOrder() {
		String state = newOrder.getState();
		if(state.equals("Placed")){
			Orders newOrder2 = new PlacedOrders(newOrder);
			newOrder2.ToCancelledOrders();
			Orders newOrder3 = new CancelledOrders(newOrder2);			
			updateOrder(newOrder3);
			return 1;
		}else{
			return 0;
		}
		
	}



}
