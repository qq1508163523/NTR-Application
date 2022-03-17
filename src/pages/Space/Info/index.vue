<template>
  <div class="info-container">
    <div class="el-row">
      <div class="el-col-xs-5 el-col-sm-6 el-col-md-5 el-col-lg-4">
        <span>昵称: </span>
      </div>
      <div class="el-col-xs-18 el-col-sm-17 el-col-md-18 el-col-lg-19">
        <el-popover
            placement="bottom-end"
            title="错误"
            width="200"
            trigger="manual"
            content="昵称长度在1至12之间"
            v-model="visible">
          <el-input slot="reference" size="small" v-model.trim="nickname" @focus="visible=false"></el-input>
        </el-popover>
      </div>
    </div>
    <div class="el-row">
      <div class="el-col-xs-5 el-col-sm-6 el-col-md-5 el-col-lg-4">
        <span>用户名: </span>
      </div>
      <div class="el-col-xs-18 el-col-sm-17 el-col-md-18 el-col-lg-19">
        <el-input disabled size="small" :value="account.username"></el-input>
      </div>
    </div>
    <div class="el-row">
      <el-col class="el-col-xs-5 el-col-sm-6 el-col-md-5 el-col-lg-4">
        <span>邮箱: </span>
      </el-col>
      <el-col class="el-col-xs-18 el-col-sm-17 el-col-md-18 el-col-lg-19">
        <el-input disabled size="small" :value="account.email"></el-input>
      </el-col>
    </div>
    <div class="el-row">
      <div class="el-col-xs-5 el-col-sm-6 el-col-md-5 el-col-lg-4">
        <span>加入时间: </span>
      </div>
      <div class="el-col-xs-18 el-col-sm-17 el-col-md-18 el-col-lg-19">
        <el-input disabled size="small" :value="account.createTime | dateFormatter"></el-input>
      </div>
    </div>
    <el-divider></el-divider>
    <div style="text-align: center;margin-bottom: 1.25rem">
      <el-button type="primary" @click="sendUpdate">保存</el-button>
    </div>
  </div>
</template>

<script>

import {mapState} from "vuex";

export default {
  name: "Info",
  data(){
    return {
      nickname: "",
      visible: false
    }
  },
  methods: {
    async sendUpdate(){
      if(this.nickname.length === 0 || this.nickname.length > 12){
        this.visible = true;
      }else{
        let p = this.$processing(document.querySelector(".info-container"),"处理中");
        try{
          await this.$store.dispatch("account/updateNickname",this.nickname);
          this.$message.success("修改成功");
        }catch(err){
          this.$message.error(err.message);
        }
        p.close();
      }
    }
  },
  watch: {
    accountNickname:{
      deep: true,
      immediate: true,
      handler(val){
        this.nickname = val.nickname;
      }
    }
  },
  computed: {
    accountNickname(){
      return this.account;
    },
    ...mapState("account",["account"])
  }
}
</script>

<style lang="scss" scoped>

.info-container{
  > .el-row{
    margin: 0 auto 1rem 0;
    >:first-child{
     line-height: 32px;
      text-align: right;
      padding-right: 1rem;
      font-size: 1rem
    }
  }
}

</style>