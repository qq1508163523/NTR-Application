<template>
  <div class="detail-container">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{branch.translatedName}}</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="el-row detail-content-container">
      <div class="el-col-17 right-container">
        <div class="right-up-container">
          <Intro :branch="branch" />
          <Playlist :episodeList="episodeList" :branchId="branchId" :currentEpisode="-99"/>
          <div class="intro-container">
            <div class="icon-title">
              <span style="font-size: 1.25rem">简介</span>
            </div>
            <div style="margin-top: 0.5rem">
              <span>{{branch.intro}}</span>
            </div>
          </div>
          <Relate :relateList="relateList"/>
        </div>
        <div class="right-down-container">
          <Comment :commentRoot="commentRoot" :branchId="branchId" :loadComment="loadComment"/>
        </div>
      </div>
      <div class="el-col-6 left-container">
        <SimilarRecommend :similarRecommendList="similarRecommendList" :limitSize="10"/>
      </div>
    </div>
  </div>
</template>

<script>

import {mapState,mapActions} from 'vuex';

import Intro from "@/components/Intro";
import SimilarRecommend from "@/components/SimilarRecommend";
import Playlist from "@/components/Playlist";
import Relate from "@/components/Relate";
import Comment from "@/components/Comment";

export default {
  name: "index",
  props: ["branchId"],
  components:{
    Intro,
    SimilarRecommend,
    Playlist,
    Relate,
    Comment
  },
  computed:{
    ...mapState('branchInfo',['branch','similarRecommendList','relateList','episodeList','commentRoot'])
  },
  methods:{
    load(){
      this.getBranch(this.branchId);
      this.getSimilarRecommendList(this.branchId);
      this.getEpisodeList(this.branchId);
      this.getRelateList(this.branchId);
      this.loadComment(1);
      if(sessionStorage.getItem("token") !== null){
        this.getCollection(this.branchId);
      }
    },
    loadComment(pageNum){
      this.getComment({branchId:this.branchId,pageNum});
    },
    ...mapActions('branchInfo',{getBranch: "getBranch",getRelateList:"getRelateList",
      getSimilarRecommendList:"getSimilarRecommendList",getEpisodeList:"getEpisodeList",
      getCollection: "getCollection",getComment: "getComment"})
  },
  watch: {
    $route: {
      handler: function(from, to){
        this.load();
        this.$backTop();
      },
      deep: true
    }
  },
  mounted() {
    this.load();
  }
}
</script>

<style lang="scss" scoped>

.detail-container{
  width: 1140px;
  min-width: 1140px;
  margin: 1.5rem auto 0 auto;
  > * {
    margin: 1rem 0;
  }
  > .detail-content-container{
    > .right-container{
      padding-right: 0.5rem;
      > * {
        padding: 0.35rem;
        border-radius: 5px;
        box-shadow: 2px 2px 2px #c9c6c6;
        background-color: #ffffff;
      }
      > .right-up-container{
        > .intro-container{
          border-bottom: 1px solid #b6b2b2;
          border-top: 1px solid #b6b2b2;
          padding: 0.5rem 0;
        }
        > * {
          margin-bottom: 1.5rem;
        }
      }
      > .right-down-container{
        margin-top: 1.5rem;
      }
    }
    > .left-container{
      margin-left: 1.25rem;
      padding: 0.35rem;
      border-radius: 5px;
      box-shadow: 2px 2px 2px #c9c6c6;
      background-color: #ffffff;
    }
  }
}


</style>