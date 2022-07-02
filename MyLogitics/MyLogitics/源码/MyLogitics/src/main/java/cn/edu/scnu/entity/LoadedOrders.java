package cn.edu.scnu.entity;
//状态模式：3已装车LoadedOrders
public class LoadedOrders extends Orders {
	
	@Override
	public void ChangeOrdersState(){
		String state = "Delivered";
		super.setState(state);
		

	}
	
	@Override
	public void ToCancelledOrders(){
		
	}

	public LoadedOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoadedOrders(Orders newOrder) {
		super(newOrder);
		// TODO Auto-generated constructor stub
	}
	
}
