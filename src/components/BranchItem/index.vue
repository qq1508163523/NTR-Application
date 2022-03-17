<template>
  <div class="branch-item-container">
    <div class="cover-container" @click="goDetail(branch.branchId)">
      <img v-lazy="branch.imgUrl" :alt="branch.translatedName">
      <div class="status-container">
        <span>{{branch.status}}</span>
      </div>
    </div>
    <div class="title-container one-line-ellipsis">
      <span @click="goDetail(branch.branchId)">{{branch.translatedName}}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "BranchItem",
  props: ['branch'],
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
.branch-item-container {
  .cover-container {
    position: relative;
    cursor: pointer;
    &:hover {
      opacity: 0.85;
    }

    img {
      max-width: 100%;
      height: auto;
      border-radius: 0.3125rem;
    }

    > .status-container {
      position: absolute;
      text-align: center;
      bottom: 0;
      padding-top: 0.125rem;
      right: 0;
      left: 0;
      background-color: black;
      opacity: 0.6;
      font-size: 0.7rem;
      height: 1rem;
      color: #fff;
      border-radius: 0 0 0.3125rem 0.3125rem;
    }
  }
  .title-container {
    font-size: 0.9rem;
    margin-top: 0.25rem;
    text-align: center;
    span {
      cursor: pointer;
      &:hover {
        color: blueviolet;
      }
    }
  }
}


</style>