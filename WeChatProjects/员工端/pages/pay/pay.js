// pages/pay/pay.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    order:app.globalData.order
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
    this.setData({
      order:app.globalData.order
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
  nextState(){
    console.log(app.globalData.user.userName);
    var oId = this.data.order.oId;
    wx.request({
      url: app.globalData.host+'order/updateOrder?oId='+oId,
      header:{
        "tokenName":app.globalData.tokenName,
        "loginName":encodeURIComponent(app.globalData.user.userName)
      },
      success:function(res){
        if(res.data.code==200){
          wx.showToast({
            title: '状态更新成功',
            icon:'success',
            duration:1500
          })
        }
        else{
          console.log(res)
        }
        wx.switchTab({
          url: '../../pages/changeOrder/changeOrder',
        })
      }
    })
  },
  updateOrder(){
    console.log(app.globalData.user.userName);
    console.log(app.globalData.tokenName);
    wx.request({
      url: app.globalData.host+'order/editOrder',
      header:{
        "tokenName":app.globalData.tokenName,
        "loginName":encodeURIComponent(app.globalData.user.userName)
      },
      method:'POST',
      data:JSON.stringify(app.globalData.order),
      dataType:"json",
      success:function(res){
        if(res.data.code==200){
          wx.showToast({
            title: '支付成功',
            icon:'success'
          })
        }
        else{
          console.log(res);
        }
      },
      fail:function(e){
        console.log(e)
        console.log(app.globalData.order)
      }
    })
  },
  pay:function(){
    app.globalData.order.isPay=1;
    this.updateOrder();
    this.nextState();
    wx.navigateBack({
      delta: 2,
    })
  }
})