// pages/user/user.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isLogin:app.globalData.isLogin,
    user:app.globalData.user,
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

  login(e){
    var that = this;
    var user=e.detail.value;
    if(user.userName=="" || user.passWord==""){
      wx.showToast({
        title: '用户名或密码不能为空!',
        icon:"none",
        duration:2000
      })
    }
    else{
      console.log(user);
      wx.request({
        url: app.globalData.host+'user/login',
        data:JSON.stringify(user),
        method:'POST',
        success:function(res){
          var code = res.data.code;
          if(code=="200"){
            wx.showToast({
              title: '登陆成功',
              icon:'success'
            })
            app.globalData.user=user;
            app.globalData.isLogin=true;
            app.globalData.tokenName=res.data.token
            that.setData({
              isLogin:true,
              user:user
            })
          }
          else if(code=="403"){
            wx.showToast({
              title: '用户名或密码错误！',
              icon:'none'
            })
          }
          else{
            wx.showToast({
              title: '未知错误',
              icon:'error'
            })
          }
        },
        fail:function(e){
          wx.showToast({
            title: '登录失败',
            icon:'error'
          })
          console.log(e);
        }
      })
    }
    
  },
  logout(){
    app.globalData.isLogin=false;
    app.globalData.user={};
    this.setData({
        isLogin:false,
        user:{}
    })
  }
})