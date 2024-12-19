<template>
  <div>
    <div style="text-align: center;margin-top: 100px">
      <span
          style="border-left: 6px solid rgb(1, 237, 203);padding-left: 10px;font-size: 30px;font-weight: 700">预约座位</span>
    </div>

    <div style="display:flex;align-items: center;justify-content: center;margin-top: 100px">
      <div>起始时间:</div>
      <div class="prompt-text"> 2020年11月20日 12:12:12</div>
      <div>关闭时间:</div>
      <div class="prompt-text">2020年11月21日 12:12:12</div>
      <div style="background: rgb(253,226,226);color: rgb(245,108,108);padding: 8px;border-radius: 15px">1 号自习室</div>
    </div>

    <div style="display:flex;margin-top: 100px;">
      <!--      左边的选择座位-->
      <div style="display: flex;padding-left:400px;flex-wrap: wrap;cursor: pointer;flex: 80%">
        <div :class="[index === currentIndex ? 'select-class' : 'class-item']" @click="selectClass(index)"
             v-for="(item,index) in classData">
          <div v-if="item.state === 1" style="background: rgb(208,0,0);width: 100%;height: 100%;border-radius: 15px;display: flex;justify-content: center;align-items: center">{{ index + 1 }}</div>
          <div v-else>{{ index + 1 }}</div>
        </div>
      </div>

      <!--      右边的选择信息-->
      <div style="flex: 20%;">
        <div>选择的座位：</div>
        <div style="margin-top: 20px;display: flex">
          <div style="border: 1px solid rgb(148,148,148);font-weight:700; background:rgb(247,247,247); padding: 10px">
            {{ currentIndex + 1 }} 号
          </div>
        </div>
        <div style="margin-top: 20px">
          <el-button type="primary" @click="selectSeat">确认选座</el-button>
        </div>
        <div style="margin-top: 30px"><span
            style="padding: 10px;border-radius:10px;background: rgb(148,148,148);color:rgb(148,148,148) ">1</span>空闲
        </div>
        <div style="margin-top: 30px"><span
            style="padding: 10px;border-radius:10px;background: rgb(208,0,0);color: rgb(208,0,0)">1</span>有人
        </div>
        <div style="margin-top: 30px"><span
            style="padding: 10px;border-radius:10px;background: rgb(0,183,12);color:rgb(0,183,12) ">1</span>选中
        </div>
      </div>

    </div>

  </div>
</template>

<script>
import {getClassById,checkedSeat} from "../../network";

export default {
  name: "CheckClass",
  data() {
    return {
      classData: {},
      currentIndex: -1,
      cid :''
    }
  },
  methods: {
    selectClass(index) {
      this.currentIndex = index
    },
    selectSeat(){
      const data = {}
      data.sid = this.classData[this.currentIndex].id
      data.cid = this.cid

      checkedSeat(data).then(res => {
        if (res.code === 2000){
          this.$message({
            message: '预约成功',
            type: 'success'
          });
        }else if (res.code === 20000){
          this.$message.error(res.msg);
        }else{
          this.$message.error("预约失败");
        }

        getClassById(this.cid).then(res => {
          this.classData = res.data
        })
      })
    }
  },
  created() {
    const id = this.$route.params.id
    this.cid = id
    getClassById(id).then(res => {
      this.classData = res.data
    })
  }
}
</script>

<style scoped>

.select-class {
  border-radius: 13px;
  color: white;
  min-height: 50px;
  max-height: 50px;
  background: rgb(0, 183, 12);
  width: 9%;
  margin: 0 5px 5px 0;
  display: flex;
  justify-content: center;
  align-items: center;

}

.class-item {
  border-radius: 13px;
  color: white;
  min-height: 50px;
  max-height: 50px;
  background: rgb(148, 148, 148);
  width: 9%;
  margin: 0 5px 5px 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.prompt-text {
  margin: 0 10px;
  padding: 8px;
  background: rgb(179, 216, 255);
  color: rgb(83, 168, 255);
  border-radius: 15px
}
</style>