package cn.edu.scnu.entity;



public class CancelledOrders extends Orders {
	
	@Override
	public void ChangeOrdersState(){


	}

	@Override
	public void ToCancelledOrders(){
		
	}

	public CancelledOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CancelledOrders(Orders newOrder) {
		super(newOrder);
		// TODO Auto-generated constructor stub
	}

}
