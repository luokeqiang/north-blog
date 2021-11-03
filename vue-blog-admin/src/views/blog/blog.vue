<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container" style="margin: 10px 0 10px 0;">

      <el-form :inline="true" v-show="showSearch" label-width="68px" class="filter">
        <el-input
          clearable
          class="filter-item"
          style="width: 150px;"
          v-model="queryParams.keyword"
          placeholder="请输入博客名"
          @keyup.enter="handleFind"
        ></el-input>

        <el-select
          v-model="queryParams.sortKeyword"
          style="width: 140px"
          class="filter-item"
          filterable
          clearable
          remote
          reserve-keyword
          placeholder="请输入分类名"
          @keyup.enter="handleFind"
        >
          <el-option
            v-for="item in sortOptions"
            :key="item.uid"
            :label="item.sortName"
            :value="item.uid"
          ></el-option>
        </el-select>

        <el-select
          v-model="queryParams.tagKeyword"
          class="filter-item"
          filterable
          clearable
          remote
          reserve-keyword
          placeholder="请输入标签名"
          @keyup.enter="handleFind"
          style="width:140px"
        >
          <el-option
            v-for="item in tagOptions"
            :key="item.uid"
            :label="item.content"
            :value="item.uid"
          ></el-option>
        </el-select>

        <el-button style="margin-left: 10px;" class="filter-item" type="primary" icon="el-icon-search" @click="handleFind">查找</el-button>

      </el-form>
      <el-row :gutter="10" style="margin-bottom: 8px;display: block">
        <el-col :span="1.5">
          <el-button class="filter-item" type="primary" @click="handleAdd" icon="el-icon-edit">添加博客</el-button>
        </el-col>

        <el-col :span="1.5">
          <el-button class="filter-item" type="warning"  icon="el-icon-star-on">本地上传</el-button>
        </el-col>

        <el-col :span="1.5">
          <el-button class="filter-item" type="warning"  icon="el-icon-s-flag">导出选中</el-button>
        </el-col>

        <el-col :span="1.5">
          <el-button class="filter-item" type="info"  icon="el-icon-folder-opened">添加专题</el-button>
        </el-col>

        <el-col :span="1.5">
          <el-button class="filter-item" type="danger" icon="el-icon-delete">删除选中</el-button>
        </el-col>

        <right-toolbar :showSearch="showSearch"></right-toolbar>
      </el-row>
    </div>

    <el-table :data="tableData"
              ref="articleTable"
              style="width: 100%"
              :default-sort="{prop: 'createTime', order: 'descending'}">
      <el-table-column type="selection"></el-table-column>

      <el-table-column label="序号" width="60" align="center">
        <template v-slot="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>

      <el-table-column label="标题图" width="160" align="center">
        <template v-slot="scope">
          <img
            v-if="scope.row.photoList"
            :src="scope.row.photoList[0]"
            style="width: 130px;height: 70px;"
          >
        </template>
      </el-table-column>

      <el-table-column label="标题" width="160" align="center">
        <template v-slot="scope">
          <span @click="onClick(scope.row)" style="cursor:pointer;">{{ scope.row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column label="作者" width="100" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否原创" width="100" align="center" prop="isOriginal" sortable="custom" :sort-by="['isOriginal']">
        <template v-slot="scope">
          <el-tag v-if="scope.row.isOriginal==1" type="success">原创</el-tag>
          <el-tag v-if="scope.row.isOriginal==0" type="info">转载</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="分类" width="100" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.category }}</span>
        </template>
      </el-table-column>

      <el-table-column label="标签" width="200" align="center" >
        <template v-slot="scope">
          <template v-for="(item, index) in scope.row.tagList">
            <el-tag
              style="margin-left: 3px"
              type="warning"
              v-if="item"
              :key="index"
            >{{item.content}}</el-tag>
          </template>
        </template>
      </el-table-column>

<!--      <el-table-column label="推荐等级" width="100" align="center" prop="level" sortable="custom" :sort-by="['level']">-->
<!--        <template v-slot="scope">-->
<!--          <el-tag v-for="item in blogLevelDictList" :key="item.uid" v-if="scope.row.level == item.dictValue" :type="item.listClass">{{item.dictLabel}}</el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="点击数" width="90" align="center" prop="clickCount" sortable="custom" :sort-by="['clickCount']">
        <template v-slot="scope">
          <span>{{ scope.row.clickCount }}</span>
        </template>
      </el-table-column>

      <el-table-column label="开启评论" width="100" align="center" prop="openComment" sortable="custom" :sort-by="['openComment']">
        <template v-slot="scope">
          <template v-for="item in openDictList">
            <el-tag  :key="item.uid" :type="item.listClass" v-if="scope.row.openComment == item.dictValue">{{item.dictLabel}}</el-tag>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="发布状态" width="100" align="center" prop="isPublish" sortable="custom" :sort-by="['isPublish']">
        <template v-slot="scope">
          <template v-for="item in blogPublishDictList">
            <el-tag  :key="item.uid" :type="item.listClass" v-if="scope.row.isPublish == item.dictValue">{{item.dictLabel}}</el-tag>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" width="160" align="center" prop="createTime" sortable="custom" :sort-orders="['ascending', 'descending']">
        <template v-slot="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" fixed="right" min-width="150">
        <template v-slot="scope">
          <el-button @click="handleEdit(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
<!--    <div class="block">-->
<!--      <el-pagination-->
<!--        @current-change="handleCurrentChange"-->
<!--        :current-pagev-slot:scope="currentPage"-->
<!--        :page-size="pageSize"-->
<!--        layout="total, prev, pager, next, jumper"-->
<!--        :total="total"-->
<!--      ></el-pagination>-->
<!--    </div>-->
  <blog-edit :visible="visible.blogEdit"/>
  </div>
</template>

<script>
import RightToolbar from '@/components/RightToolbar/RightToolbar'
import BlogEdit from '@/views/blog/BlogEdit'
export default {
  name: 'Blog',
  components: {
    RightToolbar,
    BlogEdit
  },
  data() {
    return {
      visible: {
        blogEdit: false
      },
      showSearch: true,
      queryParams: {
        keyword: '',
        tagKeyword: '', // 标签搜索
        sortKeyword: '', // 分类搜索
        levelKeyword: '', // 等级搜索
        publishKeyword: '', // 发布 搜索
        originalKeyword: '', // 原创 搜索
        typeKeyword: '' // 文章类型
      }, // 搜索条件
      tagOptions: [], // 标签候选框
      sortOptions: [], // 分类候选框
      blogPublishDictList: [],
      openDictList: [],
      tableData: [
        {
          title: '假如给我三天光明',
          photoList: [],
          author: '海',
          category: '',
          isOriginal: 1,
          tagList: [],
          createTime: '',
          clickCount: ''
        },
        {
          title: '假如给我三天光明',
          photoList: [],
          author: '海',
          category: '',
          isOriginal: 0,
          tagList: [],
          createTime: '',
          clickCount: ''
        }
      ] // 博客数据
    }
  },
  methods: {
    handleFind() {
    },
    handleAdd() {
      this.visible.blogEdit = true
    }
  }
}
</script>
<style scoped lang="scss">
.filter{
  margin-bottom: 8px;
  .filter-item{
    margin-right: 8px;
  }
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  margin: 0 0 0 10px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width:  195px;
  height: 105px;
  line-height: 105px;
  text-align: center;
}
.imgBody {
  width:  195px;
  height: 105px;
  border: solid 2px #ffffff;
  float: left;
  position: relative;
}
.uploadImgBody {
  margin-left: 5px;
  width:  195px;
  height: 105px;
  border: dashed 1px #c0c0c0;
  float: left;
  position: relative;
}
.uploadImgBody :hover {
  border: dashed 1px #00ccff;
}
.inputClass {
  position: absolute;
}
.el-dialog__body {
  padding-top: 10px;
  padding-bottom: 0px;
}
.el-dialog {
  min-height: 400px;
}
.el-upload__tip {
  margin-top: 10px;
  margin-left: 10px;
  color: #3e999f;
}

.upload-demo {
  margin-top: 50px;
}
.tipBox {
  margin-bottom: 30px;
}
.tip {
  font-size: 14px;
  font-weight: bold;
  color: #808080;
}
.tipItem {
  line-height: 22px;
  color: #A9A9A9;
}
</style>
