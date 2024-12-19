<template>
    <!--为echarts准备一个具备大小的容器dom-->
    <div id="main" style="width: 90%;height: 90%;margin-top: 50px"></div>
</template>
<script>
import * as echarts from 'echarts';
export default {
  name: '',
  data() {
    return {
      charts: '',
      /* opinion: ["1", "3", "3", "4", "5"],*/
      opinionData: ["320", "224", "451", "468", "503"]
    }
  },
  methods: {
    drawLine(id) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['上周座位预约情况']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },

        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ["星期一","星期二","星期二","星期二","星期二"]

        },
        yAxis: {
          type: 'value'
        },

        series: [{
          name: '上周座位预约情况',
          type: 'line',
          stack: '总量',
          data: this.opinionData
        }]
      })
    }
  },
  //调用
  mounted() {
    this.$nextTick(function() {
      this.drawLine('main')
    })
  }
}
</script>
<style scoped>
  * {
      margin: 0;
      padding: 0;
      list-style: none;
    }
</style>
