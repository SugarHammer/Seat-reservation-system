import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from "./router";
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import moment from "moment";

Vue.filter('dateFormat',(dateStr,pattern = 'YYYY-MM-DD HH:mm:ss') =>{
  return moment(dateStr).format(pattern)
} )

Vue.use(VueRouter)
Vue.use(ElementUI)
Vue.config.productionTip = false

const vue = new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

export default vue