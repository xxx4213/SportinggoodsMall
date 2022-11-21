<template>
  <div :class="{'hidden':hidden}" class="pagination-container">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background :current-page="offset" :page-sizes="pageSizes" :page-size="limit" :layout="layout" :total="total" prev-text="上一页" next-text="下一页" class="page-content">
    </el-pagination>
  </div>
</template>

<script>
// import { scrollTo } from '@/utils/scrollTo'

export default {
  name: "Pagination",
  props: {
    total: {
      //总记录数
      required: true,
      type: Number
    },
    offset: {
      //当前页
      type: Number,
      default: 1
    },
    limit: {
      //每页大小
      type: Number,
      default: 10
    },
    pageSizes: {
      //每页大小组
      type: Array,
      default() {
        return [10, 20, 30, 40, 50];
      }
    },
    layout: {
      //所需组件
      type: String,
      default: "total, sizes, prev, pager, next, jumper"
    },
    hidden: {
      //是否隐藏
      type: Boolean,
      default: false
    }
  },
  methods: {
    handleSizeChange(val) {
      this.$emit("update:limit", val);
      this.$emit("changePage");
      let time = setTimeout(() => {
        scrollTo(0, 0);
      }, 100);
    },
    handleCurrentChange(val) {
      this.$emit("update:offset", val);
      this.$emit("changePage");
      let time = setTimeout(() => {
        scrollTo(0, 0);
      }, 100);
    }
  }
};
</script>

<style lang="scss" scoped>
.pagination-container {
  background: #fff;
  padding: 15px 16px;
}
.pagination-container.hidden {
  display: none;
}
.page-content {
  text-align: center;
  ::v-deep .btn-prev {
    padding: 0 10px;
  }
  ::v-deep .btn-next {
    padding: 0 10px;
  }
}
</style>
