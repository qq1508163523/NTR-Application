<template>
  <div class="episodes-container">
    <div class="icon-title">
<!--      <span>-->
<!--        <i class="el-icon-video-play" style="font-size: 2rem;color: deepskyblue"></i>-->
<!--      </span>-->
      <span style="font-size: 1.25rem">播放列表</span>
    </div>
    <div class="el-row gutter-0">
      <div class="el-col-xs-6 el-col-sm-6 el-col-md-4 el-col-lg-3"
              v-for="episode in episodeList" :key="episode.md5">
        <div :class="{'episode-active':currentEpisode === episode.episodeNumber}"
             @click="goPlay(episode.episodeNumber)"
             class="playlist-item">
          <span>
            {{episode.episodeNumber | mapEpisodeNumber}}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Playlist",
  props: ["episodeList","branchId","currentEpisode"],
  methods: {
    goPlay(episodeNumber){
      this.$router.push({
        name: "play",
        query: {
          episodeNumber
        },
        params: {
          branch: this.branchId
        }
      });
    }
  }
}
</script>

<style lang="scss" scoped>

.episodes-container{
  //> .playlist-item-container{
  //  margin-top: 0.5rem;
    //display: grid;
    //grid-template-columns: repeat(8, 1fr);
    //grid-row-gap: 5px;
    //grid-column-gap: 5px;
    .playlist-item{
      margin-top: 0.25rem;
      border: 1.5px solid #ccc7c0;
      border-radius: 3px;
      padding: 0.2rem;
      text-align: center;
      color: black;
      cursor: pointer;
      &:hover{
        border: 1.5px solid darkred;
      }
    //}
  }
}

.episode-active{
  background-color: indianred;
  > span{
    color: white;
  }
}


</style>