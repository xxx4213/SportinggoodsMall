<template>
  <div class="dashboard-container">
    <h1>后台管理系统</h1>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      name: sessionStorage.getItem("name")
    };
  },
  created() {},
  mounted() {},
  methods: {},
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
<style>
.echarts {
  width: 100%;
  height: 500px;
}
</style>
