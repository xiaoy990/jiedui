// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './Router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$ajax = axios
Vue.use(ElementUI);

axios.defaults.timeout = 5000
axios.defaults.headers.post['Content-Type'] = 'appliaction/json;charset=UTF-8'
axios.defaults.baseURL = 'http://localhost:8080'
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})