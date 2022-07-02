// pages/send/send.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderExample:{"oId":"oId","openid":"openid","sender":"sender","senderAddress":"senderAddress","senderPhone":"senderPhone","receiver":"receiver","receiverAddress":"receierAddress","receiverPhone":"receiverPhone","sendAddressId":"sendAddressId","transport":"transport","goodsWeight":"goodsWeight","goodsNum":"goodsNum","insurance":"insurance","price":"price","placedTime":"placedTime","pickedTime":"pickedTime","receivedTime":"receivedTime","state":"state","pay":true},
    orders:[],
    showDialog: false,
    orm:{
      'Placed':'已下单',
      'Picked':'物流公司已收货',
      'Loaded':'已装车',
      'Delivered':'已送达',
      'Recceived':'已签收',
      'Cancelled':'已取消',
      'cancelled':'已取消'
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // this.loadTestData();
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
    if(app.globalData.openid==="not exsits"){
      app.userLogin();
    }
    else{
      this.getOrders();
    }
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

toDetailPage(e){
  console.log(e.currentTarget.dataset.order);
  wx.navigateTo({
    url: '../../pages/orderDetail/orderDetail?oId='+e.currentTarget.dataset.order.oId+'&',
  })
},
getOrders(){
  if(app.globalData.openid==="not exsits"){
    wx.showToast({
      title: '尚未授权登录！',
      icon:'error',
      duration:1500
    })
    return;
  }
  var that = this;
  wx.showLoading({
    title: '正在获取运单',
  })
  wx.request({
    url:app.globalData.host+ 'order/findOrderByOpenId?openId='+app.globalData.openid,
    success:function(res){
      that.setData({
        orders:res.data.orderList
      })
      wx.hideLoading({
        success: (res) => {},
      })
    },
    fail:function(res){
      wx.showToast({
        title: '获取运单失败！',
        icon:"error",
        duration:2000
      })
      wx.hideLoading({
        success: (res) => {},
      })
    }
  });
}
// loadTestData(){
    
//     let orderExample1 = JSON.parse(JSON.stringify(this.data.orderExample));
//     orderExample1.state="placed";
//     let orderExample2 = JSON.parse(JSON.stringify(this.data.orderExample));
//     orderExample2.state="delivered";
//     let orderExample3 = JSON.parse(JSON.stringify(this.data.orderExample));
//     orderExample3.state="loaded";
//     let orderExample4 = JSON.parse(JSON.stringify(this.data.orderExample));
//     orderExample4.state="picked";
//     let orderExample5 = JSON.parse(JSON.stringify(this.data.orderExample));
//     orderExample5.state="canceled";
//     let orderExample6 = JSON.parse(JSON.stringify(this.data.orderExample));
//     orderExample6.state="received";
//     this.setData({
//       orders:[orderExample1,orderExample2,orderExample3,orderExample4,orderExample5,orderExample6]
//     })
// }
})