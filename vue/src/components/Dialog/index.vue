<template>
  <el-dialog :title="title" :visible.sync="show" :width="width+'px'" :append-to-body="appendBody" @close="$emit('cancal')" class="dialog" @open="$emit('open')" :close-on-click-modal="modalClose">
    <slot></slot>
    <span slot="footer" class="dialog-footer" v-show="showButton">
      <el-button @click="$emit('cancal')" size="medium">取消</el-button>
      <el-button type="primary" @click="$emit('confirm')" size="medium" :loading="loading">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
//import x from ''
export default {
  components: {},
  props: {
    showDialog: {
      type: Boolean
    },
    loading: {
      type: Boolean
    },
    title: {
      type: String
    },
    width: {
      type: String,
      default: '650'
    },
    showButton: {
      type: Boolean,
      default: true
    },
    modalClose: {
      type: Boolean,
      default: false
    },
    appendBody: {
      //是否嵌套
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      show: this.showDialog
    }
  },
  watch: {
    showDialog() {
      this.show = this.showDialog
    }
  },
  created() {},
  mounted() {},
  beforeDestroy() {},
  computed: {},
  methods: {}
}
</script>

<style lang='scss' scoped>
.dialog {
  >>> .el-dialog__header {
    padding: 10px 0;
    margin: 0 20px 10px;
    border-bottom: 1px #d2d2d2 solid;
    span {
      font-size: 16px;
    }
    >>> .el-dialog__headerbtn {
      top: 15px;
      right: 20px;
    }
  }
  >>> .el-dialog__footer {
    text-align: right;
    >>> .el-button {
      width: 100px;
    }
  }
}
</style>