package cn.edu.scnu.entity;


import java.util.Date;

//状态模式：4已送达DeliveredOrders
public class DeliveredOrders extends Orders {
	
	@Override
	public void ChangeOrdersState(){
		String state = "Recceived";
		super.setState(state);
		
		Date receivedTime  =new Date();
		super.setReceivedTime(receivedTime);

	}

	@Override
	public void ToCancelledOrders(){
		
	}
	
	
	public DeliveredOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveredOrders(Orders newOrder) {
		super(newOrder);
		// TODO Auto-generated constructor stub
	}
	
}
