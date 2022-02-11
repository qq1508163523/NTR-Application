<template>
  <div class="daily-recommend-container">
    <div class="icon-title">
      <span>
        <i class="el-icon-video-play" style="font-size: 2.25rem;color: red"></i>
      </span>
      <span>今日推荐</span>
    </div>
    <ItemDisplay :itemList="$store.state.home.dailyRecommendList" :limitSize="12" :colSize="6" :gutter="12"/>
  </div>
</template>

<script>

import ItemDisplay from "@/components/ItemDisplay";

export default {
  name: "DailyRecommend",
  components: {
    ItemDisplay
  },
  mounted() {
    this.$store.dispatch('home/getDailyRecommendList');
  }
}
</script>

<style lang="scss" scoped>
.daily-recommend-container{
  > .icon-title{
    > span{
      vertical-align: middle;
    }
    span:last-of-type{
      margin-left: 0.25rem;
      font-size: 1.5rem
    }
  }
}


</style>