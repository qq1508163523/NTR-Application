<template>
  <div class="search-container">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>"{{this.keyword}}"</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="search-content-container">
      <div v-show="searchResult.dataList.length > 0">
        <div style="padding: 1rem 0">
          <Pagination :pageSize="24" :total="searchResult.total" :reqPage="reqPage"/>
        </div>
        <div style="margin-top: 1rem">
          <div>
            <span style="font-weight: bold;font-size: 1rem">共{{searchResult.total}}个结果</span>
          </div>
          <div>
            <ItemDisplay :itemList="searchResult.dataList" :limitSize="24" :colSize="6" :gutter="12"/>
          </div>
        </div>
      </div>
      <el-empty v-show="searchResult.dataList.length === 0" description="无搜索结果" :image-size="200"></el-empty>
    </div>
  </div>
</template>

<script>

import Pagination from "@/components/Pagination";
import ItemDisplay from "@/components/ItemDisplay";
import {mapState} from "vuex";

export default {
  name: "Search",
  props: ['keyword'],
  components: {
    Pagination,
    ItemDisplay
  },
  methods: {
    load(){
      this.$store.dispatch("search/getSearchResult");
    },
    reqPage(newPage){
      this.searchQuery.from = newPage;
      this.load();
    }
  },
  computed:{
    ...mapState('search',['searchResult','searchQuery'])
  },
  watch: {
    $route: {
      handler: function(from, to){
        this.searchQuery.keyword = this.keyword;
        this.load();
      },
      deep: true
    }
  },
  mounted() {
    this.searchQuery.keyword = this.keyword;
    this.load();
  }
}
</script>

<style lang="scss" scoped>

.search-container{
  width: 1140px;
  min-width: 1140px;
  margin: 1.5rem auto 0 auto;
  > .search-content-container{
    margin-top: 0.5rem;
    padding: 0.35rem;
    border-radius: 5px;
    box-shadow: 2px 2px 2px #c9c6c6;
    background-color: #ffffff;
  }
}

</style>