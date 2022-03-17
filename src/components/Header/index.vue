<template>
  <div class="header-container">
    <div class="header-media-big hidden-md-and-down">
      <div>
        <router-link to="/" class="logo">NTR</router-link>
        <ul class="nav-list">
          <li><router-link to="/category">目录</router-link></li>
          <li><router-link to="/#">每日推荐</router-link></li>
          <li><router-link to="/#">最近更新</router-link></li>
        </ul>
      </div>
      <div>
        <div>
          <el-input @keyup.enter.native="goSearch" v-model="keyword" placeholder="请输入内容" prefix-icon="el-icon-search"></el-input>
        </div>
        <el-divider direction="vertical"></el-divider>
        <div v-if="!account.username">
          <router-link to="/login" style="font-size: 0.9rem">登录</router-link>
        </div>
        <div v-else>
          <el-dropdown trigger="click">
            <img :src="account.profilePicUrl" :alt="account.profilePicName">
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <router-link to="/space/info">我的信息</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/space/profilePic">我的头像</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/space/collection">我的收藏</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/space/security">账号安全</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <span style="color: red" @click="logout">退出登录</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <div class="header-media-small hidden-lg-and-up">
      <div class="toggle-btn" @click="changeDrawerMode">
        <div class="hamburg-component"></div>
        <div class="hamburg-component"></div>
        <div class="hamburg-component"></div>
      </div>
      <div class="logo">
        <router-link to="/" class="logo">NTR</router-link>
      </div>
      <div class="search">
        <el-input @keyup.enter.native="goSearch" v-model="keyword" placeholder="请输入内容" prefix-icon="el-icon-search"></el-input>
      </div>
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
            <span slot="title" style="font-size: 1.2rem;font-weight: bold">NTR</span>
          </el-menu-item>
          <el-menu-item index="/category">
            <i class="el-icon-menu"></i>
            <span slot="title">目录</span>
          </el-menu-item>
          <el-menu-item index="#">
            <i class="el-icon-star-off"></i>
            <span slot="title">每日推荐</span>
          </el-menu-item>
          <el-menu-item index="#">
            <i class="el-icon-time"></i>
            <span slot="title">最近更新</span>
          </el-menu-item>
          <el-menu-item index="/login" v-if="!account.username">
            <i class="el-icon-s-custom"></i>
            <span slot="title">登录</span>
          </el-menu-item>
          <el-menu-item index="/space/info" v-else>
            <i class="el-icon-s-custom"></i>
            <span slot="title">我的账号</span>
          </el-menu-item>
        </el-menu>
      </el-drawer>
    </div>
  </div>
</template>

<script>

import {mapState} from "vuex";

export default {
  name: "Header",
  data(){
    return {
      keyword: "",
      drawer: false
    }
  },
  methods: {
    changeDrawerMode(){
      this.drawer = !this.drawer;
    },
    goSearch(){
      this.keyword&&this.$router.push({
        name: "search",
        query: {
          keyword: this.keyword
        }
      });
    },
    loadAccount(){
      this.$store.dispatch("account/getAccount");
    },
    logout(){
      this.$store.dispatch("account/logout");
      this.$router.push({name:'home'});
    }
  },
  computed: {
    ...mapState("account",["account"])
  },
  watch: {
    '$route.path': {
      handler: function(from, to){
        this.drawer = false;
      }
    }
  },
  mounted() {
    this.loadAccount()
  }
}
</script>

<style lang="scss" scoped>

.header-container{
  line-height: 4.375rem;
  > .header-media-big{
    padding: 0 1.2rem;
    div:first-of-type {
      display: flex;
      float: left;
      .logo {
        font-size: 1.5rem;
        font-weight: bold;
      }

      .nav-list {
        margin-left: 1rem;
        > li {
          font-size: 1rem;
          margin: 0 0.65rem;
          float: left;
          > a {
            &:hover {
              text-decoration: none;
            }
          }
        }
      }
    }

    div:last-of-type {
      display: inline-block;
      float: right;
      div:first-of-type {
        margin-right: 0.5rem;
      }

      div:last-of-type {
        margin-left: 1rem;
        img{
          height: 2.5rem;
          width: 2.5rem;
          border-radius: 50%;
          border: 2px solid #c7bfbf;
          vertical-align: middle;
          cursor: pointer;
          &:hover{
            border: 2px solid #d5c35f;
          }
        }
      }
    }
  }
  > .header-media-small{
    > .toggle-btn{
      cursor: pointer;
      position: absolute;
      margin-top: 1rem;
      float: left;
      > .hamburg-component{
        width: 2.25rem;
        height: 0.3125rem;
        border: 1px solid black;
        border-radius: 0.3125rem;
        margin: 0.3125rem 0;
        background-color: black;
      }
    }
    > .logo{
      text-align: center;
      font-size: 1.75rem;
      font-weight: bold;
    }

    > .search{
      margin-top: 0.25rem;
    }
  }
}

// mobile < 767px
@media screen and (max-width: 767px){

}

// tablet between 768px and 992px
@media screen and (min-width: 768px){

}

//  PC between 992px and 1200px
@media screen and (min-width: 992px){

}

// extra large screen greater than 1200px
@media screen and (min-width: 1200px){

}



</style>