<template>
  <div class="useraddr w100">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="p-r mb30">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>收货地址</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="addr w100 f-r f-w-t">
      <div class="addr-row default f-c f-a-c f-j-c zs mr20 mb20" @click="show=true">
        <i class="el-icon-circle-plus font30 mb10"></i>
        添加新收货地址
      </div>
      <div class="addr-row  mr20 p20 mb20 font14 font-grey p-r" :class="item.isdefault=='是'?'addr-row-active':''" v-for="item in list" :key="item.id">
        <div class="font18 mb20 font-black f-r f-a-c f-j-s">
          <span>{{item.name}}</span>
          <span class="font-default font14" v-show="item.isdefault=='是'">默认</span>
        </div>
        <div class="mb10">{{item.tel}}</div>
        <div style="line-height:20px">{{item.addr}}</div>

        <div class="p-a f-r f-a-c font-default" style="bottom:10px;right:10px">
          <div class="mr10 zs" @click="edit(item)">修改</div>
          <div class="zs" @click="del(item)">删除</div>
        </div>
      </div>
    </div>

    <el-dialog title="收货地址" :visible="show" @close="close" width="700px">
      <el-form ref="addForm" :model="form" class="addForm pl50 pr50 pt20" label-position="right" size="small">
        <el-form-item label="">
          <el-input v-model="form.name" placeholder="请输入收件人"></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="form.tel" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="form.addr" placeholder="请输入收货地址" type="textarea" rows="5"></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-radio label="是" v-model="form.isdefault"></el-radio>
          <el-radio label="否" v-model="form.isdefault"></el-radio>
        </el-form-item>
      </el-form>
      <div class="pb40 t-c pl50 pr50">
        <el-button class="w100" type="primary" @click="submit">确定</el-button>
      </div>
    </el-dialog>
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
      form: {
        name: "",
        tel: "",
        addr: "",
        isdefault: "否",
      },
      show: false,
      list: [],
    };
  },
  created() {
    this.getData();
  },
  mounted() {},
  beforeDestroy() {},
  computed: {
    ...mapGetters(["userinfo"]),
  },
  methods: {
    //获取数据
    getData() {
      api
        .addrslist({
          limit: 9999,
          offset: 1,
          uid: this.userinfo.id,
        })
        .then((res) => {
          this.list = res.data.list;
        });
    },

    //修改
    edit(item) {
      this.form = JSON.parse(JSON.stringify(item));
      this.show = true;
    },

    //关闭
    close() {
      this.resetData("form", this);
      this.show = false;
    },

    //删除
    del(item) {
      api
        .addrsdel({
          id: item.id,
        })
        .then((res) => {
          if (res.code == 200) {
            this.$message.success("删除成功!");
            this.getData();
          }
        });
    },

    //提交
    async submit() {
      if (this.form.isdefault == "是") {
        await api.allnodefault({
          uid: this.userinfo.id,
        });
      }
      api
        .addrssave({
          ...this.form,
          uid: this.userinfo.id,
        })
        .then((res) => {
          if (res.code == 200) {
            this.$message.success("保存成功!");
            this.close();
            this.getData();
          }
        });
    },
  },
};
</script>

<style lang='scss' scoped>
.useraddr {
}
.addr {
  .addr-row {
    width: 270px;
    height: 180px;
    border: 1px solid #e0e0e0;
  }
  .addr-row-active {
    border: 1px solid #ff734d;
  }
  .default {
    color: #b0b0b0;
  }
}
</style>