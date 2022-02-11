<template>
    <el-row class="space-container">
      <el-col :span="4">
        <el-menu router
            :default-active="this.$route.path"
            class="el-menu-vertical-demo">
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
      </el-col>
      <el-col :span="20">
        <router-view></router-view>
      </el-col>
    </el-row>
</template>

<script>


export default {
  name: "Space"
}
</script>

<style lang="scss" scoped>

.space-container{
  margin: 1.5rem auto 0 auto;
  width: 1140px;
  min-width: 1140px;
  background-color: white;
  >:first-child{
    .el-menu{
      //background-color: #fdf9f9;
      .el-menu-item{
        text-align: center;
      }
    }
  }
  >:last-child{
    padding-top: 1.25rem;
    border-left: 1px solid #e5e0e0;
  }
}



</style>