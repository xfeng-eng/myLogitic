package cn.edu.scnu.entity;


import java.util.Date;



import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
状态模式：0已取消CancelledOrders 
状态模式：1已下单PlacedOrders（选择立即支付或者货到付款）  下单时间orderTime
状态模式：2已提货PickedOrders    员工拿到货物 
状态模式：3已装车LoadedOrders    
状态模式：4已送达DeliveredOrders
状态模式：5已收货ReceivedOrders 需要判断是否已支付
*/


@ApiModel(value="运单",description="运单")
public class Orders {
	
	private Integer oId;  //运单的key值
	private String openId; //发货人微信小程序用户标识
	private String sender;//发货人昵称
	private String senderAddress;//发货人住址
	private String senderPhone;//发货人联系电话
	private String receiver;
	private String receiverAddress;
	private String receiverPhone;
	private Integer sendAddressId;
	private Integer receiveAddressId;
	private String transport;
	private Double goodsWeight;
	private Double goodsVolume;
	private Double goodsNum;
	private Integer insurance;
	private Double price;//运单总价格
	

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(value = "下单时间",required = false,hidden=true,example = "2021-08-18 20:11:53")
	private Date placedTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(value = "提货时间",required = false,hidden=true,example = "2021-08-18 20:11:53")
	private Date pickedTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(value = "完成时间",required = false,hidden=true,example = "2021-08-18 20:11:53")
	private Date receivedTime;
	
	private Integer isPay;
	@ApiModelProperty(value = "状态模式",required = false,hidden=true,example = "placed")
	private String state;
	
	public Orders(Orders newOrder) {
		super();
		this.oId = newOrder.getoId();
		this.openId = newOrder.getOpenId();
		this.sender = newOrder.getSender();
		this.senderAddress = newOrder.getSenderAddress();
		this.senderPhone = newOrder.getSenderPhone();
		this.receiver = newOrder.getReceiver();
		this.receiverAddress = newOrder.getReceiverAddress();
		this.receiverPhone = newOrder.getReceiverPhone();
		this.sendAddressId = newOrder.getSendAddressId();
		this.receiveAddressId = newOrder.getReceiveAddressId();
		this.transport = newOrder.getTransport();
		this.goodsWeight = newOrder.getGoodsWeight();
		this.goodsVolume = newOrder.getGoodsVolume();
		this.goodsNum = newOrder.getGoodsNum();
		this.insurance = newOrder.getInsurance();
		this.price = newOrder.getPrice();
		this.placedTime = newOrder.getPlacedTime();
		this.pickedTime = newOrder.getPickedTime();
		this.receivedTime = newOrder.getReceivedTime();
		this.isPay = newOrder.getIsPay();
		this.state = newOrder.getState();
	}


	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Orders(Integer oId, String openId, String sender, String senderAddress, String senderPhone, String receiver,
			String receiverAddress, String receiverPhone, Integer sendAddressId, Integer receiveAddressId,
			String transport, Double goodsWeight, Double goodsVolume, Double goodsNum, Integer insurance, Double price,
			Date placedTime, Date pickedTime, Date receivedTime, Integer isPay, String state) {
		super();
		this.oId = oId;
		this.openId = openId;
		this.sender = sender;
		this.senderAddress = senderAddress;
		this.senderPhone = senderPhone;
		this.receiver = receiver;
		this.receiverAddress = receiverAddress;
		this.receiverPhone = receiverPhone;
		this.sendAddressId = sendAddressId;
		this.receiveAddressId = receiveAddressId;
		this.transport = transport;
		this.goodsWeight = goodsWeight;
		this.goodsVolume = goodsVolume;
		this.goodsNum = goodsNum;
		this.insurance = insurance;
		this.price = price;
		this.placedTime = placedTime;
		this.pickedTime = pickedTime;
		this.receivedTime = receivedTime;
		this.isPay = isPay;
		this.state = state;
	}


	public Integer getoId() {
		return oId;
	}


	public void setoId(Integer oId) {
		this.oId = oId;
	}


	public String getOpenId() {
		return openId;
	}


	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getSenderAddress() {
		return senderAddress;
	}


	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}


	public String getSenderPhone() {
		return senderPhone;
	}


	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getReceiverAddress() {
		return receiverAddress;
	}


	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}


	public String getReceiverPhone() {
		return receiverPhone;
	}


	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}


	public Integer getSendAddressId() {
		return sendAddressId;
	}


	public void setSendAddressId(Integer sendAddressId) {
		this.sendAddressId = sendAddressId;
	}


	public Integer getReceiveAddressId() {
		return receiveAddressId;
	}


	public void setReceiveAddressId(Integer receiveAddressId) {
		this.receiveAddressId = receiveAddressId;
	}


	public String getTransport() {
		return transport;
	}


	public void setTransport(String transport) {
		this.transport = transport;
	}


	public Double getGoodsWeight() {
		return goodsWeight;
	}


	public void setGoodsWeight(Double goodsWeight) {
		this.goodsWeight = goodsWeight;
	}


	public Double getGoodsVolume() {
		return goodsVolume;
	}


	public void setGoodsVolume(Double goodsVolume) {
		this.goodsVolume = goodsVolume;
	}


	public Double getGoodsNum() {
		return goodsNum;
	}


	public void setGoodsNum(Double goodsNum) {
		this.goodsNum = goodsNum;
	}


	public Integer getInsurance() {
		return insurance;
	}


	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Date getPlacedTime() {
		return placedTime;
	}


	public void setPlacedTime(Date placedTime) {
		this.placedTime = placedTime;
	}


	public Date getPickedTime() {
		return pickedTime;
	}


	public void setPickedTime(Date pickedTime) {
		this.pickedTime = pickedTime;
	}


	public Date getReceivedTime() {
		return receivedTime;
	}


	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}


	public Integer getIsPay() {
		return isPay;
	}


	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public void ChangeOrdersState() {
		// TODO Auto-generated method stub
		
	}


	public void ToCancelledOrders() {
		// TODO Auto-generated method stub
		
	}






	
	


}
