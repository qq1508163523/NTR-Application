<template>
  <el-row type="flex">
    <el-col :span="8" class="left-container">
      <img :src="branch.imgUrl" alt="branch.translatedName">
    </el-col>
    <el-col :span="16" class="right-container">
      <div class="info-container">
        <span>{{branch.translatedName}}</span>
        <el-row>
          <el-col :span="4"><p>原名:</p></el-col>
          <el-col :span="20"><p>{{branch.originalName}}</p></el-col>
        </el-row>
<!--        <el-row v-if="branch.nicknameList !== null">-->
<!--          <el-col :span="4"><p>别名:</p></el-col>-->
<!--          <el-col :span="20">-->
<!--            <p v-for="nickname in nicknameList" :key="nickname.nicknameId">-->
<!--              {{nickname.nickname}}-->
<!--            </p>-->
<!--          </el-col>-->
<!--        </el-row>-->
        <el-row>
          <el-col :span="4"><p>首播时间:</p></el-col>
          <el-col :span="20"><p>{{branch.releaseDate | dateFormatter}}</p></el-col>
        </el-row>
        <el-row>
          <el-col :span="4"><p>年份:</p></el-col>
          <el-col :span="20"><p>{{branch.releaseDate | dateFormatter('yyyy')}}年</p></el-col>
        </el-row>
        <el-row>
          <el-col :span="4"><p>季度:</p></el-col>
          <el-col :span="20"><p>{{branch.releaseDate | dateFormatter('m')}}月</p></el-col>
        </el-row>
        <el-row>
          <el-col :span="4"><p>状态:</p></el-col>
          <el-col :span="20"><p>{{branch.status}}</p></el-col>
        </el-row>
        <el-row>
          <el-col :span="4"><p>类型:</p></el-col>
          <el-col :span="20">
            <span v-for="genre in branch.genreList" :key="genre.genreName">
              {{genre.genreName}}
            </span>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="4"><p>地区:</p></el-col>
          <el-col :span="20"><p>{{branch.region}}</p></el-col>
        </el-row>
      </div>
      <div style="position: absolute;bottom: 0">
        <el-button size="small" type="primary" @click="doAction" :loading="showBtnLoading">
<!--          {{$store.state.branchInfo.isCollected?'取消收藏':'点击收藏'}}-->
          {{getBtnText}}
        </el-button>
      </div>
    </el-col>
  </el-row>
</template>

<script>

export default {
  name: "Intro",
  props:['branch'],
  data(){
    return {
      showBtnLoading: false,
      btnText: ""
    }
  },
  methods:{
    async doAction(){
      this.showBtnLoading = true;
      if(this.$store.state.branchInfo.isCollected){
        await this.removeCollect();
      }else{
        await this.addCollect();
      }
      this.showBtnLoading = false;
    },
    async addCollect(){
      try{
        await this.$store.dispatch("branchInfo/insertCollection",this.branch.branchId);
        this.$message.success("收藏成功");
      }catch(err){
        this.$message.error(err.message);
      }
    },
    async removeCollect(){
      try{
        await this.$store.dispatch("branchInfo/deleteCollection",this.branch.branchId);
        this.$message.success("取消成功");
      }catch(err){
        this.$message.error(err.message);
      }
    }
  },
  computed: {
    getBtnText(){
      this.btnText = this.$store.state.branchInfo.isCollected?"取消收藏":"点击收藏";
      if(this.showBtnLoading){
        this.btnText = "处理中";
      }
      return this.btnText;
    }
  }
}
</script>

<style lang="scss" scoped>

.info-container{
  > span{
    font-size: 1.25rem;
    font-weight: bold;
  }
  > .el-row{
    font-size: 0.9rem;
    > :nth-child(1){
      font-weight: bold;
    }
  }
  > * {
    margin: 0.5rem 0;
  }
}

.right-container{
  position: relative;
}

</style>