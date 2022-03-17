<template>
  <div class="category-container container">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>目录</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="category-content-container">
      <IndexTable :indexTable="indexTable"/>
      <Result :reqPage="reqPage" :result="categoryResult"/>
    </div>
  </div>
</template>

<script>

import IndexTable from "@/pages/Category/IndexTable";
import Result from "@/components/Result";

import {mapState} from "vuex";

export default {
  name: "Category",
  components: {
    IndexTable,
    Result
  },
  methods: {
    load(){
      this.$store.dispatch("category/getIndexTable");
      this.$store.dispatch("category/getCategoryResult");
    },
    reqPage(newPage){
      this.categoryQuery.from = newPage;
      this.$store.dispatch("category/getCategoryResult");
    }
  },
  computed:{
    ...mapState('category',['indexTable','categoryResult','categoryQuery'])
  },
  mounted() {
    this.load();
  }
}
</script>

<style lang="scss" scoped>

.category-container{
  margin-top: 1.5rem;
  > .category-content-container{
    margin-top: 0.5rem;
    padding: 0.35rem;
    border-radius: 0.3125rem;
    box-shadow: 2px 2px 2px #c9c6c6;
    background-color: #ffffff;
  }
}

</style>