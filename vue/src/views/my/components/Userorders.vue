<template>
  <div class="userorders">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>我的订单</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="logo w100 mt20   f-r f-j-r">
      <el-input placeholder="请输入订单号" style="width:300px" v-model="code" class="search">
        <el-button slot="append" icon="el-icon-search" @click="getData"></el-button>
      </el-input>
    </div>

    <el-tabs v-model="activeName" class="mb20" @tab-click="getData">
      <el-tab-pane label="全部" name=""></el-tab-pane>
      <el-tab-pane label="未发货" name="未发货"></el-tab-pane>
      <el-tab-pane label="已发货" name="已发货"></el-tab-pane>
    </el-tabs>

    <div class="userorders-row w100 p30 mb20" v-for="item in list" :key="item.code">
      <div class="font18 font-grey f-r f-a-c f-j-s mb20">
        <span>订单号: {{item.code}}</span>
        <span class="font15" :class="item.status=='未发货'?'font-default':''">{{item.status}}</span>
      </div>
      <div class="f-r f-a-r f-j-s font14  mb20">
        <div>
          {{item.time}}
          <el-divider direction="vertical"></el-divider>
          {{item.sname}}
          <el-divider direction="vertical"></el-divider>
          {{item.stel}}
        </div>

        <div class="font-grey">
          实付金额： <span class="font28 font-black">{{sumPrice(item.goods)}}</span> 元
        </div>
      </div>
      <div class="font14">
        地址: {{item.saddr}}
      </div>
      <div class="goods-right mt20" v-if="!item.iscommit && item.status=='已发货'">
        <div class="btn font12 font-grey zs" @click="open(item)">
          订单评价
        </div>
      </div>
      <div class="t-r font-default zs" @click="item.showDetail = !item.showDetail">{{!item.showDetail?'查看详情':'收起'}}</div>
      <div v-show="item.showDetail">
        <el-divider></el-divider>
        <div class="userorders-goods w100 f-r f-j-s">
          <div class="goods-left">
            <div class="goods-row mb20 f-r f-a-c" v-for="(goods,index) in item.goods" :key="index">
              <img :src="goods.img" style="height:80px;width:80px;">
              <div class="ml20 font14 f-c">
                <span style="margin-bottom:15px;">{{goods.gname}}</span>
                <span>{{goods.price}}元 x {{goods.num}}</span>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

    <el-dialog title="" :visible="show" class="login" width="400px" @close="close">
      <div class="t-c font17 font-default">评论</div>
      <el-form ref="addForm" :model="form" class="addForm pl50 pr50 pb10 pt20" label-position="right" size="small">
        <el-form-item label="">
          <el-upload class="avatar-uploader" :action="url" :show-file-list="false" :on-success="imgSuccess">
            <div class="f-r f-a-c">
              <div class="p-r f-r f-a-c" v-for="(item,index) in form.img" :key="index" v-show="form.img.length>0">
                <img :src="item" class="avatar mr10" style="border-radius:6px">
                <i class="el-icon-close font18 p-a" style="top:0px;right:10px;" @click.stop="del(index)"></i>
              </div>
              <div v-show="form.img.length<3" class="f-r f-a-c f-j-c noimg">
                <i class="el-icon-plus"></i>
              </div>
            </div>
          </el-upload>
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="form.content" placeholder="请输入评论内容" rows="5" type="textarea"></el-input>
        </el-form-item>
        <div class="w100">
          <el-button class="w100" type="primary" @click="submit">提交评论</el-button>
        </div>
      </el-form>
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
      activeName: "",
      code: "",
      show: false,
      form: {
        content: "",
        img: [],
        code: "",
        gid: "",
      },
      list: [],
    };
  },
  created() {},
  mounted() {
    this.getData();
  },
  beforeDestroy() {},
  computed: {
    ...mapGetters(["userinfo"]),

    //计算总价
    sumPrice() {
      return (item) => {
        let sum = 0;
        item.forEach((i) => {
          sum += i.price * i.num;
        });
        return sum;
      };
    },
  },
  methods: {
    //获取列表
    getData() {
      api
        .orderslist({
          code: this.code,
          uid: this.userinfo.id,
          offset: 1,
          limit: 9999,
          status: this.activeName == "0" ? "" : this.activeName,
        })
        .then((res) => {
          if (res.code == 200) {
            let arr = {};
            let newarr = [];
            if (res.data.list.length != 0) {
              res.data.list.forEach((item, index) => {
                if (!arr[item.code]) {
                  arr[item.code] = [];
                }
                arr[item.code].push(item);
              });

              Object.keys(arr).forEach((item) => {
                newarr.push({
                  code: item,
                  iscommit: arr[item][0].cid ? true : false,
                  time: arr[item][0].time,
                  saddr: arr[item][0].saddr,
                  sname: arr[item][0].sname,
                  stel: arr[item][0].stel,
                  status: arr[item][0].status,
                  goods: arr[item],
                  showDetail: false,
                });
              });
              //设置默认展开行
              this.defaultCode = newarr[0].code;
            }
            this.list = newarr;
            this.total = res.data.total;
          }
        });
    },

    //打开评价
    open(item) {
      this.form.code = item.code;
      this.show = true;
    },

    //上传图片
    imgSuccess(file) {
      this.form.img.push(file.data);
    },

    //关闭
    close() {
      this.resetData("form", this);
      this.show = false;
    },

    //删除
    del(index) {
      this.form.img.splice(index, 1);
    },

    //提交评论
    submit() {
      if (this.form.img.length == 0) {
        this.$message.error("请上传图片!");
        return;
      }
      if (this.form.content == "") {
        this.$message.error("请填写评论!");
        return;
      }
      let promise = [];
      this.list.forEach((f) => {
        if (f.code == this.form.code) {
          promise = f.goods.map(async (i) => {
            return await api.commitsave({
              uid: this.userinfo.id,
              content: this.form.content,
              code: this.form.code,
              gid: i.gid,
              img: this.form.img.join(","),
            });
          });
        }
      });

      Promise.all(promise).then((result) => {
        this.$message.success("评论成功!");
        this.close();
        this.getData();
      });
    },
  },
};
</script>

<style lang='scss' scoped>
.userorders {
  .logo {
    .search {
      input {
        border-radius: 0px;
      }
      ::v-deep .el-input__inner {
        border-radius: 0px;
      }
      ::v-deep .el-input-group__append {
        background-color: #fff;
        border-radius: 0px;
        button {
          border-radius: 0px;
        }
      }
    }
  }
  .goods-right {
    .btn {
      text-align: center;
      width: 120px;
      height: 30px;
      line-height: 30px;
      color: #757575;
      border: 1px solid#e0e0e0;
    }
  }
  .userorders-row {
    border: 1px solid #e0e0e0;
    .userorders-goods {
      .goods-left {
        width: 700px;
      }
    }
  }
}
.login {
  ::v-deep .el-dialog__body {
    padding: 0 0 20px;
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
  border: 1px #c7c7c7 dashed;
  border-radius: 10px;
  font-size: 28px;
  color: #8c939d;
  width: 70px;
  height: 70px;
  line-height: 70px;
  text-align: center;
}
.avatar {
  width: 70px;
  height: 70px;
  display: block;
  object-fit: cover;
}
.noimg {
  width: 70px;
  height: 70px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
}
</style>