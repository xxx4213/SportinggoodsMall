<template>
  <div class="head f-r f-j-c f-a-c">
    <div style="width:1200px" class="f-r f-a-c f-j-s h100">
      <div class="tab-row h100 f-r ">
        <div class="tab mr10 h100 p-r" v-for="(item,index) in tab" :key="index" v-show="isShow(item.login)" @click="$router.push(item.url)">{{item.name}}
        </div>
      </div>

      <div class="font14 login-register f-r f-a-c">
        <div v-if="Object.keys(userinfo).length!=0" class="mr20">欢迎 <span class="font-default">{{userinfo.name}}</span> </div>
        <div class="zs mr10" v-if="!Object.keys(userinfo).length!=0" @click="$router.push('/login')">超级管理员</div>
        <div class="zs mr10" v-if="!Object.keys(userinfo).length!=0" @click="$router.push('/login')">普通管理员</div>
        <div class="zs" v-if="!Object.keys(userinfo).length!=0" @click="types='登录';show=true">登录</div>
        <div class="zs ml10" v-if="!Object.keys(userinfo).length!=0" @click="types='注册';show=true">注册</div>
        <div class="zs" v-if="Object.keys(userinfo).length!=0" @click="loginout">退出</div>
        <div class="zs ml20 f-r f-a-c f-j-c font-snow" v-if="Object.keys(userinfo).length!=0" @click="$router.push('/car')" style="background: #409EFF;width:100px;height:100%">购物车({{goodsNum}})</div>
      </div>

      <el-dialog title="" :visible.sync="show" class="login" width="400px">
        <div v-if="types=='注册'">
          <div class="t-c font17 font-default">注册</div>
          <el-form ref="addForm" :model="addForm" class="addForm pl50 pr50 pb10 pt40" label-position="right" size="small">
            <el-form-item label="">
              <el-upload class="avatar-uploader" :action="url" :show-file-list="false" :on-success="imgSuccess">
                <img v-if="addForm.img" :src="addForm.img" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="">
              <el-input v-model="addForm.name" placeholder="请输入昵称"></el-input>
            </el-form-item>
            <el-form-item label="">
              <el-radio label="男" v-model="addForm.sex"></el-radio>
              <el-radio label="女" v-model="addForm.sex"></el-radio>
            </el-form-item>
            <el-form-item label="">
              <el-input v-model="addForm.user" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input v-model="addForm.pwd" type="password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input v-model="repwd" type="password" placeholder="重新输入密码"></el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input v-model="addForm.tel" placeholder="请输入手机号"></el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input v-model="addForm.addr" placeholder="请输入居住地" type="textarea" rows="4"></el-input>
            </el-form-item>
          </el-form>
          <div class="font-default font14 pl50 pr50 t-r mb10 zs" @click="types='登录'">已有账号?登录</div>
          <div class="pb40 t-c pl50 pr50">
            <el-button class="w100" @click="register">注册</el-button>
          </div>
        </div>

        <div v-if="types=='登录'">
          <div class="t-c font17 font-default">登录</div>
          <el-form ref="addForm" :model="addForm" class="addForm pl50 pr50 pt40" label-position="right" size="small">
            <el-form-item label="">
              <el-input v-model="addForm.user" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input v-model="addForm.pwd" type="password" placeholder="请输入密码"></el-input>
            </el-form-item>
          </el-form>
          <div class="font-default font14 pl50 pr50 t-r mb10 zs f-r f-a-c f-j-s mb40">
            <span @click="types='注册'">没有账号?注册</span>
          </div>
          <div class="pb40 t-c pl50 pr50">
            <el-button class="w100" @click="login">登录</el-button>
          </div>
        </div>

      </el-dialog>

    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import * as api from "@/api/api";

export default {
  components: {},
  props: ["showSearch", "carNum"],
  data() {
    return {
      url: process.env.VUE_APP_BASE_FILE,
      step: 1,
      show: false,
      types: "",
      addForm: {
        name: "",
        user: "",
        pwd: "",
        tel: "",
        img: "",
        addr: "",
        sex: "男",
      },
      repwd: "",
      activeTab: this.$route.path,
      isLogin: sessionStorage.getItem("id") != undefined ? true : false,
      name: sessionStorage.getItem("name"),
      searchName: "",
      tab: [
        {
          name: "首页",
          url: "/home",
        },
        {
          name: "个人中心",
          url: "/my?component=Userinfo",
          login: true,
        },
        {
          name: "我的订单",
          url: "/my?component=Userorders",
          login: true,
        },
      ],
    };
  },
  created() {
    if (localStorage.getItem("userinfo")) {
      this.setUserInfo(JSON.parse(localStorage.getItem("userinfo")));
    }
    if (localStorage.getItem("car")) {
      this.setGoods(JSON.parse(localStorage.getItem("car")));
    }
  },
  mounted() {},
  beforeDestroy() {},
  computed: {
    ...mapGetters(["goods", "goodsNum", "userinfo"]),

    //是否显示tab
    isShow() {
      return (login) => {
        if (login && Object.keys(this.userinfo).length == 0) {
          return false;
        }
        return true;
      };
    },
  },
  methods: {
    ...mapMutations(["setUserInfo", "setGoods"]),
    imgSuccess(file) {
      this.addForm.img = file.data;
    },

    // 登录
    login() {
      if (this.addForm.user == "" || this.addForm.pwd == "") {
        this.$message.error("请输入账号密码!");
        return;
      }
      api
        .userlist({
          limit: 1,
          offset: 1,
          user: this.addForm.user,
          pwd: this.addForm.pwd,
        })
        .then((res) => {
          if (res.data.list.length > 0) {
            this.setUserInfo(res.data.list[0]);
            this.show = false;
            this.$message.success("登录成功!");
          } else {
            this.$message.error("账号密码错误!");
          }
        });
    },

    // 注册
    async register() {
      // 非空判断
      try {
        if (this.repwd == "") {
          throw new Error("请填写所有信息");
        }
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
      if (this.addForm.pwd != this.repwd) {
        this.$message.error("两次输入密码不一致");
        return;
      }
      //检测是账号是否重复

      let user = await api.userlist({
        limit: 1,
        offset: 1,
        user: this.addForm.user,
      });

      if (user.data.total) {
        this.$message.error("账号已存在");
        return;
      }

      api.usersave(this.addForm).then((res) => {
        if (res.code == 200) {
          this.$message.success("注册成功!");
          this.addForm = {
            name: "",
            user: "",
            pwd: "",
            tel: "",
            img: "",
            addr: "",
            sex: "男",
          };
          this.repwd = "";
          this.types = "登录";
        } else {
          this.$message.error("注册失败!");
        }
      });
    },

    loginout() {
      this.setUserInfo({});
      localStorage.clear();
      sessionStorage.clear();
      this.$router.push("/home");
    },
  },
  watch: {
    types() {
      this.addForm = {
        name: "",
        user: "",
        pwd: "",
        tel: "",
        img: "",
        addr: "",
        sex: "男",
      };
      this.repwd = "";
      this.step = 1;
    },
  },
};
</script>

<style lang='scss' scoped>
.head {
  height: 35px;
  background: #fff;
  width: 100%;
  box-shadow: 0 1px 3px rgba(26, 26, 26, 10%);
  .tab {
    position: relative;
    height: 100%;
    line-height: 35px;
    font-size: 14px;
    cursor: pointer;
    border-bottom: 2px solid transparent;
    color: #333;
  }
  .login-register {
    color: #333;
    height: 100%;
  }
  .tab-active {
    color: #409eff;
    &::before {
      content: " ";
      width: 100%;
      position: absolute;
      font-weight: 600;
      bottom: 0;
      left: 0;
      height: 2px;
      background-color: #409eff;
    }
  }
}

.login {
  ::v-deep .el-dialog__body {
    padding: 0;
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
  }
}
</style>