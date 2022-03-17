<template>
  <div>
    <div v-if="result.dataList.length > 0">
      <div style="padding: 1rem 0">
        <Pagination :pageSize="24" :total="result.total" :reqPage="reqPage"/>
      </div>
      <div style="margin-top: 1rem">
        <div>
          <span style="font-weight: bold;font-size: 1rem">共{{result.total}}个结果</span>
        </div>
        <div class="el-row gutter-2">
          <div class="el-col-xs-8 el-col-sm-6 el-col-md-4 el-col-lg-4"
               v-for="branch in result.dataList"
               :key="branch.branchId">
            <BranchItem :branch="branch"></BranchItem>
          </div>
        </div>
      </div>
    </div>
    <el-empty v-else description="无搜索结果" :image-size="200"></el-empty>
  </div>
</template>


<script>

import BranchItem from "@/components/BranchItem";
import Pagination from "@/components/Pagination";

export default {
  name: "Result",
  props:['result','reqPage'],
  components: {
    BranchItem,
    Pagination
  }
}
</script>

<style scoped>

</style>