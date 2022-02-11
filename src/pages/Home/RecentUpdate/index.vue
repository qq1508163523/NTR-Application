<template>
  <div class="recent-update-container">
    <div class="icon-title">
      <span>
        <i class="el-icon-video-play" style="font-size: 2.25rem;color: #2b89a9"></i>
      </span>
      <span>最近更新</span>
    </div>
    <ItemDisplay :itemList="$store.state.home.recentUpdateList" :limitSize="12" :colSize="6" :gutter="12"/>
  </div>

</template>

<script>

import ItemDisplay from "@/components/ItemDisplay";

export default {
  name: "RecentUpdate",
  components: {
    ItemDisplay
  },
  mounted() {
    this.$store.dispatch('home/getRecentUpdate');
  }
}
</script>

<style lang="scss" scoped>

.recent-update-container{
  margin-top: 1rem;
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