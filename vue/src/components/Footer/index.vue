<template>
  <div class="footer" v-if="false">
    <!-- <ul class="f-r f-a-c f-j-s center pt20 font15 row1">
      <li class="f-r f-a-c f-j-c w100"> <i class="el-icon-setting mr10 font20" /> 预约维修服务</li>
      <el-divider direction="vertical" class="h100"></el-divider>
      <li class="f-r f-a-c f-j-c w100"><i class="el-icon-refresh-right mr10 font20" /> 7天无理由退货</li>
      <el-divider direction="vertical" class="h100"></el-divider>
      <li class="f-r f-a-c f-j-c w100"><i class="el-icon-refresh mr10 font20" /> 15天免费换货</li>
      <el-divider direction="vertical" class="h100"></el-divider>
      <li class="f-r f-a-c f-j-c w100"><i class="el-icon-goods mr10 font20" /> 满99包邮</li>
      <el-divider direction="vertical" class="h100"></el-divider>
      <li class="f-r f-a-c f-j-c w100"><i class="el-icon-headset mr10 font20" /> 全国售后</li>
    </ul>
    <div class="center">
      <el-divider />
    </div>

    <div class="footer-rows f-r f-j-s center">
      <ul class="f-c f-a-c f-j-c font13 font-grey">
        <li class="font-black font15 mb20">帮助中心</li>
        <li class="mb10">账户管理</li>
        <li class="mb10">购物指南</li>
        <li class="mb10">订单操作</li>
      </ul>
      <ul class="f-c f-a-c font13 font-grey">
        <li class="font-black font15 mb20">服务支持</li>
        <li class="mb10">售后服务</li>
        <li class="mb10">自助服务</li>
        <li class="mb10">相关下载</li>
      </ul>
      <ul class="f-c f-a-c font13 font-grey">
        <li class="font-black font15 mb20">线下门店</li>
        <li class="mb10">服务网点</li>
        <li class="mb10">授权网点</li>
      </ul>
      <ul class="f-c f-a-c font13 font-grey">
        <li class="font-black font15 mb20">关注我们</li>
        <li class="mb10">新浪微博</li>
        <li class="mb10">官方微博</li>
        <li class="mb10">联系我们</li>
      </ul>
      <ul class="f-c f-a-c font13 font-grey">
        <li class="font-black font15 mb20">特色服务</li>
        <li class="mb10">绿色通道</li>
        <li class="mb10">礼物码</li>
        <li class="mb10">防伪查询</li>
      </ul>
    </div> -->
  </div>
</template>

<script>
//import x from ''
export default {
  components: {},
  data() {
    return {};
  },
  created() {},
  mounted() {},
  beforeDestroy() {},
  computed: {},
  methods: {}
};
</script>

<style lang='scss' scoped>
.footer {
  min-height: 150px;
  background: #fff;
  li {
    cursor: pointer;
  }
  li:hover {
    color: rgb(255, 115, 77);
  }
  .row1 {
    ul {
      height: 40px;
    }
  }
  .center {
    width: 1200px;
    position: relative;
    left: 50%;
    margin-left: -600px;
  }
  .footer-rows {
    ul {
    }
  }
}
</style>