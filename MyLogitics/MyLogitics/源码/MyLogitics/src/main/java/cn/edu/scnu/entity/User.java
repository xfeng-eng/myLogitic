package cn.edu.scnu.entity;

public class User {
	//驼峰命名的封装规则
		private String uId;
		private String userName;
		private String passWord;
		private String permission;
		private String userNickName;
		
		private String phone;
		private Integer addressId;
		private String email;
		private int status;
		 // getter()&setter() 
		
	
	
		public String getuId() {
			return uId;
		}
		public void setuId(String uId) {
			this.uId = uId;
		}
		public String getPassWord() {
			return passWord;
		}
		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
	
		public String getPermission() {
			return permission;
		}
		public void setPermission(String permission) {
			this.permission = permission;
		}
	
		
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
	
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserNickName() {
			return userNickName;
		}
		public void setUserNickName(String userNickName) {
			this.userNickName = userNickName;
		}
		
		public Integer getAddressId() {
			return addressId;
		}
		public void setAddressId(Integer addressId) {
			this.addressId = addressId;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		

}
