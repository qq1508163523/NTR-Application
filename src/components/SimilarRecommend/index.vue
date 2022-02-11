<template>
  <div class="sr-container">
    <div class="icon-title">
      <span style="font-size: 1.25rem">相关推荐</span>
    </div>
    <div class="el-row item-container" v-for="branch in similarRecommendList.slice(0,limitSize)" :key="branch.branchId">
      <div class="el-col-10 cover-container">
        <img @click="goDetail(branch.branchId)" :src="branch.imgUrl" :alt="branch.branchId">
      </div>
      <div class="el-col-14 info-container">
        <div>
          <span @click="goDetail(branch.branchId)">{{branch.translatedName}}</span>
        </div>
        <div>
          <div>
            <span>状态: &nbsp;&nbsp;</span><span>{{branch.status}}</span>
          </div>
          <div>
            <span>年份: &nbsp;&nbsp;</span><span>{{branch.releaseDate | dateFormatter('yyyy')}}年</span>
          </div>
          <div>
            <span>季度: &nbsp;&nbsp;</span><span>{{branch.releaseDate | dateFormatter('m')}}月</span>
          </div>
          <div>
            <span>类型: &nbsp;&nbsp;</span>
            <span v-for="genre in branch.genreList" :key="genre.genreName">
              {{genre.genreName}}
          </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SimilarRecommend",
  props: ["similarRecommendList","limitSize"],
  methods: {
    goDetail(branchId) {
      this.$router.push({
        name: 'detail',
        params:{
          branchId
        }
      });
    }
  }
}
</script>

<style lang="scss" scoped>

.sr-container{
  > .item-container{
    padding: 0.5rem 0;
    border-bottom: 1px solid #ece9e9;
    > .cover-container{
      > img{
        cursor: pointer;
        max-width: 100%;
        height: auto;
        border-radius: 5px;
      }
    }
    > .info-container{
      padding-left: 0.5rem;
      > div:first-of-type{
        cursor: pointer;
        font-weight: bold;
        font-size: 0.9rem;
        & :hover{
          color: blueviolet;
        }
      }
      > div:last-of-type{
        > div {
          margin: 0.25rem 0;
          > span:first-of-type{
            font-weight: 600;
          }
        }
      }
    }
  }
}

</style>