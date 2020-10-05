import Dialog from '@vant/weapp/dialog/dialog';

Page({
  data: {
    custom_data:{"backText":"主页","content":"全局数据管理"},
    fileList: [],

    user:{name:'',msg:'',imagepath:''},
  },
  update_user_name:function(e){
    var user = this.data.user
    user.name = e.detail
    this.setData({
      user:user
    })
  },
  update_user_msg:function(e){
    var user = this.data.user
    user.msg = e.detail
    this.setData({
      user:user
    })
  },
  onShow:function(){
    var user = wx.getStorageSync('user') || {name:'',msg:'',imagepath:''}
    
    //填充图片上传
    if(user.imagepath!='')
      this.setData({
        fileList:[{"name":null,"url":user.imagepath}]
      })

    this.setData({
      user:user
    })
  },
  save:function(){
    var user = this.data.user

    //图片的临时路径转换为存储路径 存储图片
    var that = this
    if(this.data.fileList.length>0){ //有头像的保存
      wx.saveFile({
        tempFilePath:this.data.fileList[0].url,
        success:function(res){
          var savedFilePath = res.savedFilePath;
          console.log(savedFilePath); 
          
          user.imagepath = savedFilePath
          wx.setStorageSync('user', user)
        },
        fail:function(res){
          wx.setStorageSync('user', user)
        }
      });
    }
    else{ //无头像的保存
      if(user.imagepath!=''){
        wx.removeSavedFile({
          filePath: user.imagepath,
          complete (res) {
            console.log('已删除图片')
          }
        })
      }
      user.imagepath=''
      wx.setStorageSync('user', user)
    }

    wx.showToast({
      title: '保存成功',
      icon:'none',
    })
  },
  afterRead_delete:function(){
    this.setData({fileList:[]})
  },
  afterRead:function(e){
    var path = e.detail.file.path
    this.setData({fileList:[{"name":null,"url":path}]})
  },
  onClick_updateSpendLogsValues:function(){
    wx.showLoading({
      title: '优化花支日志金额格式中...',
      mask:true,
    })

    var spendLogs = wx.getStorageSync('spendLogs') || []
    for(var i in spendLogs){
      for(var j in spendLogs[i].datas){
        spendLogs[i].datas[j].value = parseFloat(spendLogs[i].datas[j].value)
      }
    }
    wx.setStorageSync('spendLogs', spendLogs)

    wx.hideLoading({
      complete: (res) => {},
    })
    wx.showToast({
      icon:'none',
      title: '优化完毕',
    })
  },
  onClick_clear_tasks:function(){
    Dialog.confirm({
      title: '清空',
      message: '删除任务日志所有数据!?'
    }).then(() => {
      // on confirm
      var tasks = wx.getStorageSync('tasks') || []
      for(var i in tasks)
        if(i!=0)
          tasks.splice(i,1)
      wx.setStorageSync('tasks', tasks)

      wx.showToast({
        icon:'none',
        title: '已清空任务日志',
      })
    }).catch(() => {
      // on cancel
    });
  },
  onClick_clearAll:function(){
    Dialog.confirm({
      title: '清空',
      message: '删除所有数据!?\n删除所有数据!?\n删除所有数据!?'
    }).then(() => {
      // on confirm
      wx.clearStorageSync()
      app.onLaunch()

      wx.showToast({
        icon:'none',
        title: '已清空所有数据',
      })
    }).catch(() => {
      // on cancel
    });
  },
  onClick_clear_spendLogs:function(){
    Dialog.confirm({
      title: '清空',
      message: '删除花支日志所有数据!?'
    }).then(() => {
      // on confirm
      wx.removeStorageSync('spendLogs')

      wx.showToast({
        icon:'none',
        title: '已清空花支日志',
      })
    }).catch(() => {
      // on cancel
    });
  },
})