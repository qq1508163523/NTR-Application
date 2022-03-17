<template>
  <div class="detail-container container">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{branch.translatedName}}</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="el-row">
      <div class="el-col-xs-24 el-col-lg-18">
        <div class="left-up-container">
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
          <div class="hidden-lg-and-up">
            <SimilarRecommendHorizontal :similarRecommendList="similarRecommendList"/>
          </div>
        </div>
        <div class="left-down-container">
          <Comment :commentRoot="commentRoot" :branchId="branchId" :loadComment="loadComment"/>
        </div>
      </div>
      <div class="el-col-lg-6 hidden-md-and-down">
        <SimilarRecommendVertical :similarRecommendList="similarRecommendList" :limitSize="10"/>
      </div>
    </div>
  </div>
</template>

<script>

import {mapState,mapActions} from 'vuex';

import Intro from "@/components/Intro";
import SimilarRecommendVertical from "@/components/SimilarRecommendVertical";
import SimilarRecommendHorizontal from "@/components/SimilarRecommendHorizontal";
import Playlist from "@/components/Playlist";
import Relate from "@/components/Relate";
import Comment from "@/components/Comment";
import BranchItem from "@/components/BranchItem";

export default {
  name: "index",
  props: ["branchId"],
  components:{
    Intro,
    SimilarRecommendVertical,
    SimilarRecommendHorizontal,
    Playlist,
    Relate,
    BranchItem,
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
  margin: 1.5rem auto 0 auto;
  > .el-row{
    margin-top: 0.5rem;
    > div:first-child{
      padding-right: 0.75rem;
      > * {
        padding: 0.35rem;
        border-radius: 5px;
        box-shadow: 2px 2px 2px #c9c6c6;
        background-color: #ffffff;
      }
      > .left-up-container{
        > .intro-container{
          border-bottom: 1px solid #b6b2b2;
          border-top: 1px solid #b6b2b2;
          padding: 0.5rem 0;
        }
        > * {
          margin-bottom: 1.5rem;
        }
      }
      > .left-down-container{
        margin-top: 1.25rem;
      }
    }
    > div:last-child{
      padding: 0.5rem;
      border-radius: 5px;
      box-shadow: 2px 2px 2px #c9c6c6;
      background-color: #ffffff;
    }
  }
}


@media screen and(max-width: 992px) {
  .detail-container {
    > .el-row {
      > div:first-child {
        padding-right: 0;
      }
    }
  }
}

</style>