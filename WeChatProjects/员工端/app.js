// app.js
App({
  onLaunch() {
   
  },
  globalData: {
    user:{},
    order:{},
    isLogin:false,
    host:"http://www.mylogitics.com/",
    address:[],
    addressArray:[],
    addressId:[],
    dataLoaded:false,
    tokenName:null
  },
  checkLogin(){
    if(!this.globalData.isLogin){
      wx.switchTab({
        url: '../../pages/user/user',
      })
      return false;
    }
    else{
      return true;
    }
  }
})
