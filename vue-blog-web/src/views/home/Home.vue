<template>
  <div>
    <div class="box-card">
      <el-card :body-style="{ padding: '0px'}" class="card">
        <el-row :gutter="20">
          <el-col :span="5">
            <img
              src="https://t7.baidu.com/it/u=1819248061,230866778&fm=193&f=GIF"
              class="image"
            />
          </el-col>
          <el-col :span="18">
            <div class="content">
              <a class="title">关于CountDownLatch的用法</a>
              <span class="introduction">
                CountDownLatch是JUC包下的一个工具类，其作用是，可以保证多个线程全部执行结束后才进行后续操作
              </span>
            </div>
          </el-col>
          <el-col :span="24"><div class="time"><el-icon>
            <clock />
          </el-icon>
            {{new Date()}}</div></el-col>
        </el-row>
      </el-card>
      <div class="split"></div>
      <el-card :body-style="{ padding: '0px'}" class="card">
        <el-row :gutter="20">
          <el-col :span="5">
            <img
              src="https://t7.baidu.com/it/u=1819248061,230866778&fm=193&f=GIF"
              class="image"
            />
          </el-col>
          <el-col :span="18">
            <div class="content">
              <a class="title">关于CountDownLatch的用法</a>
              <span class="introduction">
                CountDownLatch是JUC包下的一个工具类，其作用是，可以保证多个线程全部执行结束后才进行后续操作
              </span>
            </div>
          </el-col>
          <el-col :span="24"><div class="time"><el-icon>
            <clock />
          </el-icon>
            {{new Date()}}</div></el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  components: {}
}
</script>

<style scoped lang="scss">
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}
.image {
  --image-width: 130px
}
.image {
  width: var(--image-width);
  height: var(--image-width);
  margin: 10px;
  display: inline-block;
  float: left;
}
.box-card {
  width: 680px;
  margin: 10px auto;
}
.content{
  margin: 10px 10px 10px 15px;
  text-align: left;
  .title{
    float: left;
    font-size: 18px;
    font-weight: bold;
    color: #555;
    &:hover{
      cursor: pointer;
      color: #019688;
    }
  }
  .introduction{
    float: left;
    margin-top: 10px;
    line-height: 1.5;
    font-size: 14px;
    display: block;
    text-indent: 2em;
  }
}
.time{
  text-align: left;
  margin-left: 10px;
}
.split{
  height: 10px;
  width: 10px;
}
.card{
  &:hover{
    border-left: 3px solid #019688;
    border-right: 3px solid #019688;
  }
}
</style>
