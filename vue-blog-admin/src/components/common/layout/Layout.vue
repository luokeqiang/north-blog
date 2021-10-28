<template>
  <div>
    <div class="header-container" :style="{left: headLeft}">
      <hamburger class="hamburger-container" :is-active="!collapse" @click="onHamburgerChange"/>
      <el-breadcrumb separator="/" class="breadcrumb-container">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>
        <el-breadcrumb-item>活动列表</el-breadcrumb-item>
        <el-breadcrumb-item>活动详情</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="sidebar-container" :style="{'width': sidebarWidth}">
     <sidebar :collapse="collapse"/>
    </div>
    <div class="main-container" :style="{'margin-left': containerMarginLeft}">
      <router-view/>
    </div>
  </div>
</template>

<script>
import Sidebar from '@/components/common/layout/Sidebar'
import Hamburger from '@/components/Hamburger'
export default {
  name: 'Layout',
  components: {
    Sidebar,
    Hamburger
  },
  data() {
    return {
      collapse: true,
      items: []
    }
  },
  computed: {
    sidebarWidth() {
      return this.collapse ? '53px' : '148px'
    },
    headLeft() {
      return this.collapse ? '75px' : '170px'
    },
    containerMarginLeft() {
      return this.collapse ? '90px' : '185px'
    }
  },
  methods: {
    onHamburgerChange() {
      this.collapse = !this.collapse
    }
  }
}
</script>

<style lang="scss" scoped>
.hamburger-container {
  line-height: 58px;
  height: 50px;
  float: left;
  padding-right: 10px;
  display: inline-block;
}
.breadcrumb-container {
  line-height: 50px;
  float: left;
}
.header-container {
  position: fixed;
  top: 0;
  z-index: 999;
  height: 50px;
  width: 100%;
  background-color: #fff;
  border-bottom: solid 1px #e6e6e6;
  transition: left 0.5s;
}
// 主体区域
.main-container {
  min-height: 100%;
  margin-top: 60px;;
  position: relative;
  transition: margin-left 0.5s;
}
// 侧边栏
.sidebar-container {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  //width: 148px!important;
  height: 100%;
  overflow: hidden;
  transition: width 0.5s;
  .el-menu {
    border: none;
    height: 100%;
    width: 100% !important;
  }
  ::v-deep .el-sub-menu__title{
    padding-left: 10px!important;
  }
}
.min-sidebar-container{
  width: 10px
}
</style>
