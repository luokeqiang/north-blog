<template>
  <el-menu
    :uniqueOpened="true"
    :collapse="collapse"
    default-active="2"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    background-color="#304156"
    text-color="#bfcbd9"
    active-text-color="#409EFF"
  >
    <el-sub-menu index="1">
      <template #title>
        <i class="el-icon-edit"></i>
        <span>博客管理</span>
      </template>
      <el-menu-item index="1-1">博客管理</el-menu-item>
      <el-menu-item index="1-2">分类管理</el-menu-item>
      <el-menu-item index="1-3">标签管理</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script>
export default {
  name: 'Sidebar',
  props: {
    collapse: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style scoped>

</style>
