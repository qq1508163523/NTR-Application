<template>
  <div id="app">
    <el-container>
      <el-header height="4.375rem">
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

@import "assets/style/common";
@import "assets/style/reset";
@import "assets/style/rem";

.el-header{
  box-shadow: 0 2px 1px #e0d2d2;
  background-color: white;
}

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


// mobile < 767px
@media screen and (max-width: 767px){
  .el-main{
      margin-top: 1.25rem;
  }
}

// tablet between 768px and 992px
@media screen and (min-width: 768px){
  .el-main{
    margin-top: 1.25rem;
  }
}

//  PC between 992px and 1200px
@media screen and (min-width: 992px){

}

// extra large screen greater than 1200px
@media screen and (min-width: 1200px){

}

</style>
