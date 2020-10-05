import * as echarts from '../../ec-canvas/echarts';
var util = require('../../../pages/public/public')

var pie_chart = null;
var income_pie_chart = null;
var column_chart = null;

//柱状图初始化
function initChart_column(canvas, width, height) {
  column_chart = echarts.init(canvas, null, {
    width: width,
    height: height
  });
  canvas.setChart(column_chart);

  var option = {
    title:{
      text:"本月每日花支收入情况"
    },
    color: ['#E64340','#09BB07'],
    grid: {
      left: 0,
      right: 0,
      bottom: 15,
      top: 60,
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        axisTick: { show: false },
        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15',16,'17','18','19','20','21','22','23','24','25','26','27','28','29','30','31'],
        axisLine: {
          lineStyle: {
            color: '#999'
          }
        },
        axisLabel: {
          color: '#666'
        }
      }
    ],
    yAxis: [
      {
        type: 'value',
        axisTick: { show: false },
        axisLine: {
          lineStyle: {
            color: '#999'
          }
        },
        axisLabel: {
          color: '#666'
        }
      }
    ],
    tooltip: {
      trigger: 'axis',
      formatter: '{b}日\n{a0}: {c0}\n{a1}: +{c1}',
      axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      }
    },
    series: [
      {
        name: '花支',
        type: 'bar',
        stack:'情况',
        label: {
          normal: {
            show: false,
            position: 'inside'
          }
        },
        data: [],
      },
      {
        name: '收入',
        type: 'bar',
        stack:'情况',
        label: {
          normal: {
            show: false,
            position: 'inside'
          }
        },
        data: [],
      }
    ]
  };

  column_chart.setOption(option);
  return column_chart;
}

//开发区域 开始
  //饼图初始化
  function initChart_pie(canvas, width, height) {
    pie_chart = echarts.init(canvas, null, {
      width: width,
      height: height
    });
    canvas.setChart(pie_chart);

    var option = {
      title: {
          text: '本月各花支类型支出情况',
          left: 'left',
      },
      // tooltip: {
      //     trigger: 'item',
      //     formatter: '{b}: {c}\n{d}%' 
      // },
      visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
              colorLightness: [0, 1]
          }
      },
      series: [
        {
          top:50,
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data:[].sort(function (a, b) { return a.value - b.value; }),
          roseType: 'radius',
          label: {
            show: true,
            formatter: "{b}\n{d}%"
          },
          labelLine: {
              smooth: 0.2,
              length: 10,
              length2: 20
          },
          animationType: 'scale',
          animationEasing: 'elasticOut',
          animationDelay: function (idx) {
              return Math.random() * 200;
          }
        }
      ]
    };
    pie_chart.setOption(option);

    //点击监听事件 暂时在这
    function eConsole(param) {
      var percent = param.percent +'%'
      var value = param.data['value']
      var spendTypeName = param.data['name'] //点击的饼图区块名(花支类名)
      console.log(spendTypeName)
      var spendLogs = wx.getStorageSync('spendLogs') || [] //获取所有花支日志
      console.log(spendLogs)

      //获取当前选择日期
      var pages = getCurrentPages()    
      var currentPage = pages[pages.length - 1]   
      var month_title = currentPage.data.month_title
      //去掉month_title年月日中的日
      var cnt = 0;
      var tmp = "";
      for(var i in month_title){
        if(month_title[i]=='/')
          cnt++;
        if(cnt==2)
          break;

        tmp+=month_title[i];
      }
      month_title = tmp
      console.log(month_title)

      var ret = []
      for(var i in spendLogs){
        if(spendLogs[i].time.indexOf(month_title)>=0){ //时间筛选
          var datas = spendLogs[i].datas
          for(var j in datas){
            if(datas[j].operate==='-' && datas[j].spend_type===spendTypeName){
              ret.push(datas[j])
            }
          }
        }
      }
      //根据value排序一下
      for(var i=0;i<ret.length;i++){
        for(var j=i+1;j<ret.length;j++){
          if(parseFloat(ret[i].value)<parseFloat(ret[j].value)){
            var tmp = ret[j]
            ret[j] = ret[i]
            ret[i] = tmp
          }
        }
      }
      console.log(ret)

      //ret为所需要的数据数组 渲染到前端
      currentPage.setData({
        spendLogs_filter_spend:ret,
        spendDetailWindowName:spendTypeName+": "+value+' '+percent
      })
    }
    pie_chart.on("click", eConsole);
    return pie_chart;
  }

  //收入饼图初始化
  function initChart_income_pie(canvas, width, height) {
    income_pie_chart = echarts.init(canvas, null, {
      width: width,
      height: height
    });
    canvas.setChart(income_pie_chart);

    var option = {
      title: {
          text: '本月各收入类型收入情况',
          left: 'left',
      },
      // tooltip: {
      //     trigger: 'item',
      //     formatter: '{b}: {c}\n{d}%'
      // },
      visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
              colorLightness: [0, 1]
          }
      },
      series: [
        {
          top:50,
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data:[].sort(function (a, b) { return a.value - b.value; }),
          roseType: 'radius',
          label: {
            show: true,
            formatter: "{b}\n{d}%"
          },
          labelLine: {
              smooth: 0.2,
              length: 10,
              length2: 20
          },
          animationType: 'scale',
          animationEasing: 'elasticOut',
          animationDelay: function (idx) {
              return Math.random() * 200;
          }
        }
      ]
    };

    income_pie_chart.setOption(option);

    //点击监听事件 暂时在这
    function eConsole(param) {
      var percent = param.percent +'%'
      var value = param.data['value'] //点击的饼图区块名(花支类名)
      var spendTypeName = param.data['name'] //点击的饼图区块名(花支类名)
      console.log(spendTypeName)
      var spendLogs = wx.getStorageSync('spendLogs') || [] //获取所有花支日志
      console.log(spendLogs)

      //获取当前选择日期
      var pages = getCurrentPages()    
      var currentPage = pages[pages.length - 1]   
      var month_title = currentPage.data.month_title
      //去掉month_title年月日中的日
      var cnt = 0;
      var tmp = "";
      for(var i in month_title){
        if(month_title[i]=='/')
          cnt++;
        if(cnt==2)
          break;

        tmp+=month_title[i];
      }
      month_title = tmp
      console.log(month_title)

      var ret = []
      for(var i in spendLogs){
        if(spendLogs[i].time.indexOf(month_title)>=0){ //时间筛选
          var datas = spendLogs[i].datas
          for(var j in datas){
            if(datas[j].operate==='+' && datas[j].spend_type===spendTypeName){
              ret.push(datas[j])
            }
          }
        }
      }
      //根据value排序一下
      for(var i=0;i<ret.length;i++){
        for(var j=i+1;j<ret.length;j++){
          if(parseFloat(ret[i].value)<parseFloat(ret[j].value)){
            var tmp = ret[j]
            ret[j] = ret[i]
            ret[i] = tmp
          }
        }
      }
      console.log(ret)

      //ret为所需要的数据数组 渲染到前端
      currentPage.setData({
        spendLogs_filter_income:ret,
        incomeDetailWindowName:spendTypeName+": "+value+' '+percent
      })
    }
    income_pie_chart.on("click", eConsole);
    return income_pie_chart;
  }

//更新饼图
function updateChart_pie(datas){
  //获取当前页面对象,重置详细条目
  var pages = getCurrentPages()    
  var currentPage = pages[pages.length - 1]   
  currentPage.setData({
    spendLogs_filter_spend:[]
  })

  if(pie_chart==null)
    return;
    
  var option = pie_chart.getOption()
  option.series[0].data = datas
  pie_chart.setOption(option)
}

//更新收入饼图
function updateChart_income_pie(datas){
  //获取当前页面对象,重置详细条目
  var pages = getCurrentPages()    
  var currentPage = pages[pages.length - 1]   
  currentPage.setData({
    spendLogs_filter_income:[]
  })

  if(income_pie_chart==null)
    return;
    
  var option = income_pie_chart.getOption()
  option.series[0].data = datas
  income_pie_chart.setOption(option)
}
//开发区域 结束

//更新柱状图
function updateChart_column(data_spends,data_incomes){
  if(column_chart==null)
    return;

  var option = column_chart.getOption()
  //0花支 1收入
  option.series[0].data = data_spends
  option.series[1].data = data_incomes
  column_chart.setOption(option)
}

Page({
  data: {
    spendDetailWindowName:'',
    spendLogs_filter_spend:[],
    incomeDetailWindowName:'',
    spendLogs_filter_income:[],

    currentDate: new Date().getTime(),
    minDate: new Date().getTime(),

    ec_column_chart: {
      onInit: initChart_column
    },
    ec_pie_chart: {
      onInit: initChart_pie
    },
    ec_income_pie_chart:{
      onInit: initChart_income_pie
    },

    custom_data:{"backText":"花支日志","content":"统计"},

    show_timeChoose_popup:false,
    canvas_height:400,

    month_spend:0,
    month_income:0,
    month_title:0,
  },
  onClick_loadChart:function(){
    this.onShow()

    wx.showToast({
      icon:'none',
      title: '本月图表加载完毕',
    })
  },
  clear_spendLogs_filter_spend:function(){
    this.setData({
      spendLogs_filter_spend:[]
    })
  },
  clear_spendLogs_filter_income:function(){
    this.setData({
      spendLogs_filter_income:[]
    })
  },
  getTaskLogs_useTime(time){
    var result = []
    var spendLogs = wx.getStorageSync('spendLogs') || []
    for(var i in spendLogs){
      if(spendLogs[i].time.indexOf(time)>=0)
        result.push(spendLogs[i])
    }
    return result;
  },
  fill_month_completed_count(month_taskLogs){
    var month_income = 0
    var month_spend = 0
    for(var i in month_taskLogs){
      month_spend += parseFloat(month_taskLogs[i].day_spend)
      month_income += parseFloat(month_taskLogs[i].day_income)
    }
    month_income = month_income.toFixed(2)
    month_spend = month_spend.toFixed(2)

    this.setData({
      month_income:month_income,
      month_spend:month_spend
    })
  },
  fill_month_title:function(time){
    this.setData({month_title:time})
  },
  update_column_chart:function(month_taskLogs){
    var data_spends=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
    var data_incomes=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
    for(var i in month_taskLogs){
      var time = parseInt(month_taskLogs[i].time.substr(8,2))-1 //数组基0
      data_spends[time]=-(parseFloat(month_taskLogs[i].day_spend).toFixed(2))
      data_incomes[time]=parseFloat(month_taskLogs[i].day_income).toFixed(2)
    }

    //调用页面更新柱状图函数
    updateChart_column(data_spends,data_incomes)
  },
  update_pie_chart:function(month_taskLogs){
    //获取所有类型{默认消费类:0,饮食:0}
    var month_task_types = []
    var task_types = wx.getStorageSync('types') || []
    for(var i in task_types){
      if(task_types[i].type=='-')
        month_task_types[task_types[i].text]=0
    }

    //统计每种类型
    for(var i in month_taskLogs){
      var month_taskLog_datas = month_taskLogs[i].datas
      for(var j in month_taskLog_datas){
        if(month_taskLog_datas[j].spend_type!=null && month_taskLog_datas[j].operate=='-') //不为转账且是消费
        month_task_types[month_taskLog_datas[j].spend_type] += parseFloat(month_taskLog_datas[j].value)
      }
    }

    //拼装datas
    var datas = []
    for(var i in month_task_types){
      month_task_types[i]=month_task_types[i].toFixed(2)
      datas.push({
        name:i,
        value: month_task_types[i]
      })
    }

    //调用页面更新饼图函数
    updateChart_pie(datas)
  },
  update_income_pie_chart:function(month_taskLogs){
    //获取所有类型{默认消费类:0,饮食:0}
    var month_task_types = []
    var task_types = wx.getStorageSync('types') || []
    for(var i in task_types){
      if(task_types[i].type=='+')
        month_task_types[task_types[i].text]=0
    }

    //统计每种类型
    for(var i in month_taskLogs){
      var month_taskLog_datas = month_taskLogs[i].datas
      for(var j in month_taskLog_datas){
        if(month_taskLog_datas[j].spend_type!=null && month_taskLog_datas[j].operate=='+') //不为转账且是收入
        month_task_types[month_taskLog_datas[j].spend_type] += parseFloat(month_taskLog_datas[j].value)
      }
    }

    //拼装datas
    var datas = []
    for(var i in month_task_types){
      month_task_types[i]=month_task_types[i].toFixed(2)
      datas.push({
        name:i,
        value: month_task_types[i]
      })
    }

    //调用页面更新收入饼图函数
    updateChart_income_pie(datas)
  },
  onShow:function(){
    var time = util.formatTime(new Date()).split(' ')[0]
    var yearAndMonth = time.substr(0,7) //2020/02

    //填充时间
    this.fill_month_title(time)
    //获取本月spendLogs
    var month_taskLogs = this.getTaskLogs_useTime(yearAndMonth)
    //填充月收入和月支出
    this.fill_month_completed_count(month_taskLogs)
    //更新柱状图
    this.update_column_chart(month_taskLogs)
    //更新饼图
    this.update_pie_chart(month_taskLogs)
    //更新收入饼图
    this.update_income_pie_chart(month_taskLogs)
  },
  timeChoose:function(e){
    var time = util.formatTime(new Date(e.detail)).split(' ')[0]
    var yearAndMonth = time.substr(0,7)

    //填充时间
    this.fill_month_title(yearAndMonth)
    //获取本月spendLogs
    var month_taskLogs = this.getTaskLogs_useTime(yearAndMonth)
    //填充月收入和月支出
    this.fill_month_completed_count(month_taskLogs)
    //更新柱状图
    this.update_column_chart(month_taskLogs)
    //更新饼图
    this.update_pie_chart(month_taskLogs)
    //更新收入饼图
    this.update_income_pie_chart(month_taskLogs)

    this.onClose_timeChoose_popup()

    wx.showToast({
      icon:'none',
      title: '本月图表加载完毕',
    })
  },
  onInput_timeChoose:function(event) {
    this.setData({
      currentDate: event.detail
    });
  },
  onClick_timeChoose:function(){
    this.setData({show_timeChoose_popup:true,canvas_height:0})
  },
  onClose_timeChoose_popup:function(){
    this.setData({show_timeChoose_popup:false,canvas_height:400})
  },
})