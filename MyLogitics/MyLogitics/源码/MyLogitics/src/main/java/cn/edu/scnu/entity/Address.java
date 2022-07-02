package cn.edu.scnu.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(value="地址",description="地址")
public class Address {
	private Integer addressId;
	private String name;
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(Integer addressId, String name) {
		super();
		this.addressId = addressId;
		this.name = name;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
