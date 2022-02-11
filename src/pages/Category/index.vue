<template>
  <div class="category-container">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>目录</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="category-content-container">
      <IndexTable :indexTable="indexTable"/>
      <div v-show="categoryResult.dataList.length > 0">
        <div style="border-top: 1px solid #f1ecec;padding: 1rem 0">
          <Pagination :pageSize="24" :total="categoryResult.total" :reqPage="reqPage"/>
        </div>
        <div style="margin-top: 1rem">
          <div>
            <span style="font-weight: bold;font-size: 1rem">共{{categoryResult.total}}个结果</span>
          </div>
          <div>
            <ItemDisplay :itemList="categoryResult.dataList" :limitSize="24" :colSize="6" :gutter="12"/>
          </div>
        </div>
      </div>
      <el-empty v-show="categoryResult.dataList.length === 0" description="无搜索结果" :image-size="200"></el-empty>
    </div>
  </div>
</template>

<script>

import IndexTable from "@/pages/Category/IndexTable";
import Pagination from "@/components/Pagination";
import ItemDisplay from "@/components/ItemDisplay";

import {mapState} from "vuex";

export default {
  name: "Category",
  components: {
    IndexTable,
    Pagination,
    ItemDisplay
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
  width: 1140px;
  min-width: 1140px;
  margin: 1.5rem auto 0 auto;
  > .category-content-container{
    margin-top: 0.5rem;
    padding: 0.35rem;
    border-radius: 5px;
    box-shadow: 2px 2px 2px #c9c6c6;
    background-color: #ffffff;
  }
}

</style>