<template>
  <div class="home">

    <Head />

    <div class="f-r h100 mt20 my-menu center">
      <div class="my-left h100 mr20 pt40 pl40">
        <div class="my-left-row mb20 zs font15" @click="tabActive=item.component" :class="tabActive==item.component?'font-default':'font-grey'" v-for="item in tabbars" :key="item.name">{{item.name}}</div>
      </div>
      <Middle :showBread="false" :showSearch="false" class="my-right w100 h100">
        <div slot class="w100 h100">
          <component :is="tabActive" :name="item.component" v-for="item in tabbars" :key="item.component" v-if="tabActive==item.component" />
        </div>
      </Middle>
    </div>

    <Footer />
  </div>
</template>

<script>
import Head from "@/components/Head";
import Footer from "@/components/Footer";
import Middle from "@/components/Middle";
import Userinfo from "./components/Userinfo";
import Userorders from "./components/Userorders";
import Useraddrs from "./components/Useraddrs";
import * as api from "@/api/api";

export default {
  components: { Head, Middle, Footer, Userinfo, Userorders, Useraddrs },
  data() {
    return {
      tabActive: "Userinfo",
      tabbars: [
        {
          component: "Userinfo",
          name: "个人信息"
        },
        {
          component: "Userorders",
          name: "我的订单"
        },
        {
          component: "Useraddrs",
          name: "收货地址"
        }
      ]
    };
  },
  created() {
    if (this.$route.query.component) {
      this.tabActive = this.$route.query.component;
    }
  },
  watch: {
    "$route.query.component": {
      immediate: true,
      handler(newval, oldval) {
        if (newval != oldval) {
          this.tabActive = newval;
        }
      }
    }
  },
  mounted() {},
  methods: {},
  computed: {}
};
</script>

<style lang="scss" scoped>
.home {
  background-color: #f6f6f6;
  min-height: 800px;
  background-size: 100% 100%;
}

.my-menu {
  .my-left {
    width: 300px;
    height: 500px;
    background: #fff;
  }
  .my-right {
    padding: 20px;
    min-height: 700px;
    background-color: #fff;
  }
}

.center {
  width: 1200px;
  position: relative;
  left: 50%;
  margin-left: -600px;
}
</style>

