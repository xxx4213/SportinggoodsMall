<template>
  <div class="home bg">

    <Head :showSearch="false" />

    <Middle :showBread="false" :showSearch="false">
      <div slot class="content m20" v-show="tableData.length">
        <el-table :data="tableData" style="width: 100%" header-align="center" height="850px" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column prop="date" label="商品" width="180" align="center">
            <template slot-scope="scope">
              <img :src="scope.row.img" style="width:150px;height:100px;object-fit:contain" align="center">
            </template>
          </el-table-column>
          <el-table-column prop="name" label="名称" width="180" align="center" />
          <el-table-column prop="type" label="分类" width="180" align="center" />
          <el-table-column prop="price" label="单价" align="center" />
          <el-table-column prop="date" label="数量" width="190" align="center">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.buynum" @change="handleChange(scope.row.buynum,scope.$index)" :min="1" :max="scope.row.num" label="描述文字"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column prop="price" label="操作" align="center">
            <template slot-scope="scope" class="t-c" align="center">
              <span class="font-default zs" @click="del(scope.$index)">删除</span>
            </template>
          </el-table-column>
        </el-table>

        <div class="f-r f-a-c f-j-s">
          <div class="font-default pl20 zs" @click="delall">
            删除
          </div>
          <div class="f-r f-a-c f-j-r mt20">
            <div class="font14">总计金额：</div>
            <div class="font20 font-red m20">¥ {{sumPrice}}</div>
            <el-button type="danger" size="medium" class="btn" style="width:150px" @click="settlement">结算</el-button>
          </div>
        </div>
      </div>

      <div slot class="content m20 f-c f-a-c f-j-c" v-show="!tableData.length" style="height:800px">
        <el-empty description="购物车为空"></el-empty>
        <el-button type="default" @click="$router.push('/')">返回首页</el-button>
      </div>
    </Middle>

    <el-dialog title="请选择收货地址" :visible="show" class="login" width="950px" @close="show=false">
      <div class="f-r f-a-c f-w-t">
        <div class="addr-row  mr20 p20 mb20 font14 font-grey p-r zs" v-for="item in addrs" :key="item.id" @click="addrActive=item" :class="JSON.stringify(addrActive)==JSON.stringify(item)?'addr-active':''">
          <div class="font18 mb20 font-black f-r f-a-c f-j-s">
            <span>{{item.name}}</span>
            <span class="font-default font14" v-show="item.isdefault=='是'">默认</span>
          </div>
          <div class="mb10">{{item.tel}}</div>
          <div style="line-height:20px">{{item.addr}}</div>
        </div>
      </div>
      <div class="pb40 t-c pl50 pr50 pt40">
        <el-button style="width:250px" type="primary" @click="pay">提交订单</el-button>
      </div>
    </el-dialog>

    <Footer />
  </div>
</template>

<script>
import Head from "@/components/Head";
import Footer from "@/components/Footer";
import Middle from "@/components/Middle";
import * as api from "@/api/api";
import { mapGetters, mapMutations } from "vuex";
import { Loading } from "element-ui";

export default {
  components: { Head, Middle, Footer },
  data() {
    return {
      tableData: [],
      show: false,
      addrs: [],
      addrActive: {},
      multipleSelection: [],
    };
  },
  created() {
    this.getAddrs();
  },
  mounted() {
    if (!this.goods || this.goods.length == 0) {
      this.tableData = [];
    } else {
      this.tableData = this.goods;
    }
  },
  methods: {
    ...mapMutations(["setGoods"]),

    // 全选
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    // 删除
    delall() {
      if (!this.multipleSelection.length) {
        this.$message.error("没有选中商品!");
        return;
      }
      let result = [];
      for (let i = 0; i < this.tableData.length; i++) {
        let obj = this.tableData[i];
        let id = obj.id;
        let isExist = false;

        for (let j = 0; j < this.multipleSelection.length; j++) {
          let aj = this.multipleSelection[j];
          let n = aj.id;
          if (n == id) {
            isExist = true;
            break;
          }
        }
        if (!isExist) {
          result.push(obj);
        }
      }

      this.tableData = result;
      this.setGoods(result);
    },

    //获取收货地址
    getAddrs() {
      api
        .addrslist({
          limit: 9999,
          offset: 1,
          uid: this.userinfo.id,
        })
        .then((res) => {
          this.addrs = res.data.list;
          res.data.list.forEach((i) => {
            if (i.isdefault == "是") {
              this.addrActive = i;
            }
          });
        });
    },

    //改变购物车数量
    handleChange(num, index) {
      this.tableData[index].buynum = num;
      this.setGoods(this.tableData);
    },

    //删除
    del(index) {
      this.tableData.splice(index, 1);
      this.$message.success("删除成功!");
      this.setGoods(this.tableData);
    },

    // 点击结算
    settlement() {
      if (this.multipleSelection.length == 0) {
        this.$message.error("请选择结算物品!");
        return;
      }
      this.show = true;
    },

    //下单
    async pay() {
      if (this.goods.length < 0) {
        return;
      }

      if (!this.addrActive.id) {
        this.$message.error("请选择收货地址!");
        return;
      }

      this.show = false;

      let loading = Loading.service({
        body: true,
        text: "支付中",
      });

      let code = this.$tool.random(15487456321, 98745621589);
      let promiseAll = [];
      for (const key in this.multipleSelection) {
        let params = {
          code: code,
          num: this.multipleSelection[key]["buynum"],
          price: this.multipleSelection[key]["price"],
          gname: this.multipleSelection[key]["name"],
          img: this.multipleSelection[key]["img"],
          gid: this.multipleSelection[key]["id"],
          type: this.multipleSelection[key]["type"],
          gid: this.multipleSelection[key]["id"],
          saddr: this.addrActive["addr"],
          stel: this.addrActive["tel"],
          sname: this.addrActive["name"],
          uid: this.userinfo.id,
          status: "未发货",
        };
        promiseAll.push(await api.orderssave(params));
      }

      Promise.all(promiseAll).then((res) => {
        let result = [];
        for (let i = 0; i < this.tableData.length; i++) {
          let obj = this.tableData[i];
          let id = obj.id;
          let isExist = false;

          for (let j = 0; j < this.multipleSelection.length; j++) {
            let aj = this.multipleSelection[j];
            let n = aj.id;
            if (n == id) {
              isExist = true;
              break;
            }
          }
          if (!isExist) {
            result.push(obj);
          }
        }

        setTimeout(() => {
          loading.close();
          this.getAddrs();
          this.$message.success("下单成功!");
          this.tableData = result;
          this.setGoods(result);
        }, 1500);
      });
    },
  },
  computed: {
    ...mapGetters(["goods", "goodsNum", "userinfo"]),

    sumPrice() {
      let sum = 0;
      if (this.multipleSelection.length == 0) {
        return 0;
      }
      this.multipleSelection.map((item) => {
        sum += item.buynum * item.price;
      });
      return sum;
    },
  },
};
</script>

<style lang="scss" scoped>
.home {
  background-color: #f6f6f6;
  height: 100%;
}

.bg {
  background-size: 100% 100%;
}
.content {
  padding: 20px;
  width: 1200px;
  position: relative;
  left: 50%;
  margin-left: -600px;
  background-color: #fff;
  // box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  // height: 950px;
  overflow-y: scroll;
}

.btn {
  background-color: #409EFF;
  background: linear-gradient(90deg, #409EFF 0%, #409EFF 100%);
  // height: 50px;
  // width: 200px;
}

.addr-row {
  width: 270px;
  height: 180px;
  border: 1px solid #e0e0e0;
}
.addr-active {
  border: 1px solid #409EFF;
}
.default {
  color: #b0b0b0;
}
</style>

