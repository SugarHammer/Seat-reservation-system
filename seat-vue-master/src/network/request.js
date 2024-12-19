import axios from "axios";
import JSONbig from 'json-bigint'
import self from '../main'

export function request(config) {
  const instance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10000,
    transformResponse: [function (data) {
      // Do whatever you want to transform the data
      return JSONbig.parse(data)
    }],
  })

  //请求拦截器
  instance.interceptors.request.use(config => {
    config.headers.satoken = localStorage.getItem("token")
    return config
  },error => {
    console.log(error);
  })

  //响应拦截器
  instance.interceptors.response.use(res => {
    if (res.data.code === 5002){
      self.$message.error('请先登录账号');
      self.$router.push("/login")
    }else if (res.data.code === 5001){
      self.$message.error('token无效，请重新登录');
      self.$router.push("/login")
    }
    return res.data
    // return res.data
  },error => {
    console.log(error);
  })

  //发送真正的网络请求
  return instance(config)

}
