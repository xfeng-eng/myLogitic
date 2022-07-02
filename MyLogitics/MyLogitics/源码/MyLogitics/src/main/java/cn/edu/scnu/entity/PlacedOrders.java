package cn.edu.scnu.entity;


import java.util.Date;

//状态模式：1已下单PlacedOrders
public class PlacedOrders extends Orders {
	
	@Override
	public void ChangeOrdersState(){
		String state = "Picked";
		super.setState(state);
		
		Date pickedTime  =new Date();
		super.setPickedTime(pickedTime);


	}

	@Override
	public void ToCancelledOrders(){
		String state = "cancelled";
		super.setState(state);
	}
	
	
	public PlacedOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlacedOrders(Orders newOrder) {
		super(newOrder);
		// TODO Auto-generated constructor stub
	}

}
