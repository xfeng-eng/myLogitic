// pages/changeOrder/changeOrder.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    oId:""
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
  oIdChange(e){
    this.setData({
      oId:e.detail.value
    })
  },
  searchOrder(){
    var that = this;
    if(app.checkLogin()){
      var oId = that.data.oId;
      if(oId)
      wx.request({
        url: app.globalData.host+'order/findOrder',
        data:{
          "oId":that.data.oId
        },
        success:function(res){
            if(res.data.code==200){
              app.globalData.order=res.data.Order
              wx.navigateTo({
                url: '../../pages/orderDetail/orderDetail',
              })
            }
            else{
              wx.showToast({
                title: '单据不存在！',
                icon:'error',
                duration:1500
              })
            }
        }
      })
      
    }
    

  },
  scanAndSearch(e){
    var that = this;
    if(app.checkLogin()){
      wx.scanCode({
        onlyFromCamera: true,
        success(res) {
            try{
              var result =JSON.parse(res.result).xjwl_oid;
              that.setData({
                oId:result
              })
              that.searchOrder()
            }
            catch(error){
              wx.showToast({
                title: '数据格式错误',
                icon:'error',
                duration:2000
              })
              console.log(result);
            }
            
        },
        fail(res){
          wx.showToast({
            title: '扫码失败',
            icon:'error',
            duration:200
          })
        }
      })
    }
    
  }
})