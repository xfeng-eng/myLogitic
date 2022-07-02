// pages/orderDetail/orderDetail.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    order:{},
    addressArray:[],
    addressId:[],
    index1:-1,
    index2:-1,
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
    var that = this;
    that.setData({
      addressArray:app.globalData.addressArray,
      addressId:app.globalData.addressId,
    });
    if(options.oId){
      wx.request({
        url: app.globalData.host+'order/findOrder',
        data:{
          "oId":options.oId
        },
        success:function(res){
          that.setData({
            order:res.data.Order
          })
          var sendAddressId = res.data.Order.sendAddressId;
          var receiveAddressId = res.data.Order.receiveAddressId;
          for(var i=0;i<app.globalData.addressId.length;i++){
            if(app.globalData.addressId[i]==sendAddressId){
              that.setData({
                index1:i
              })
            }
            if(app.globalData.addressId[i]==receiveAddressId){
              that.setData({
                index2:i
              })
            }
          }
        }
      })
    }
    else{
      wx.navigateBack({
        delta: 1,
      })
    }
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
  formSubmit:function(e){
    var id = this.data.order.oId;
    if(id){
      wx.request({
        url: app.globalData.host+'order/updateOrderToCancelled?oId='+id,
        success:function(res){
          setTimeout(function(){
            wx.showToast({
                title: '取消成功',
                icon:'success',
                duration:1500
              })
          },500)
          wx.switchTab({
            url: '../../pages/myOrders/myOrders',
          })
        }
      })
    }
  }
})