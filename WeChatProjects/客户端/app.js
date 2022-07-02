// app.js
App({
  globalData: {
    appid:"wx5bbcf125b7987f4f",
    secrect:"a188b8887137db943ef6b80ab87a6502",
    openid:"not exsits",
    host:"http://www.mylogitics.com/",
    address:[],
    addressArray:[],
    addressId:[],
    dataLoaded:false
  },
  onLaunch() {
    this.userLogin();
  },
  userLogin:function(){
    var that = this;
    wx.login({
      success:function(res){
        if(res.code){
          wx.request({
            url:   'https://api.weixin.qq.com/sns/jscode2session?appid=' + that.globalData.appid + '&secret=' + that.globalData.secrect+'&grant_type=authorization_code&js_code=' + res.code,
            success:function(res){
              if(res.data.openid){
                that.globalData.openid=res.data.openid
                console.log(that.globalData.openid);
              }
              else{
                console.log(res);
              }
            }
          })
        }
      }
    })
  },
})
