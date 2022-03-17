<template>
    <div class="el-row space-container container">
      <div class="el-col-4 hidden-md-and-down">
        <el-menu router
                 :default-active="this.$route.path"
        >
          <el-menu-item index="1" disabled>
            <span slot="title" style="font-size: 1.2rem;font-weight: bold">个人中心</span>
          </el-menu-item>
          <el-menu-item index="/space/info">
            <i class="el-icon-info"></i>
            <span slot="title">我的信息</span>
          </el-menu-item>
          <el-menu-item index="/space/profilePic">
            <i class="el-icon-s-custom"></i>
            <span slot="title">我的头像</span>
          </el-menu-item>
          <el-menu-item index="/space/collection">
            <i class="el-icon-star-on"></i>
            <span slot="title">我的收藏</span>
          </el-menu-item>
          <el-menu-item index="/space/comment">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">我的评论</span>
          </el-menu-item>
          <el-menu-item index="/space/security">
            <i class="el-icon-lock"></i>
            <span slot="title">账号安全</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div class="el-col-xs-24 el-col-sm-24 el-col-md-24 el-col-lg-20">
        <router-view></router-view>
      </div>
      <div class="triangle-btn hidden-lg-and-up" @click="drawer=true"></div>
      <el-drawer
          direction="ltr"
          size="12.5rem"
          :modal="false"
          :visible.sync="drawer"
          :with-header="false">
        <el-menu router
                 :default-active="this.$route.path"
        >
          <el-menu-item index="1" disabled>
            <span slot="title" style="font-size: 1.2rem;font-weight: bold">个人中心</span>
          </el-menu-item>
          <el-menu-item index="/space/info">
            <i class="el-icon-menu"></i>
            <span slot="title">我的信息</span>
          </el-menu-item>
          <el-menu-item index="/space/profilePic">
            <i class="el-icon-s-custom"></i>
            <span slot="title">我的头像</span>
          </el-menu-item>
          <el-menu-item index="/space/collection">
            <i class="el-icon-star-on"></i>
            <span slot="title">我的收藏</span>
          </el-menu-item>
          <el-menu-item index="/space/comment">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">我的评论</span>
          </el-menu-item>
          <el-menu-item index="/space/security">
            <i class="el-icon-lock"></i>
            <span slot="title">账号安全</span>
          </el-menu-item>
        </el-menu>
      </el-drawer>
    </div>
</template>

<script>


export default {
  name: "Space",
  data(){
    return {
      drawer: false
    }
  },
  watch: {
    '$route.path': {
      handler: function(from, to){
        this.drawer = false;
      }
    }
  }

}
</script>

<style lang="scss" scoped>

.space-container{
  margin-top: 1.5rem;
  background-color: white;
  > div:nth-child(1){
    .el-menu{
      .el-menu-item{
        text-align: center;
      }
    }
  }
  > div:nth-child(2){
    padding-top: 1.25rem;
    margin-left: -1px;
    border-left: 1px solid #e5e0e0;
  }
}

.triangle-btn{
  width: 0;
  height: 0;
  border-top: 1.25rem solid transparent;
  border-bottom: 1.25rem solid transparent;
  border-left: 1.25rem solid red;
  border-right: 1.25rem solid transparent;
  position: fixed;
  left: 0.1rem;
  top: 50%;
  margin-top: -0.75rem;
  cursor: pointer;
}



</style>