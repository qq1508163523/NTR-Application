<template>
  <div class="recent-update-container">
    <div class="icon-title">
      <span>
        <i class="el-icon-video-play" style="font-size: 2.25rem;color: #2b89a9"></i>
      </span>
      <span>最近更新</span>
    </div>
    <div class="el-row gutter-2">
      <div class="el-col-xs-8 el-col-sm-6 el-col-md-4 el-col-lg-4"
           v-for="branch in $store.state.home.recentUpdateList" :key="branch.branchId"
      >
        <BranchItem :branch="branch"></BranchItem>
      </div>
    </div>
  </div>

</template>

<script>

import BranchItem from "@/components/BranchItem";

export default {
  name: "RecentUpdate",
  components: {
    BranchItem
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