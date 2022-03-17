<template>
  <div>
    <div class="icon-title">
      <span style="font-size: 1.25rem;">相关推荐</span>
    </div>
    <div class="el-row gutter-1">
      <div class="el-col-xs-8 el-col-sm-6 el-col-md-4 el-col-lg-4"
              v-for="branch in similarRecommendList"
              :key="branch.branchId">
        <BranchItem :branch="branch"></BranchItem>
      </div>
    </div>
  </div>
</template>

<script>

import BranchItem from "@/components/BranchItem";

export default {
  name: "SimilarRecommendHorizontal",
  props: ["similarRecommendList"],
  components: {
    BranchItem
  }
}
</script>

<style scoped>

</style>