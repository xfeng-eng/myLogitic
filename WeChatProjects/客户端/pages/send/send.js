// pages/send/send.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    addressArray:[],
    index1:-1,
    index2:-1,
    address:[],
    addressId:[],
    transport:"汽运",
    errorMsg:"",
    dataLoaded:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    this.checkLogin();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this;
    wx.request({
      url: app.globalData.host+'address/findAddressList',
      success:function(res){
          var allAddress=res.data.allAddress
          var array = []
          var idArray = []
          for(var i=0;i<allAddress.length;i++){
            array.push(allAddress[i].name)
            idArray.push(allAddress[i].addressId)
          }
          that.setData({
            address:allAddress,
            addressArray:array,
            addressId:idArray,
            dataLoaded:true
          })
          app.globalData.address=allAddress;
          app.globalData.addressArray=array;
          app.globalData.addressId=idArray;
          app.globalData.dataLoaded=true;
      },
      fail:function(res){
        wx.showToast({
          title: '数据获取失败！',
          icon:'error',
          duration:2000
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  userLogin:function(){
    if(app.globalData.openid==="not exsits"){
      app.userLogin();
    }
  },
  checkLogin(){
    if(app.globalData.openid=="not exsits"){
      return false;
    }
    return true;
  },
  checkData(param){
    if(param.sender=="" || param.senderAddress=="" || param.senderPhone=="" || this.data.index1<=-1 || this.data.index2<=-1 || param.receiver=="" || param.receiverAddress=="" || param.receiverPhone=="" || param.transform==""){
      this.setData({
        errorMsg:"除保费外所有数据不能为空！"
      })
      return false;
    }
    else if(param.senderPhone.length!=11){
      this.setData({
        errorMsg:"联系电话格式错误，座机请加区号！"
      })
      return false;
    }
    else if(param.receiverPhone.length!=11){
      this.setData({
        errorMsg:"收货人联系电话格式错误，座机请加区号！"
      })
      return false;
    }
    else if(param.sendAddressId==param.receiveAddressId){
      this.setData({
        errorMsg:"发货地和收货地不能相同！"
      })
      return false;
    }
    this.setData({
      errorMsg:""
    })
    return true;
  },
  loadWholeData:function(data){
    data.state="placed"
    data.isPay=0
    data.sendAddressId=this.data.addressId[this.data.index1];
    data.receiveAddressId=this.data.addressId[this.data.index2];
    data.transport=this.data.transport;
    if(data.insurance==""){
      data.insurance=0;
    }
    return data;
  },
  formSubmit:function(e){
    var that = this;
    if(!that.checkLogin()){
      this.setData({
        errorMsg:"请登陆后操作！"
      })
      that.userLogin();
      return;
    }
    if(!this.data.dataLoaded){
      wx.showToast({
        title: '请等待数据载入',
        icon:'none'
      })
      return;
    }
    let param = e.detail.value;
    param.openId=app.globalData.openid;
    param = this.loadWholeData(param);
    if(!this.checkData(param)){
      return;
    }
    this.send(param)
  },
  send(param){
    var that = this;
    wx.request({
      method:"POST",
      url: app.globalData.host+'order/addOrder',
      dataType:"json",
      data:param,
      success:function(res){
        wx.showToast({
          title: '下单成功',
          icon:'success',
          duration:2000
        })
      },
      fail:function(res){
        console.log(res)
      }
    })
  },
  bindPickerChange1: function(e) {
    this.setData({
      index1: e.detail.value
    })
  },
  bindPickerChange2: function(e) {
    this.setData({
      index2: e.detail.value
    })
  },
  transportChange:function(e){
    this.setData({
      transport:e.detail.value
    })
  }
})