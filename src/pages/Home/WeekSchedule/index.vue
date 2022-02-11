<template>
  <div class="week-schedule-container">
    <div class="icon-title">
      <span>
        <i class="el-icon-date" style="font-size: 2.25rem;color: #9754e1"></i>
      </span>
      <span>周更新列表</span>
    </div>
    <el-tabs v-model="activeName" :stretch="true">
      <el-tab-pane
          v-for="week in $store.state.home.weekScheduleList"
          :label="'周'+week.nameCn"
          :name="week.nameEn"
          :key="week.nameEn"
      >
        <div class="week-item">
          <ItemDisplay v-if="week.branchList.length > 0" :itemList="week.branchList" :colSize="6" :limitSize="18" :gutter="6"/>
          <div v-else style="text-align: center">
              <h2 style="color: #9d7c7c;">空空如也</h2>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>

import ItemDisplay from "@/components/ItemDisplay";

export default {
  name: "WeekSchedule",
  components:{
    ItemDisplay
  },
  data(){
    return {
      activeName: 'Mon'
    }
  },
  mounted() {
    this.$store.dispatch("home/getWeekSchedule");
  }
}
</script>

<style lang="scss" scoped>

.week-schedule-container{
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

.week-item{
  max-height: 460px;
  overflow-y: auto;
  overflow-x: hidden;
}



</style>