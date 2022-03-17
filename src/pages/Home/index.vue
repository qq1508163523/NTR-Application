<template>
  <div class="home-container">
    <Carousel/>
    <div class="content-container container">
      <DailyRecommend/>
      <el-row :gutter="12" type="flex">
        <el-col :xs="24" :sm="24" :md="24" :lg="18">
          <WeekSchedule/>
        </el-col>
        <el-col :span="6">
          <MonthRank/>
        </el-col>
      </el-row>
      <RecentUpdate/>
    </div>
  </div>
</template>

<script>

// components
import Carousel from "@/pages/Home/Carousel";
import DailyRecommend from "@/pages/Home/DailyRecommend";
import WeekSchedule from "@/pages/Home/WeekSchedule";
import MonthRank from "@/pages/Home/MonthRank";
import RecentUpdate from "@/pages/Home/RecentUpdate";

export default {
  name: "Home",
  components: {
    Carousel,
    DailyRecommend,
    WeekSchedule,
    MonthRank,
    RecentUpdate
  }
}
</script>

<style lang="scss" scoped>

.home-container{
  margin: 1.5rem auto 0 auto;
   > .content-container {
    margin: 0 auto;
     .el-row{
       margin-top: 2.5rem;
     }
  }
}

</style>