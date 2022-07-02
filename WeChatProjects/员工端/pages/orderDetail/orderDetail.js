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
    this.setData({
      order:app.globalData.order
    })
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
    that.setData({
      order:app.globalData.order,
      addressArray:app.globalData.addressArray,
      addressId:app.globalData.addressId,
    });
    var sendAddressId = this.data.order.sendAddressId;
    var receiveAddressId =this.data.order.receiveAddressId;
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
  checkValidData(num){
    if(num==0 || num=="" || num=="0" || num=='null' || num==null){
      return false;
    }
    return true;
  },
  updateOrder(){
    wx.request({
      url: app.globalData.host+'order/editOrder',
      method:'POST',
      data:JSON.stringify(app.globalData.order),
      dataType:"json",
      header:{
        "tokenName":app.globalData.tokenName,
        "loginName":encodeURIComponent(app.globalData.user.userName) 
      },
      success:function(res){
        wx.showToast({
          title: '修改成功',
          icon:'success',
          duration:1500
        })
      },
      fail:function(e){
        console.log(e)
        console.log(app.globalData.order)
      }
    })
  },
  nextState(){
    var oId = this.data.order.oId;
    wx.request({
      url: app.globalData.host+'order/updateOrder?oId='+oId,
      header:{
        "tokenName":app.globalData.tokenName,
        "loginName":encodeURIComponent(app.globalData.user.userName) 
      },
      success:function(res){
        console.log(res)
        if(res.data.code=="200"){
          setTimeout(function(){
            wx.showToast({
              title: '状态更新成功',
              icon:'success',
              duration:1500
            })
          },1500)
        }
        else{
          setTimeout(function(){
            wx.showToast({
              title: '状态更新失败',
              icon:'error',
              duration:1500
            })
          },1500)
        }
        wx.switchTab({
          url: '../../pages/changeOrder/changeOrder',
        })
      }
    })
  },
  changeState(e){
    if(!app.globalData.isLogin){
      return;
    }
    var valid = this.checkValidData(app.globalData.order.goodsWeight) && this.checkValidData(app.globalData.order.goodsNum) && this.checkValidData(app.globalData.order.goodsVolume) && this.checkValidData(app.globalData.order.price);
    if(app.globalData.order.state=='Placed'){
      if(valid){
        if(app.globalData.order.isPay==0){
          wx.navigateTo({
            url: '../pay/pay?price',
          })
        }
        else{
          this.nextState()
        }
      }else{
        wx.showToast({
          title: '运费,件数,体积，重量都不能为空或0！',
          icon:'none',
          duration:1500
        })
      }
    }
    else{
      this.nextState();
    }
  },
  changeWeight(e){
    app.globalData.order.goodsWeight=parseFloat(e.detail.value);
  },
  changeVolume(e){
    app.globalData.order.goodsVolume=parseFloat(e.detail.value);
  },
  changeNum(e){
    app.globalData.order.goodsNum=parseInt(e.detail.value);
  },
  changePrice(e){
    app.globalData.order.price=parseFloat(e.detail.value);
  }
})