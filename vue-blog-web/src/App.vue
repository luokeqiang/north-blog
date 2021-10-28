<template>
  <div id="app">
    <layout/>
  </div>
</template>

<script>
import Layout from '@/components/common/layout/Layout'
export default {
  name: 'App',
  components: {
    Layout
  }
}
</script>
<style lang="scss">
@import "~@/assets/css/base.css";
#app{
  width: 100%;
  height: 100%;
  background-color:rgba(192,192,192,0.4);
}
</style>
