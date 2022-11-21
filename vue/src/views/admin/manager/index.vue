<template>
  <div class="dashboard-container">

    <el-card slot class="content-card" shadow="never">
      <el-table :data="list" border stripe style="width: 100%;min-height: 60vh;">
        <el-table-column label="序号" header-align="center" align="center" type="index" width="70">
        </el-table-column>
        <el-table-column prop="id" label="账号" header-align="center" align="center" />
        <el-table-column prop="pwd" label="密码" header-align="center" align="center" />
        <el-table-column prop="address" label="操作" header-align="center" align="center" width="190px">
          <template>
            <div>
              <a style="margin-left:20px;color:rgb(31, 144, 254);" >编辑</a>
              <a style="margin-left:20px;color:rgb(31, 144, 254);" >删除</a>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      
    </el-card>

    <!-- 增加编辑 -->
    

  </div>
</template>

<script>


export default {
  components: { Middle, Dialog, Pagination, Enduit },
  data() {
    
  },
  created() {
    
  },
  mounted() {},
  methods: {
    //获取列表
    getData() {
      
    },

    //模态框-关闭
    close() {
     
    },

    //删除
    async del(item) {
      
    },

    // 新增/编辑-提交
    async confirm() {
      
    },

    //编辑
    edit(item) {
      
    }
  },
  computed: {}
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
>>> .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
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
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>