<template>
  <div>
    <div style="text-align: center;margin-top: 100px">
      <span
          style="border-left: 6px solid rgb(1, 237, 203);padding-left: 10px;font-size: 30px;font-weight: 700">留言列表</span>
    </div>

    <div style="display:flex;margin-top: 100px;">
      <!--      左边的选择座位-->
      <div style="padding-left:400px;flex: 80%;padding-right: 100px">
        <div style="display: flex;margin-bottom: 30px;border-bottom: 1px solid rgb(204,204,204);padding-bottom: 20px" v-for="item in messages">
          <div style="flex: 10%"><img
              style="width: 38px;height: 38px;border-radius: 50%"
              src="https://tse4-mm.cn.bing.net/th/id/OIP-C.rHuc8SKa0wLVwCqqA27uIwHaEt?w=283&h=180&c=7&r=0&o=5&dpr=1.25&pid=1.7"
              alt=""></div>
          <div style="flex: 90%">
            <div> {{ item.username }}</div>
            <div style="margin-top: 10px">{{ item.content }}</div>
            <div style="margin-top: 10px;font-size: 13px">{{ item.createTime |dateFormat}}</div>
            <div v-if="item.rid" style="background: rgb(237,237,237);margin-top: 20px;padding: 10px">
              <div style="color: red">管理员回复：</div>
              <div>{{ item.replay }}</div>
            </div>
          </div>

        </div>

      </div>

      <!--      右边的选择信息-->
      <div style="flex: 20%;padding-right: 100px">
        <div>发布一条留言吧~</div>
        <div>
          <el-input type="textarea" v-model="message"></el-input>
        </div>
        <div style="margin-top: 20px">
          <el-button type="primary" @click="release()">发布</el-button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import {getList,sendMsg} from "../../network/reply";

export default {
  name: "Reply",
  data() {
    return {
      message: "",
      currentPage: 1,
      pageSize: 10,
      messages: []
    }
  },
  methods: {
    release() {
      sendMsg(this.message).then(res =>{
        if (res.code === 2000){
          this.$message({
            message: '留言成功',
            type: 'success'
          });
        }else{
          this.$message.error('留言失败');
        }

        this.message = ""

        const data = {}
        data.currentPage = this.currentPage
        data.pageSize = this.pageSize
        getList(data).then(res => {
          this.messages = res.data.records
        })
      })
    }
  },
  created() {
    const data = {}
    data.currentPage = this.currentPage
    data.pageSize = this.pageSize
    getList(data).then(res => {
      this.messages = res.data.records
    })
  }
}
</script>

<style scoped>

</style>