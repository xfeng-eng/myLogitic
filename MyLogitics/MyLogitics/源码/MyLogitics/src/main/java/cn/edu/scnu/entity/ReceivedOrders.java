package cn.edu.scnu.entity;



//状态模式：5已收货RecceivedOrders
public class ReceivedOrders extends Orders {
	
	@Override
	public void ChangeOrdersState(){
		String state = "Recceived";
		super.setState(state);
		
	

	}
	
	
	@Override
	public void ToCancelledOrders(){
		
	}

	public ReceivedOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReceivedOrders(Orders newOrder) {
		super(newOrder);
		// TODO Auto-generated constructor stub
	}
	
}
