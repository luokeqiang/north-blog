import { createApp } from 'vue'
import SvgIcon from '@/components/SvgIcon'
import App from '@/App' // svg组件
const app = createApp(App)
// register globally
app.component('svg-icon', SvgIcon)

const requireAll = requireContext => requireContext.keys().map(requireContext)
const req = require.context('./svg', false, /\.svg$/)
requireAll(req)
