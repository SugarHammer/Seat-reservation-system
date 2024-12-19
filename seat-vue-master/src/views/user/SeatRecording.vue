<template>
  <div style="padding: 0 200px">
    <div style="text-align: center;margin-top: 100px">
      <span
          style="border-left: 6px solid rgb(1, 237, 203);padding-left: 10px;font-size: 30px;font-weight: 700">预约座位</span>
    </div>
      <div style="margin-top: 100px">
        <el-table
            :data="tableData"
            border
            style="width: 100%">
          <el-table-column
              prop="name"
              label="自习室名称">
            <template slot-scope="scope">
              <div>{{ scope.row.className }}</div>
            </template>
          </el-table-column>
          <el-table-column
              prop="address"
              label="座位号">
            <template slot-scope="scope">
              <div>{{ scope.row.seatNum }}</div>
            </template>
          </el-table-column>
          <el-table-column
              prop="address"
              label="预约时间">
            <template slot-scope="scope">
              <div>{{ scope.row.createTime | dateFormat }}</div>
            </template>
          </el-table-column>
          <el-table-column
              prop="address"
              label="操作">
            <template slot-scope="scope">
              <div style="text-align: center">
                <el-button @click="cancelReservation(scope.row.id)" type="danger" >取消预约</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
  </div>
</template>

<script>
import {getData,cancel} from "../../network/seatRecording";

export default {
  name: "SeatRecording",
  data() {
    return {
      tableData: []
    }
  },
  methods:{
    cancelReservation(id){
      cancel(id).then(res => {
        if (res.code === 2000){
          this.$message({
            message: '取消成功',
            type: 'success'
          });
          getData().then(res => {
            this.tableData = res.data
          })
        } else if (res.code === 20000){
          this.$message.error(res.msg);
        }
      })



    }
  },
  created() {
    getData().then(res => {
      this.tableData = res.data
    })
  }
}
</script>

<style scoped>

</style>