<template>
  <div class="home p-r bg">

    <Head @search="getData" :showSearch="true" />
    <div class="search-row">
      <div class="logo mt20 mb20 f-r f-a-c f-j-s">
        <div class="font-default font30"></div>
      </div>
    </div>

    <div class="goods-content center">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>商品详情</el-breadcrumb-item>
        <el-breadcrumb-item>{{info.type}}</el-breadcrumb-item>
        <el-breadcrumb-item>{{info.name}}</el-breadcrumb-item>
      </el-breadcrumb>

      <div class="row1 mt20 f-r f-a-l ">
        <img :src="info.img" style="width:460px;height:500px;border: 1px #c7c7c7 solid;object-fit: cover">
        <div class="f-c ml30 w100 p-r" style="height:500px">
          <div class="font-w600 font22">{{info.name}}</div>
          <p>{{info.tip}}</p>
          <div class="price font14 p10 w100">
            售价
            <span class="font30 ml40">¥{{info.price}}</span>
          </div>
          <div class="type font14 p10 w100 font-grey">
            分类
            <span class="ml40 font-black">{{info.type}}</span>
          </div>
          <div class="type font14 p10 w100 font-grey">
            库存
            <span class="ml40 font-black">{{info.num}}</span>
          </div>
          <div class="p-a btn">
            <el-button type="danger" round class="car" @click="addcar(info)">加入购物车</el-button>
          </div>
        </div>
      </div>

      <div class="row2 mt20">
        <el-tabs v-model="activeName">
          <el-tab-pane label="商品详情" name="details" v-html="info.content" class="details-content"></el-tab-pane>
          <el-tab-pane :label="`用户评价(${commits.length})`" name="commit">
            <div class="commit-row f-r mb50" v-for="item in commits" :key="item.id">
              <img :src="item.uimg" style="width:45px; height:45px; border-radius:50%;" class="mr10">
              <div style="width:100px;height:45px;line-height:45px">{{item.uname}}</div>
              <div class="w100 f-c">
                <div class="mt10 mb10">{{item.content}}</div>
                <div>
                  <img :src="img" class="mb10 mr10" style="width:80px;height:80px;" v-for="img in item.img.split(',')" :key="img">
                </div>
                <div class="t-r font-grey font13">{{item.time}}</div>
                <el-divider />
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import Head from "@/components/Head";
import Footer from "@/components/Footer";
import * as api from "@/api/api";
import { mapGetters, mapMutations } from "vuex";

export default {
  components: { Head, Footer },
  data() {
    return {
      id: "",
      activeName: "details",
      rate: 5,
      info: {},
      commits: [],
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.getData();
    this.getCommit();
  },
  computed: {
    ...mapGetters(["userinfo"]),
  },
  mounted() {},
  methods: {
    ...mapMutations(["add"]),
    //获取商品
    getData() {
      api
        .goodsone({
          id: this.id,
        })
        .then((res) => {
          if (res.code == 200) {
            this.info = res.data;
          }
        });
    },

    //获取评论
    getCommit() {
      api
        .commitlist({
          gid: this.id,
          limit: 9999,
          offset: 1,
        })
        .then((res) => {
          if (res.code == 200) {
            this.commits = res.data.list;
          }
        });
    },

    //加入购物车
    addcar(item) {
      if (Object.keys(this.userinfo).length == 0) {
        this.$message.error("请先登录!");
        return;
      } else {
        this.add(item);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.home {
  background-color: #fff;
  .search-row {
    width: 1200px;
    left: 50%;
    margin-left: -600px;
    position: relative;
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
  }
  .goods-content {
    min-height: 70vh;
    ::v-deep .el-tabs__item {
      font-size: 16px;
    }
    ::v-deep .el-rate__icon {
      font-size: 22px;
    }
    .row1 {
      .price {
        color: #fff;
        background-color: #409EFF;
        background: linear-gradient(90deg, #409EFF 0%, #409EFF 100%);
        border-radius: 8px 8px 0 0;
      }
      .type {
        background-color: #f7f9fa;
      }
      .btn {
        bottom: 0;
        width: 300px;
        left: 50%;
        margin-left: -150px;
        button {
          border-radius: 30px;
        }
        .car {
          background-color: #409EFF;
          background: linear-gradient(90deg, #409EFF 0%, #409EFF 100%);
          height: 50px;
          width: 200px;
        }
      }
    }
  }
}
.details-content {
  ::v-deep img {
    width: 100%;
  }
}
.center {
  width: 1200px;
  position: relative;
  left: 50%;
  margin-left: -600px;
}
</style>

