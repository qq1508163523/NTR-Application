<template>
  <div class="search-container container">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>"{{keyword}}"</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="search-content-container">
      <Result :reqPage="reqPage" :result="searchResult"/>
    </div>
  </div>
</template>

<script>

import Result from "@/components/Result";

import {mapState} from "vuex";

export default {
  name: "Search",
  props: ['keyword'],
  components: {
    Result
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
  margin-top: 1.5rem;
  > .search-content-container{
    margin-top: 0.5rem;
    padding: 0.35rem;
    border-radius: 0.3125rem;
    box-shadow: 2px 2px 2px #c9c6c6;
    background-color: #ffffff;
  }
}

</style>