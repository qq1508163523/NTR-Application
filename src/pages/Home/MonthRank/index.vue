<template>
  <div class="month-rank-container">
    <div class="icon-title">
      <span>
        <i class="el-icon-trophy" style="font-size: 2.25rem;color: gold"></i>
      </span>
      <span>月排行榜</span>
    </div>
    <ul class="rank-item-container">
      <li>
        <span class="rank-item-icon">
          <i class="el-icon-medal" style="font-size: 1.5rem;color: gold" ></i>
        </span>
        <span class="title">
          番名番名番名 第一季
        </span>
        <span class="status">
          完结
        </span>
      </li>
      <li>
        <span class="rank-item-icon">
          <i class="el-icon-medal" style="font-size: 1.5rem;color: silver" ></i>
        </span>
        <span class="title">
          番名番名番名 第一季
        </span>
        <span class="status">
          完结
        </span>
      </li>
      <li>
        <span class="rank-item-icon">
          <i class="el-icon-medal" style="font-size: 1.5rem;color: brown" ></i>
        </span>
        <span class="title">
          番名番名番名 第一季
        </span>
        <span class="status">
          完结
        </span>
      </li>
      <li v-for="item in 14" :key="item">
        <span class="rank-item-icon">
          <i class="el-icon-medal" style="font-size: 1.5rem;color: #dde1e3" ></i>
        </span>
        <span class="title">
          番名番名番名 第一季
        </span>
        <span class="status">
          完结
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "index"
}
</script>

<style lang="scss" scoped>

.month-rank-container{
  > .icon-title{
    > span{
      vertical-align: middle;
    }
    span:last-of-type{
      margin-left: 0.25rem;
      font-size: 1.5rem
    }
  }
  > .rank-item-container{
    margin-top: 1rem;
  }
  > .rank-item-container{
    > li{
      margin-top: 0.25rem;
      >span{
        vertical-align: middle;
        font-size: 0.9rem;
      }
      .title{
        cursor: pointer;
        &:hover{
          color: blueviolet;
        }
      }
      .status{
        color: #6c757d;
      }
    }
  }
}

</style>