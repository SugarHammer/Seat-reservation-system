<template>
  <div class="login-body">
    <div class="login-form">
      <h1 class="form-title">欢迎登录自习室选座系统</h1>
      <div class="form">
        <el-form class="form-form" ref="form" :model="form" label-width="80px">
          <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input type="password" v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="验证码">
            <div class="captcha-f">
              <el-input class="captcha" type="text" v-model="form.captcha"></el-input>
              <img @click="reloadCaptcha()" class="captcha-code" :src="captchaPath" alt="">
            </div>

          </el-form-item>
          <el-form-item label="记住我">
            <el-checkbox-group v-model="form.rememberme">
              <el-checkbox name="rememberme"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item>
            <el-button @click="register">注 册</el-button>
            <el-button type="primary" @click="onSubmit" :loading="isLogin">登 录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {v4 as uuidv4} from 'uuid';
import {login,getUserInfo} from "../network/login";

export default {
  name: "Login",
  data() {
    return {
      form: {
        username: '',
        password: '',
        captcha: '',
        rememberme: false,
        captchaId: '',
        userInfo:{}
      },
      captchaPath: "http://localhost:8080/captcha/get?cid=",
      isLogin:false
    }
  },
  methods: {
    onSubmit() {
      this.isLogin = true
      login(this.form).then(res =>{
        this.isLogin = false
        if (res.code === 2000){
          //将token存储
          localStorage.setItem('token', res.data.token)
          this.$message({
            message: '登录成功',
            type: 'success'
          });
          if (res.data.role === 'admin'){
            this.$router.push("/adminClass")
          }else {
            this.$router.push("/")
          }

        }else if (res.code === 20000){
          this.$message.error(res.msg);
        }
      })
    },
    register() {
      this.$router.push("/register")
    },
    reloadCaptcha() {
      //刷新验证码
      this.captchaPath = "http://localhost:8080/captcha/get?cid=";
      this.form.captchaId = uuidv4()
      this.captchaPath += this.form.captchaId
    }
  },
  created() {
    //进入页面，生成uuid，获取验证码
    this.form.captchaId = uuidv4()
    this.captchaPath += this.form.captchaId


  }
}
</script>

<style scoped>

@keyframes bganimation {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

.login-body {
  background-image: linear-gradient(125deg, #E4FFCD, #6DD5FA, #2980B9, #E4FFCD);
  background-size: 400%;
  animation: bganimation 15s infinite;
  height: 100vh;
}

.captcha-code {
  width: 100px;
  height: 40px;
}

.captcha-f {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.captcha {
  width: 160px;
}

.form-form {
  width: 363px;
}

.form-title {
  display: flex;
  justify-content: center;
  margin-bottom: 60px;
}

.login-body {
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-form {
  box-shadow: 4px 4px 15px #4b4949;
  padding: 10px;
  border-radius: 20px;
}

.login-form::before {
  filter: blur(1px);
}

.form {
  padding: 0 100px;
}

</style>