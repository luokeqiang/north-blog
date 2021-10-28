import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'animate.css' // animate__animated
import LazyLoadComponent from '@/components/core/LazyLoadComponent'
const app = createApp(App)
LazyLoadComponent(app)
app.use(store).use(router).mount('#app')
