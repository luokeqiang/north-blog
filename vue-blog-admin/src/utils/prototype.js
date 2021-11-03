/**
 * 挂载全局属性
 */

import store from '@/store'
import router from '@/router'

import { ElMessage, ElLoading } from 'element-plus'

import CommonConstant from '@/constant/CommonConstant'

export default (app) => {
  app.config.globalProperties.$store = store
  app.config.globalProperties.$router = router
  app.config.globalProperties.$loading = ElLoading
  app.config.globalProperties.$message = ElMessage
  app.config.globalProperties.$ResCode = CommonConstant.RES_CODE
}
