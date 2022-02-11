<template>
  <div id="app">
    <el-container>
      <el-header height="70px">
        <Header/>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>
        <Footer/>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  import Header from "@/components/Header";
  import Footer from "@/components/Footer";

  export default {
    name: 'App',
    components: {
      Header,
      Footer
    }
  }
</script>

<style lang="scss">
.el-header{
  box-shadow: 0 2px 1px #e0d2d2;
  background-color: white;
}

//#app{
//  background-color: #ecebeb;
//}

.icon-title{
  > span{
    vertical-align: middle;
  }
  span:last-of-type{
    margin-left: 0.25rem;
    font-size: 1.5rem;
    font-weight: bold;
  }
}

</style>
