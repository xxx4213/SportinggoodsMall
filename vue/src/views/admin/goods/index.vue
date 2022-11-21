<template>
  <div class="dashboard-container">
    <el-form ref="searchForm" :model="searchForm" inline size="medium" class="searchForm" label-width="80px">
      <el-form-item label="商品名称:">
        <el-input v-model="searchForm.name" placeholder="" style="width:250px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getData()" icon="el-icon-search">查询</el-button>
        <el-button @click="addForm.type = typeList[0].name;showDialog=true" icon="el-icon-plus" plain>新增</el-button>
      </el-form-item>
    </el-form>

    <el-card slot class="content-card" shadow="never">
      <el-table :data="list" border stripe style="width: 100%;min-height: 60vh;">
        <el-table-column label="序号" header-align="center" align="center" type="index" width="70">
        </el-table-column>
        <el-table-column prop="name" label="商品名" header-align="center" align="center" />
        <el-table-column prop="img" label="商品图片" header-align="center" align="center">
          <template slot-scope="scope">
            <div>
              <img :src="scope.row.img" style="width:100px;height:100px;object-fit: cover;">
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="分类" header-align="center" align="center" />
        <el-table-column prop="price" label="价格" header-align="center" align="center" />
        <el-table-column prop="num" label="数量" header-align="center" align="center" />
        <el-table-column prop="time" label="时间" header-align="center" align="center" />
        <el-table-column prop="time" label="状态" header-align="center" align="center">
          <template slot-scope="scope">
            <div>
              <el-switch v-model="scope.row.status" active-color="#13ce66" inactive-color="#ff4949" active-value="上架" inactive-value="下架" @change="change(scope.row)" />
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="address" label="操作" header-align="center" align="center" width="190px">
          <template slot-scope="scope">
            <div>
              <a style="margin-left:20px;color:rgb(31, 144, 254);" @click="edit(scope.row)">编辑</a>
              <a style="margin-left:20px;color:rgb(31, 144, 254);" @click="del(scope.row)">删除</a>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <Pagination :limit.sync="limit" :total="total" :offset.sync="offset" :hidden="hidden" @changePage="getData" />
    </el-card>

    <!-- 增加编辑 -->
    <Dialog :showDialog.sync="showDialog" title="" @cancal="close" @confirm="confirm" width="850">
      <div slot>
        <el-form ref="addForm" :model="addForm" label-width="120px" class="addForm" label-position="right" size="small">
          <el-form-item label="上传商品图片:">
            <el-upload class="avatar-uploader" :action="url" :show-file-list="false" :on-success="imgSuccess">
              <img v-if="addForm.img" :src="addForm.img" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="商品名:">
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
          <el-form-item label="分类:">
            <el-select v-model="addForm.type" placeholder="请选择分类" style="width:100%">
              <el-option v-for="item in typeList" :key="item.id" :label="item.name" :value="item.name" />
            </el-select>
          </el-form-item>
          <el-form-item label="价格:">
            <el-input v-model="addForm.price"></el-input>
          </el-form-item>
          <el-form-item label="数量:">
            <el-input v-model="addForm.num"></el-input>
          </el-form-item>
          <el-form-item label="商品详情:">
            <Enduit v-model="addForm.content" />
          </el-form-item>
        </el-form>
      </div>
    </Dialog>

  </div>
</template>

<script>
import Middle from "@/components/Middle/index.vue";
import Dialog from "@/components/Dialog/index.vue";
import Pagination from "@/components/Pagination/index.vue";
import Enduit from "@/components/WangEnduit/index.vue";
import * as api from "@/api/api";

export default {
  components: { Middle, Dialog, Pagination, Enduit },
  data() {
    return {
      url: process.env.VUE_APP_BASE_FILE,
      offset: 1, //当前页
      limit: 10, //每页大小
      total: 0, //总记录数
      hidden: false, //分页是否隐藏
      searchForm: {
        name: "",
      },
      urlList: [],
      addForm: {
        name: "",
        img: "",
        type: "",
        price: "",
        content: "",
        num: "",
        status: "上架",
      },
      list: [],
      typeList: [],
      showDialog: false,
    };
  },
  created() {
    this.getData();
    this.getType();
  },
  mounted() {},
  methods: {
    //获取列表
    getData() {
      api
        .goodslist({
          name: this.searchForm.name,
          offset: this.offset,
          limit: this.limit,
        })
        .then((res) => {
          if (res.code == 200) {
            this.list = res.data.list;
            this.total = res.data.total;
          }
        });
    },

    // 修改状态
    change(e) {
      api.goodssave(e).then((res) => {
        if (res.code == 200) {
          this.$message.success("操作成功!");
          this.showDialog = false;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    //获取类型列表
    getType() {
      api
        .typelist({
          offset: 1,
          limit: 9999,
        })
        .then((res) => {
          if (res.code == 200) {
            this.typeList = res.data.list;
            this.addForm.type = res.data.list[0].name;
          }
        });
    },

    //大图上传
    imgSuccess(file) {
      this.addForm.img = file.data;
    },

    //模态框-关闭
    close() {
      this.showDialog = false;
      let time = setTimeout(() => {
        this.urlList = [];
        this.resetData("addForm", this); //重置数据
      }, 300);
    },

    //删除
    async del(item) {
      await api
        .goodsdel({
          id: item.id,
        })
        .then((res) => {
          if (res.code == 200) {
            this.$message.success("操作成功!");
          } else {
            this.$message.error(res.msg);
          }
        });

      await this.getData();
    },

    // 新增/编辑-提交
    async confirm() {
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
      await api.goodssave(this.addForm).then((res) => {
        if (res.code == 200) {
          this.$message.success("操作成功!");
          this.showDialog = false;
        } else {
          this.$message.error(res.msg);
        }
      });

      await this.getData();
    },

    //编辑
    edit(item) {
      this.addForm = JSON.parse(JSON.stringify(item)); //对象拷贝防止重置对象时污染
      this.showDialog = true;
    },
  },
  computed: {},
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
  border: 1px #c7c7c7 dashed;
  border-radius: 10px;
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
