<template>
  <el-dialog
    :title="title"
    v-model="dialogVisible"
    :before-close="closeDialog"
    fullscreen
  >
    <el-form :model="form" :rules="rules" ref="form">

      <el-row>
        <el-col :span="16">
          <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
            <el-input v-model="form.title" auto-complete="off" @input="contentChange"></el-input>
          </el-form-item>

          <el-form-item label="简介" :label-width="formLabelWidth">
            <el-input v-model="form.summary" auto-complete="off" @input="contentChange"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="标题图" :label-width="formLabelWidth">
            <div class="imgBody" v-if="form.photoList">
              <i
                class="el-icon-error inputClass"
                v-show="icon"
                @click="deletePhoto()"
                @mouseover="icon = true"
              ></i>
              <img
                @mouseover="icon = true"
                @mouseout="icon = false"
                v-bind:src="form.photoList[0]"
                style="display:inline; width: 195px;height: 105px;"
              >
            </div>
            <div v-else class="uploadImgBody" @click="checkPhoto">
              <i class="el-icon-plus avatar-uploader-icon"></i>
            </div>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="6.5">
          <el-form-item label="分类" :label-width="formLabelWidth" prop="blogSortUid">
            <el-select
              @input="contentChange"
              v-model="form.blogSortUid"
              size="small"
              placeholder="请选择"
              style="width:150px"
            >
              <el-option
                v-for="item in blogSortData"
                :key="item.uid"
                :label="item.sortName"
                :value="item.uid"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="6.5">
          <el-form-item label="标签" label-width="80px" prop="tagUid">
            <el-select
              @input="contentChange"
              v-model="tagValue"
              multiple
              size="small"
              placeholder="请选择"
              style="width:210px"
              filterable
            >
              <el-option
                v-for="item in tagData"
                :key="item.uid"
                :label="item.content"
                :value="item.uid"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="6.5">
          <el-form-item label="推荐等级" :label-width="maxLineLabelWidth" prop="level">
            <el-select v-model="form.level" size="small" placeholder="请选择" style="width:210px">
              <el-option
                v-for="item in blogLevelDictList"
                :key="item.uid"
                :label="item.dictLabel"
                :value="parseInt(item.dictValue)"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="6.5">
          <el-form-item label="是否原创" :label-width="formLabelWidth" prop="isOriginal">
            <el-radio-group v-model="form.isOriginal" size="small">
              <el-radio v-for="item in blogOriginalDictList" v-model="form.isOriginal" :key="item.code" :label="item.code" border>{{item.text}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="6.5">
          <el-form-item label="文章评论" :label-width="formLabelWidth" prop="openComment">
            <el-radio-group v-model="form.openComment" size="small">
              <el-radio v-for="item in openDictList" :key="item.code" :label="item.code" border size="small">{{item.text}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="4.5">
          <el-form-item label="是否发布" :label-width="lineLabelWidth" prop="isPublish">
            <el-radio-group v-model="form.isPublish" size="small">
              <el-radio v-for="item in blogPublishDictList" :key="item.code" :label="item.code" border>{{item.text}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="作者" :label-width="formLabelWidth" v-if="form.isOriginal==0" prop="author">
        <el-input v-model="form.author" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="文章出处" :label-width="formLabelWidth" v-if="form.isOriginal==0">
        <el-input v-model="form.articlesPart" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="外链" :label-width="formLabelWidth" v-if="form.type == 1" prop="outsideLink">
        <el-input v-model="form.outsideLink" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="内容" :label-width="formLabelWidth" prop="content">
<!--        <CKEditor v-if="systemConfig.editorModel == '0'" ref="editor" :content="form.content" @contentChange="contentChange" :height="360"></CKEditor>-->
        <markdown-editor :content="form.content" ref="editor" :height="465"></markdown-editor>
      </el-form-item>

      <el-form-item style="float: right; margin-right: 20px;">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import MarkdownEditor from '@/components/MarkdownEditor/MarkdownEditor'
export default {
  name: 'BlogEdit',
  components: {
    MarkdownEditor
  },
  data() {
    return {
      blogOriginalDictList: [
        {
          code: 1,
          text: '原创'
        },
        {
          code: 0,
          text: '转载'
        }
      ], // 存储区域字典
      blogPublishDictList: [
        {
          code: 1,
          text: '发布'
        },
        {
          code: 0,
          text: '下架'
        }
      ], // 是否字典
      openDictList: [
        {
          code: 1,
          text: '开启'
        },
        {
          code: 0,
          text: '关闭'
        }
      ], // 是否启动字典
      formLabelWidth: '120px',
      lineLabelWidth: '120px', // 一行的间隔数
      maxLineLabelWidth: '100px',
      form: {
        uid: null,
        title: null,
        summary: null,
        content: null,
        tagUid: null,
        fileUid: null,
        isOriginal: 1, // 是否原创
        isPublish: 1,
        author: null, // 作者
        clickCount: 0,
        openComment: 1,
        articlesPart: null // 文章出处
      },
      rules: {
        title: [
          { required: true, message: '标题不能为空', trigger: 'blur' }
        ],
        blogSortUid: [
          { required: true, message: '分类不能为空', trigger: 'blur' }
        ],
        tagUid: [
          { required: true, message: '标签不能为空', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '推荐等级不能为空', trigger: 'blur' },
          { pattern: /^[0-9]\d*$/, message: '推荐等级只能为自然数' }
        ],
        isPublish: [
          { required: true, message: '发布字段不能为空', trigger: 'blur' },
          { pattern: /^[0-9]\d*$/, message: '发布字段只能为自然数' }
        ],
        isOriginal: [
          { required: true, message: '原创字段不能为空', trigger: 'blur' },
          { pattern: /^[0-9]\d*$/, message: '原创字段只能为自然数' }
        ],
        openComment: [
          { required: true, message: '网站评论不能为空', trigger: 'blur' },
          { pattern: /^[0-9]\d*$/, message: '网站评论只能为自然数' }
        ],
        content: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        outsideLink: [
          { required: true, message: '外链地址不能为空', trigger: 'blur' },
          { pattern: /^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+/, message: '请输入有效的URL' }
        ]
      }
    }
  },
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: '新增博客'
    }
  },
  computed: {
    dialogVisible() {
      return this.visible
    }
  },
  methods: {
    submitForm() {
      console.log(this.$refs.editor.getData())
    }
  }
}
</script>

<style scoped>

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  margin: 0, 0, 0, 10px;
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
