const path = require('path')
function resolve(dir) {
  return path.join(__dirname, '.', dir)
}
module.exports = {
  devServer: {
    host: '0.0.0.0',
    port: 8023,
    open: true,
    proxy: {
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      [process.env.VUE_APP_BASE_API]: {
        target: 'http://localhost:9111',
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    },
    disableHostCheck: true
  },

  chainWebpack: config => {
    // 禁用系统内部对svg的处理
    config.module
      .rule('svg')
      .exclude.add(resolve('src/icons/svg'))
      .end()
    // 新建规则处理svg文件
    config.module
      .rule('icons')
      .test(/\.svg$/) // 添加匹配规则
      .include.add(resolve('src/icons/svg')) // 添加我们要处理的文件路径
      .end() // 上面的add方法改变了上下文，调用end()退回到include这一级
      .use('svg-sprite-loader')// 使用"svg-sprite-loader"这个依赖
      .loader('svg-sprite-loader')// 选中这个依赖
      .options({
        symbolId: 'icon-[name]' // 这个配置是这个包的配置不属于webpack，可以查看相关文档，symbolId指定我们使用svg图片的名字
      })
  }
}
