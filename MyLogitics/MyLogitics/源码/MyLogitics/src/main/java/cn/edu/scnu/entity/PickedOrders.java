package cn.edu.scnu.entity;
//状态模式：2已提货PickedOrders
public class PickedOrders extends Orders {
	
	@Override
	public void ChangeOrdersState(){
		String state = "Loaded";
		super.setState(state);

	}
	
	@Override
	public void ToCancelledOrders(){
		
	}

	public PickedOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PickedOrders(Orders newOrder) {
		super(newOrder);
		// TODO Auto-generated constructor stub
	}
	
}
