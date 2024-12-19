<template>
  <div class="home-body">
    <el-container>
      <el-aside width="200px" class="aside">
        <h3>自习室座位预约管理</h3>
        <el-menu
            default-active="2"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>系统管理</span>
            </template>
            <el-menu-item index="1-1" @click="goPage('/adminLog')">留言管理</el-menu-item>
            <el-menu-item index="1-2" @click="goPage('/adminNotice')">公告管理</el-menu-item>
          </el-submenu>
          <el-menu-item @click="goPage('/adminClass')" index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">自习室管理</span>
          </el-menu-item>
          <el-menu-item index="3" @click="goPage('/adminUser')">
            <i class="el-icon-setting"></i>
            <span slot="title" >用户管理</span>
          </el-menu-item>
          <el-menu-item index="4" @click="goPage('/seatfx')">
            <i class="el-icon-more-outline"></i>
            <span slot="title" >座位数据分析</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <el-menu active-text-color="rgb(255, 208, 75)" class="el-menu" mode="horizontal" background-color="#c4c4c4">
            <el-submenu index="2">
              <template slot="title"><img class="header-img"
                                          src="https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoM6WDpQlUMDAxqvq3iaXsx9OlqFKAcWZ7w5hbLb5fKqvcWYPCxTtydelytE6740jicEFAP3iaCAU6Ag/132"
                                          alt="头像加载失败">
              </template>
              <el-menu-item index="2-1">修改个人信息</el-menu-item>
              <el-menu-item index="2-2" @click="logout()">退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-header>
        <el-main>
          <router-view/>
        </el-main>
        <el-footer>自习室座位预约系统</el-footer>
      </el-container>
    </el-container>

  </div>
</template>

<script>
import {logout} from "../../../network/login";

export default {
  name: "Home",
  data() {
    return {}
  },
  methods: {
    goPage(path) {
      this.$router.push(path)
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
    }
  }
}
</script>

<style scoped>
.el-menu{
  background: #c4c4c4;
}
.header-img {
  height: 40px;
  width: 40px;
  border-radius: 50%;
}

.home-body {
  /*height: 100vh;*/
}

.el-header {
  background: #c4c4c4;
  display: flex;
  justify-content: flex-end;
  padding-right: 40px;
  align-items: center;
  height: 100%;
}

.el-header, .el-footer {
  color: #333;
  text-align: center;
  padding: 0;
}

.el-aside {
  background-color: #545c64;
  color: #333;
  text-align: center;
  height: 100vh;
}

.el-main {
  color: #333;
}


body > .el-container {
  margin-bottom: 40px;
}

.el-container{
  height: 100vh;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
