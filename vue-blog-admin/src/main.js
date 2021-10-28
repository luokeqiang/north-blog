import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/icons' // icon
// 全局样式
import '@/styles/index.scss'
import 'animate.css' // animate__animated
import LazyLoadComponent from '@/components/core/LazyLoadComponent'
const app = createApp(App)
LazyLoadComponent(app)
app.use(store).use(router).mount('#app')
