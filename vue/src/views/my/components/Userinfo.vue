<template>
  <div class="userinfo">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="p-r mb100" style="left:-140px">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>个人信息</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form ref="addForm" :model="addForm" label-width="120px" class="addForm" label-position="right" size="small">
      <el-form-item label="">
        <el-upload class="avatar-uploader" :action="url" :show-file-list="false" :on-success="imgSuccess">
          <img v-if="addForm.img" :src="addForm.img" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="名称">
        <el-input v-model="addForm.name"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-radio label="男" v-model="addForm.sex"></el-radio>
        <el-radio label="女" v-model="addForm.sex"></el-radio>
      </el-form-item>
      <el-form-item label="账号">
        <el-input v-model="addForm.user" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="addForm.pwd" type="password"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="addForm.tel"></el-input>
      </el-form-item>
      <el-form-item label="居住地:">
        <el-input v-model="addForm.addr" placeholder="请输入居住地" type="textarea" rows="4"></el-input>
      </el-form-item>

    </el-form>
    <div class="t-c btn w100 mt40">
      <el-button type="" class="p-r car" style="left:60px;" @click="submit">保存</el-button>
    </div>
  </div>
</template>

<script>
import * as api from "@/api/api";
import { mapGetters, mapMutations } from "vuex";

export default {
  components: {},
  data() {
    return {
      url: process.env.VUE_APP_BASE_FILE,
      addForm: {
        id: "",
        name: "",
        user: "",
        pwd: "",
        tel: "",
        addr: "",
        sex: "",
      },
    };
  },
  created() {},
  mounted() {
    this.addForm = this.userinfo;
  },
  beforeDestroy() {},
  computed: {
    ...mapGetters(["userinfo"]),
  },
  methods: {
    ...mapMutations(["setUserInfo"]),
    //大图上传
    imgSuccess(file) {
      this.addForm.img = file.data;
    },

    //保存
    submit() {
      // 非空判断
      try {
        Object.keys(this.addForm).forEach((k) => {
          if (this.addForm[k] == "") {
            throw new Error("请填写所有信息");
          }
        });
      } catch (err) {
        this.$message.error("请填写所有信息");
        return;
      }

      //判断手机号
      if (!/^(?:(?:\+|00)86)?1[3-9]\d{9}$/.test(this.addForm.tel)) {
        this.$message.error("手机号格式不正确");
        return;
      }

      //密码
      if (!/^[A-Za-z0-9]{6,10}$/.test(this.addForm.pwd)) {
        this.$message.error("密码为英文字母或者数字,6-10位");
        return;
      }

      api.usersave(this.addForm).then((res) => {
        if (res.code == 200) {
          this.setUserInfo(this.addForm);
          this.$message.success("修改成功!");
        }
      });
    },
  },
};
</script>

<style lang='scss' scoped>
.userinfo {
  width: 600px;
  position: relative;
  left: 50%;
  margin-left: -300px;
  // top: 5vh;
}
>>> .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader {
  text-align: center;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  border: 1px #c7c7c7 dashed;
  border-radius: 10px;
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  border-radius: 50%;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
  border-radius: 50%;
}
.btn {
  button {
    border: #ff5147;
  }
  .car {
    color: #fff;
    background-color: #ff5147;
    background: linear-gradient(90deg, #ff734d 0%, #ff5147 100%);
    height: 50px;
    width: 200px;
  }
}
</style>