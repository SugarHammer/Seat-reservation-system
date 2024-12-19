<template>
  <div style="padding: 40px 200px">
    <h1>{{ content.title }}</h1>
    <el-divider></el-divider>
    <div style="display: flex">
      <div>{{ content.createTime | dateFormat }}</div>
    </div>
    <el-divider></el-divider>
    <p style="font-size: 18px;max-width: 80vw">{{content.content}}</p>
  </div>
</template>

<script>
import {getNoticeDetail} from "../../network/noticeDetail";

export default {
  name: "NoticeDetail",
  data() {
    return {
      content: ''
    }
  },
  methods: {},
  created() {
    const id = this.$route.params.id
    getNoticeDetail(id).then(res => {
      this.content = res.data
    })
  }
}
</script>

<style scoped>

</style>