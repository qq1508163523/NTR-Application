<template>
  <div class="play-container">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ name: 'detail',params:{branchId}}">{{branch.translatedName}}</el-breadcrumb-item>
      <el-breadcrumb-item>{{episodeNumber | mapEpisodeNumber}}</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="play-content-container">
      <div class="up-container">
        <Player :branchId="branchId" :episodeNumber="episodeNumber"/>
        <Playlist :episodeList="episodeList" :branchId="branchId" :currentEpisode="episodeNumber"/>
        <div class="intro-container">
          <div class="icon-title">
            <span style="font-size: 1.25rem">简介</span>
          </div>
          <div style="margin-top: 0.5rem">
            <span>{{branch.intro}}</span>
          </div>
        </div>
        <Relate :relateList="relateList"/>
        <div class="similar-container">
          <div class="icon-title">
            <span style="font-size: 1.25rem">相关推荐</span>
          </div>
          <ItemDisplay :itemList="similarRecommendList" :limitSize="12" :colSize="6" :gutter="12"/>
        </div>
      </div>
      <div class="down-container">
        <Comment :commentRoot="commentRoot" :branchId="branchId" :loadComment="loadComment"/>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";

import Player from "@/components/Player";
import Playlist from "@/components/Playlist";
import DPlayer from "dplayer";
import Relate from "@/components/Relate";
import Comment from "@/components/Comment";
import ItemDisplay from "@/components/ItemDisplay";

export default {
  name: "Play",
  props:['branchId','episodeNumber'],
  components: {
    Player,
    Playlist,
    Relate,
    ItemDisplay,
    Comment
  },
  computed:{
    ...mapState('branchInfo',['branch','similarRecommendList','relateList','episodeList','episode','commentRoot'])
  },
  methods:{
    load(){
      this.getBranch(this.branchId);
      this.getSimilarRecommendList(this.branchId);
      this.getEpisodeList(this.branchId);
      this.getRelateList(this.branchId);
      this.loadComment(1);
      this.initPlayer();
    },
    loadComment(pageNum) {
      this.getComment({branchId:this.branchId,pageNum});
    },
    initPlayer(){
      let payload = {
        branchId:this.branchId,
        episodeNumber: this.episodeNumber,
        callback:function (url) {
          new DPlayer({
            container: document.getElementById('dplayer'),
            video: {
              url
            }
          });
        }
      };
      this.$store.dispatch("branchInfo/getEpisode",payload);
    },
    ...mapActions('branchInfo',{getBranch: "getBranch",getRelateList:"getRelateList",
      getSimilarRecommendList:"getSimilarRecommendList",getEpisodeList:"getEpisodeList"
      ,getComment:"getComment"})
  },
  watch:{
    $route: {
      handler: function(from, to){
        this.initPlayer();
        this.$backTop();
      },
      deep: true
    }
  },
  mounted() {
    this.load()
  }
}
</script>

<style lang="scss" scoped>

.play-container{
  width: 1140px;
  min-width: 1140px;
  margin: 1rem auto 0 auto;
  > .play-content-container{
    margin-top: 0.75rem;
    > * {
      margin-bottom: 1.25rem;
      border-radius: 5px;
      box-shadow: 2px 2px 2px #c9c6c6;
      padding: 0.75rem;
      background-color: white;
    }
    > .up-container{
      > * {
        margin-bottom: 1.25rem;
      }
      > .intro-container{
        border-bottom: 1px solid #b6b2b2;
        border-top: 1px solid #b6b2b2;
        padding: 0.5rem 0;
      }
    }
  }
}


</style>