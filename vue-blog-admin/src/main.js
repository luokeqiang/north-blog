import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/icons' // icon
// 全局样式
import '@/styles/index.scss'
import 'animate.css' // animate__animated
import 'vditor/src/assets/scss/index.scss'
import LazyLoadComponent from '@/components/core/LazyLoadComponent'
import prototype from '@/utils/prototype'
const app = createApp(App)
// 加载自定义组件
LazyLoadComponent(app)
// 定义全局属性
prototype(app)
app.use(store).use(router).mount('#app')
