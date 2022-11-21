<template>
  <div class="home p-r bg">

    <Head @search="getData" :showSearch="true" />
    <div class="home-content">
      <div class="logo mt20 mb20 f-r f-a-c f-j-s center">
        <div class="font-default font30"></div>
        <el-input placeholder="请输入搜索内容" style="width:400px" v-model="search" class="search">
          <el-button slot="append" icon="el-icon-search" @click="getData"></el-button>
        </el-input>
      </div>

      <div class="bor">
        <div class="center f-r r-a-c types p-r">
          <div class="type-row font-white t-c" style="background:#409EFF;width:250px;color:#fff">
            商品分类
          </div>
          <div @click="$router.push('/search?type='+item.name)" class="type-row type-default  t-c pl20 pr20 font-w600 zs" v-for="item in types" :key="item.id">
            {{item.name}}
          </div>
        </div>
      </div>

      <div class="home-content" style="min-height:900px">
        <div class="font22 mb20 mt40 center mb60" style="font-weight:200;color:#333">搜索结果</div>
        <div class="row-father center" v-for="item in list" :key="item.type">
          <div class="rows">
            <div v-for="goods in item.goods" :key="goods.id" class="row zs" @click="$router.push('/goods?id='+goods.id)">
              <div class="row-content pb10 p10">
                <img style="height:200px;width:100%" :src="goods.img" lazy="loaded">
                <div class="font14 t-c mt20 font-hidden">
                  {{goods.name}}
                </div>
                <p class="font14 font-black t-c">{{goods.price}}元</span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import Head from "@/components/Head";
import Footer from "@/components/Footer";
import * as api from "@/api/api";

export default {
  components: { Head, Footer },
  data() {
    return {
      offset: 1, //当前页
      limit: 10, //每页大小
      total: 0, //总记录数
      hidden: false, //分页是否隐藏b
      slideList: [require("@/assets/bg1.jpg"), require("@/assets/bg2.jpg")],
      list: [],
      search: "",
      types: [],
      type: "",
    };
  },
  created() {
    this.$route.query.type && (this.type = this.$route.query.type);
    this.$route.query.search && (this.search = this.$route.query.search);
    this.getData();
    this.getTypes();
  },
  mounted() {},
  methods: {
    //获取商品
    getData() {
      api
        .goodslist({
          type: this.type,
          name: this.search,
          offset: this.offset,
          limit: this.limit,
          status: "上架",
        })
        .then((res) => {
          if (res.code == 200) {
            if (res.data.list.length > 0) {
              let arr = {};
              let newarr = [];
              res.data.list.forEach((item, index) => {
                if (!arr[item.type]) {
                  arr[item.type] = [];
                }
                arr[item.type].push(item);
              });

              Object.keys(arr).forEach((item) => {
                newarr.push({
                  type: item,
                  goods: arr[item],
                });
              });
              this.list = newarr;
            } else {
              this.list = [];
            }
          }
        });
    },

    //获取分类
    getTypes() {
      api
        .typelist({
          limit: 999,
          offset: 1,
        })
        .then((res) => {
          this.types = res.data.list;
        });
    },
  },
  watch: {
    "$route.query.type"(val) {
      this.type = val;
      this.getData();
    },
  },
  computed: {},
};
</script>

<style lang="scss" scoped>
.center {
  width: 1200px;
  left: 50%;
  margin-left: -600px;
  position: relative;
}
.home {
  background-color: #eee;
  .home-content {
    overflow: hidden;
    ::v-deep .el-carousel__button {
      width: 10px;
      height: 10px;
      border-radius: 50%;
    }
    .logo {
      .search {
        input {
          border-radius: 0px;
        }
        ::v-deep .el-input__inner {
          border-top-left-radius: 20px;
          border-bottom-left-radius: 20px;
          background:#fff;
          border: none;
        }
        ::v-deep .el-input-group__append {
          background:#fff;
          border-radius: 0px;
          border-top-right-radius: 20px;
          border-bottom-right-radius: 20px;
          border: none;
          button {
            border-radius: 0px;
          }
        }
      }
    }
  }
  .rows {
    overflow: hidden;
    margin-left: -20px;
    .row {
      img {
        object-fit: cover;
      }
      .row-content {
        background: #fff;
      }
      width: 20%;
      float: left;
      padding-left: 20px;
      margin-bottom: 20px;
    }
  }
}
.bor {
  border-bottom: 2px solid #409EFF;
}
.types {
  width: 100%;
  overflow-x: scroll;
  &::-webkit-scrollbar {
    width: 7px;
    height: 0px;
  }
}
.type-row {
  height: 50px;
  line-height: 50px;
  min-width: 120px;
}
.type-default {
  color: #333;
  &:hover {
    color: #999;
  }
}
</style>

