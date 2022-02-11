<template>
  <el-row :gutter="gutter">
    <el-col
        :span="Math.floor(24/colSize)"
        v-for="item in itemList.slice(0,Math.min(itemList.length,limitSize))"
        :key="item.branchId"
    >
      <div class="cover-container" @click="goDetail(item.branchId)">
        <img :src="item.imgUrl" :alt="item.translatedName">
        <div class="status-container">
          <span>{{item.status}}</span>
        </div>
      </div>
      <div class="title-container">
        <span @click="goDetail(item.branchId)">{{item.translatedName}}</span>
      </div>
    </el-col>
  </el-row>
</template>

<script>

export default {
  name: "ItemDisplay",
  props: ['itemList','limitSize','marginTop','colSize','gutter'],
  methods: {
    goDetail(branchId){
      this.$router.push({
        name: "detail",
        params:{
          branchId
        }
      });
    }
  }
}

</script>

<style lang="scss" scoped>

.el-col{
  margin-top: 0.75rem;
  .cover-container{
    position: relative;
    cursor: pointer;
    &:hover{
      opacity: 0.85;
    }
    img{
      max-width: 100%;
      height: auto;
      border-radius: 5px;
    }
    > .status-container{
      position: absolute;
      text-align: center;
      bottom: 2px;
      padding-top: 2px;
      right: 0;
      left: 0;
      background-color: black;
      opacity: 0.6;
      font-size: 0.7rem;
      height: 1.5em;
      overflow: hidden;
      color: #fff;
      border-radius: 0 0 5px 5px;
    }
  }
  .title-container{
    font-size: 0.9rem;
    height: 1.5em;
    overflow: hidden;
    text-align: center;
    span{
      cursor: pointer;
      &:hover{
        color: blueviolet;
      }
    }
  }
}

</style>