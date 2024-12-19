<template>
  <div>
    <div class="body">

      <!--    头部-->
      <div class="header">

        <div style="flex: 20%;text-align: right">
          <div style="font-size: 40px;color: rgb(0,179,254)">Library</div>
          <div style="font-size: 20px;color: rgb(1,237,201)">Seat selection</div>
        </div>

        <div style="display:flex;flex: 60%;justify-content: center;cursor: pointer">
          <div @click="goIndex()">主页</div>
          <div @click="goReply()" style="margin: 0 20px">留言面板</div>
        </div>

        <div style="display: flex;flex: 20%;align-items: center">
          <el-dropdown>
          <img class="header-img" :src="userInfo.headImg" alt="">
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item><span @click="goRecording()">预约记录</span></el-dropdown-item>
              <el-dropdown-item><span @click="logout">退出 </span></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>


          <el-button v-if="!userInfo.username" @click="goLogin">登录</el-button>
        </div>

      </div>
      <!--    轮播图-->
      <div class="carousel">
        <img src="https://bpic.588ku.com/back_our/20210522/bg/42af624acebe3.png!/fw/980" alt="">
<!--        <img src="https://oss.ppter8.com/lYe8h3Dw9cQlUROuTHVZBwDzHeLeHp4qG0jvFgkj.jpg!/fw/980" alt="">-->
        <div class="carousel-text">
          <div class="welcome-text">
            <h3 style="font-size: 40px">欢迎来到</h3>
          </div>
          <h1 style="font-size: 55px">XX学院自习室座位预约系统</h1>
        </div>
      </div>

      <div style="margin-top: 100px;padding: 0 400px">
        <!--公告-->
        <div style="text-align: center">
          <span class="notice-text">公告</span>
        </div>

        <div style="margin-top: 30px">
          <el-table
              :data="noticeData"
              border
              style="width: 100%">
            <el-table-column
                prop="name"
                label="标题">
              <template slot-scope="scope">
                <div @click="toNotice(scope.row.id)" style="cursor: pointer">{{ scope.row.title }}</div>
              </template>
            </el-table-column>
            <el-table-column
                prop="address"
                label="时间">
              <template slot-scope="scope">
                <div>{{ scope.row.createTime | dateFormat }}</div>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!--选座-->
        <div style="text-align: center;margin-top: 200px">
          <span class="notice-text">预约选座</span>
        </div>
        <div style="margin-top: 30px">
          <el-table
              :data="classData"
              border
              style="width: 100%">
            <el-table-column
                prop="name"
                label="自习室名称">
              <template slot-scope="scope">
                <div>{{ scope.row.name }}</div>
              </template>
            </el-table-column>
            <el-table-column
                prop="address"
                label="时间">
              <template slot-scope="scope">
                <div>{{ scope.row.createTime | dateFormat }}</div>
              </template>
            </el-table-column>
            <el-table-column
                prop="address"
                label="剩余座位">
              <template slot-scope="scope">
                <div>{{ scope.row.totalNum - scope.row.checkedNum }}</div>
              </template>
            </el-table-column>
            <el-table-column
                prop="address"
                label="操作">
              <template slot-scope="scope">
                <div style="text-align: center">
                  <el-button @click="checkClassClick(scope.row.id)" type="primary" :disabled="scope.row.totalNum - scope.row.checkedNum <= 0">选座</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>

      </div>

    </div>
    <!--      页脚-->
    <div class="footer">
      <a class="a" href="/adminClass">后台管理系统入口</a>
    </div>
  </div>

</template>

<script>
import {getNotices,getClass} from "../../network/index";
import {getUserInfo, logout} from "../../network/login";

export default {
  name: "Index",
  data() {
    return {
      noticeData: [],
      classData: [],
      userInfo: {}
    }
  },
  methods:{
    goLogin(){
      this.$router.push("/login")
    },
    logout(){
      logout().then(res => {
        if (res.code === 2000){
          this.$message({
            message: '退出成功',
            type: 'success'
          });
          this.$router.push("/login")
        }
      })
    },
    goIndex(){
      this.$router.push("/")
    },
    goReply(){
      this.$router.push("/reply")
    },
    checkClassClick(id){
      this.$router.push({
        path: `/checkClass/${id}`
      })
    },
    toNotice(id){
      this.$router.push({
        path: `/noticeDetail/${id}`
      })
    },goRecording(){
      this.$router.push({
        path: `/seatRecording`
      })
    }
  },
  created() {
    //获取公告
    getNotices().then(res => {
      this.noticeData = res.data.records
    })

    //获取自习室
    getClass().then(res => {
      this.classData = res.data.records
    })

    getUserInfo().then(res =>{
      this.userInfo = res.data
    })
  }
}
</script>

<style scoped>

.header-img{
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.a {
  text-decoration: none;
  color: white;
}

.footer {
  text-align: center;
  margin-top: 200px;
  background: #333333;
  line-height: 100px;
}

.notice-text {
  font-weight: 700;
  font-size: 30px;
  padding-left: 7px;
  border-left: 4px solid rgb(1, 237, 203);
}

.welcome-text {
  display: flex;

}

.body {
  padding: 0 10px;
}

.demo {
  border: 1px red solid;
}

.header {
  align-items: center;
  justify-content: space-between;
  height: 130px;
  display: flex;
}

.carousel {
  position: relative;
}

.carousel-text {
  color: white;
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.carousel-text h3 {
  padding-bottom: 14px;
  border-bottom: 2px rgb(1, 237, 203) solid;
}

.carousel img {
  width: 100%;
  height: 700px;
}
</style>
