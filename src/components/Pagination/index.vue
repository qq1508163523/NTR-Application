<template>
  <div style="display: flex;justify-content: center">
    <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="pageNum"
        :page-size="pageSize"
        layout="prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "Pagination",
  props:['pageSize','total','reqPage'],
  data(){
    return {
      pageNum: 1
    }
  },
  methods: {
    handleCurrentChange(val) {
      this.reqPage(val);
    }
  },
  mounted() {
    this.$bus.$on('PaginationReset',()=>{
      this.pageNum = 1;
    });
  },
  beforeDestroy() {
    this.$bus.$off('PaginationReset');
  }
}
</script>

<style scoped>

</style>