<template>
  <div class="header-container">
    <div>
      <router-link to="/" class="logo">NTR</router-link>
      <ul class="nav-list">
        <li><router-link to="/category">目录</router-link></li>
        <li><a href="#">每日推荐</a></li>
        <li><a href="#">最近更新</a></li>
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
        <el-dropdown>
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
</template>

<script>

import {mapState} from "vuex";

export default {
  name: "Header",
  data(){
    return {
      keyword: ""
    }
  },
  methods: {
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
  mounted() {
    this.loadAccount()
  }
}
</script>

<style lang="scss" scoped>

.header-container{
  padding: 0 1.2rem;
  line-height: 70px;
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
        height: 40px;
        width: 40px;
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





</style>