<template>
  <div>
    <el-carousel :interval="4000" type="card" trigger="click" height="352px">
      <el-carousel-item v-for="(carousel,index) in $store.state.home.carouselList" :key="index">
        <img @click="goDetail(carousel.branchId)" :src="carousel.imgUrl" :alt="carousel.translatedName">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>

export default {
  name: "Carousel",
  methods: {
    goDetail(branchId){
      this.$router.push({
        name: "detail",
        params: {
          branchId
        }
      })
    }
  },
  mounted() {
    this.$store.dispatch('home/getCarouselList');
  }
}
</script>

<style lang="scss" scoped>

img{
  border-radius: 10px;
}

</style>