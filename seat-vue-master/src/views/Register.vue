<template>
  <div class="register-body">
    <div class="bg"></div>
    <div class="bg bg2"></div>
    <div class="bg bg3"></div>
    <div class="content">
      <h1>欢 迎 注 册</h1>
      <el-form class="form-form" ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickName"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input type="password" v-model="form.checkPassword"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button @click="goLogin">去 登 录</el-button>
          <el-button type="primary" @click="registerClick()">注 册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import {register} from "../network/login";

export default {
  name: "Register",
  data(){
    return{
      form: {
        username: '',
        password: '',
        checkPassword: '',
        nickName: ''

      },
    }
  },
  methods: {
    registerClick(){
      register(this.form).then(res => {
        if (res.code === 2000){
          this.$message({
            message: '注册成功',
            type: 'success'
          });
          this.$router.push("/login")
        }else if (res.code === 20000){
          this.$message.error(res.msg);
        }
      })
    },
    goLogin(){
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>
html {
  height: 100%;
}
body {
  margin: 0;
}
.bg {
  animation: slide 3s ease-in-out infinite alternate;
  background-image: linear-gradient(-60deg, #6c3 50%, #09f 50%);
  bottom: 0;
  left: -50%;
  opacity: .5;
  position: fixed;
  right: -50%;
  top: 0;
  z-index: -1;
}
.bg2 {
  animation-direction: alternate-reverse;
  animation-duration: 4s;
}
.bg3 {
  animation-duration: 5s;
}
.content {
  background-color: rgba(255, 255, 255, .8);
  border-radius: .25em;
  box-shadow: 0 0 .25em rgba(0, 0, 0, .25);
  box-sizing: border-box;
  left: 50%;
  padding: 10vmin;
  position: fixed;
  text-align: center;
  top: 50%;
  transform: translate(-50%, -50%);
}
h1 {
  font-family: monospace;
}
@keyframes slide {
  0% {
    transform: translateX(-25%);
  }
  100% {
    transform: translateX(25%);
  }
}
</style>