<template>
  <div>
    <div class="header">
      <yi-header/>
    </div>
    <div class="container" ref="container">
      <div class="bg">
        <vue-particles
          id="particles-js"
          color="#409EFF"
          :particleOpacity="0.7"
          :particlesNumber="180"
          shapeType="circle"
          :particleSize="4"
          linesColor="#409EFF"
          :linesWidth="1"
          :lineLinked="true"
          :lineOpacity="0.4"
          :linesDistance="150"
          :moveSpeed="3"
          :hoverEffect="true"
          hoverMode="grab"
          :clickEffect="true"
          clickMode="push"
        />
      </div>
      <router-view/>
    </div>
    <div class="footer animate__animated" ref="subnav" :class="{animate__slideInUp: isFixed,animate__slideOutDown: !isFixed}">
      <yi-footer/>
    </div>
  </div>
</template>

<script>
import YiHeader from '@/components/common/layout/YiHeader'
import YiFooter from '@/components/common/layout/YiFooter'
export default {
  name: 'Layout',
  components: {
    YiHeader,
    YiFooter
  },
  data() {
    return {
      isFixed: true
    }
  },
  mounted() {
    if (this.$refs.subnav.getBoundingClientRect) {
      this.scrollTop(this.$refs.subnav.getBoundingClientRect())
    }
  },
  methods: {
    // 这是封装的一个方法
    scrollTop(h) {
      this.test()((res) => {
        this.isFixed = res.scrollH === 0
      })
    },
    test() {
      return function(callback) {
        // 页面总高
        var totalH = document.body.scrollHeight || document.documentElement.scrollHeight
        // 可视高
        var clientH = window.innerHeight || document.documentElement.clientHeight
        var result = {}
        window.addEventListener('scroll', function(e) {
          // 计算有效高
          var validH = totalH - clientH
          // 滚动条卷去高度
          var scrollH = document.body.scrollTop || document.documentElement.scrollTop
          // 百分比
          result.percentage = (scrollH / validH * 100).toFixed(2)
          result.scrollH = scrollH
          callback && callback(result)
        })
      }
    }
  }
}
</script>

<style scoped lang="scss">
.header {
  color: #fff;
  position: fixed;
  top: 0;
  left: 0;
  height: 60px;
  width: 100%;
  background-color: #393D49;
  border-bottom: 1px solid #5FB878;
}
.container{
  margin-top: 60px;
  height: 100%;
  text-align: center;
}
.footer {
  position: fixed;
  bottom: 0;
  height: 20px;
  margin-top: 40px;
  width: 100%;
  color: #000;
  text-align: center;
  background-color: #2c3e50;
}
#particles-js{
  background-color: #3c4144;
  width: 100%;
  height: 100px;
}
.hidden{
  animation: hidden-key-frames 1s;
  animation-fill-mode:forwards;
  -webkit-animation-fill-mode:forwards; /* Safari 和 Chrome */
}
@keyframes hidden-key-frames {
  from{opacity: 1}
  to{opacity: 0}
}
</style>
