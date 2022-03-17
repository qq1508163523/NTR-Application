<template>
  <div class="el-row intro">
    <div class="el-col-xs-9 el-col-sm-6 el-col-md-6 el-col-lg-8">
      <img class="img-fluid" v-lazy="branch.imgUrl" :alt="branch.translatedName">
    </div>
    <div class="el-col-xs-15 el-col-sm-18 el-col-md-18 el-col-lg-16">
      <div class="info-container">
        <div class="title one-line-ellipsis">
          <span>{{branch.translatedName}}</span>
        </div>
        <div class="el-row">
          <div class="el-col-xs-7 el-col-sm-6 el-col-md-5 el-col-lg-4"><span>原名:</span></div>
          <div class="el-col-xs-17 el-col-sm-18 el-col-md-19 el-col-lg-20"><span>{{branch.originalName}}</span></div>
        </div>
        <div class="el-row">
          <div class="el-col-xs-7 el-col-sm-6 el-col-md-5 el-col-lg-4"><span>首播时间:</span></div>
          <div class="el-col-xs-17 el-col-sm-18 el-col-md-19 el-col-lg-20"><span>{{branch.releaseDate | dateFormatter}}</span></div>
        </div>
        <div class="el-row">
          <div class="el-col-xs-7 el-col-sm-6 el-col-md-5 el-col-lg-4"><span>年份:</span></div>
          <div class="el-col-xs-17 el-col-sm-18 el-col-md-19 el-col-lg-20"><span>{{branch.releaseDate | dateFormatter('yyyy')}}年</span></div>
        </div>
        <div class="el-row hidden-xs-only">
          <div class="el-col-xs-7 el-col-sm-6 el-col-md-5 el-col-lg-4"><span>季度:</span></div>
          <div class="el-col-xs-17 el-col-sm-18 el-col-md-19 el-col-lg-20"><span>{{branch.releaseDate | dateFormatter('m')}}月</span></div>
        </div>
        <div class="el-row">
          <div class="el-col-xs-7 el-col-sm-6 el-col-md-5 el-col-lg-4"><span>状态:</span></div>
          <div class="el-col-xs-17 el-col-sm-18 el-col-md-19 el-col-lg-20"><span>{{branch.status}}</span></div>
        </div>
        <div class="el-row">
          <div class="el-col-xs-7 el-col-sm-6 el-col-md-5 el-col-lg-4"><span>类型:</span></div>
          <div class="el-col-xs-17 el-col-sm-18 el-col-md-19 el-col-lg-20">
            <span v-for="genre in branch.genreList" :key="genre.genreName">
              {{genre.genreName}}
            </span>
          </div>
        </div>
        <div class="el-row">
          <div class="el-col-xs-7 el-col-sm-6 el-col-md-5 el-col-lg-4"><span>地区:</span></div>
          <div class="el-col-xs-17 el-col-sm-18 el-col-md-19 el-col-lg-20"><span>{{branch.region}}</span></div>
        </div>
      </div>
      <div style="position: absolute;bottom: 0">
        <el-button size="mini" type="primary" @click="doAction" :loading="showBtnLoading">
          {{getBtnText}}
        </el-button>
      </div>
    </div>
  </div>
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

.intro{
  display: flex;
  > div:last-child{
    padding-left: 0.25rem;
  }
  .info-container{
    > .title{
      > span{
        font-size: 1.2rem;
        font-weight: bold;
      }
    }
    > div{
      font-size: 0.9rem;
      > div:first-child{
        font-weight: bold;
      }
      > div:last-child{
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
    }

    > * {
      margin-bottom: 0.5rem;
    }
  }
}

</style>