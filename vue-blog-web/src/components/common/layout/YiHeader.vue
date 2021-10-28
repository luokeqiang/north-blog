<template>
  <div>
    <div class="nav-bar-header">
      <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#393D49"
        text-color="#E6E6E6"
        active-text-color="#fff"
      >
        <el-menu-item index="1">首页</el-menu-item>
        <el-menu-item index="2">分类</el-menu-item>
        <el-menu-item index="3">消息中心</el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {
  name: 'YiHeader',
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style scoped>
.nav-bar-header{
  margin-left: 40%;
}
::v-deep .el-menu.el-menu--horizontal{
  border-bottom: none;
}
::v-deep .el-menu--horizontal>.el-menu-item.is-active{
  border-bottom-color: #409eff !important;
}
</style>
