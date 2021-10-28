import 'element-plus/dist/index.css'
import elementComponents from '@/components/core/ElementComponent'
import customerComponents from '@/components/core/CustomerCompoent'
import iconComponents from '@/components/core/IconComponent'
/**
 *  注册组件
 * @param app
 */
function registry (app) {
  elementComponents.concat(customerComponents).forEach(component => app.use(component))
  iconComponents.forEach(component => app.component(component.name, component))
}
export default (app) => {
  registry(app)
}
