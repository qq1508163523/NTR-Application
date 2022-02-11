<template>
  <div class="collection-container">
    <div class="top-container">
      <el-row v-show="!multiSelectMode">
        <el-col class="operation" :span="18">
          <el-link :underline="false" @click.native.prevent="multiSelectMode=true">批量操作</el-link>
        </el-col>
        <el-col class="collection-search" :span="5" :push="1">
          <el-input
              placeholder="请输入内容"
              size="small"
              clearable>
          </el-input>
        </el-col>
      </el-row>
      <div class="multi-mode-container" v-show="multiSelectMode">
        <div class="multi-mode-top">
          <el-button @click="multiSelectMode=false" size="mini" >返回</el-button>
        </div>
        <div class="multi-mode-bottom" style="margin-top: 1rem;">
          <el-checkbox v-model="selectAll" label="全选"></el-checkbox>
          <el-link type="danger" :underline="false" @click.native.prevent="deleteBulk" :disabled="deleteDisabled">删除选定</el-link>
        </div>
      </div>
    </div>
    <el-divider></el-divider>
    <div class="bottom-container">
      <el-row :gutter="12">
        <el-col class="item-container" :span="4" v-for="(item,index) in collection.dataList" :key="item.branch.branchId">
          <div class="cover">
            <img @click="goDetail(item.branch.branchId,index)"
                 :src="item.branch.imgUrl"
                 :alt="item.branch.translatedName"
            >
            <div class="item-checkbox" v-show="multiSelectMode">
              <el-checkbox v-model="item.checked"></el-checkbox>
            </div>
          </div>
          <div class="title">
            <el-link @click.native.prevent="goDetail(item.branch.branchId,index)"
                     :underline="false"
            >
              {{item.branch.translatedName}}
            </el-link>
          </div>
          <div class="collect-time">
            <div>
              <span>收藏于:&nbsp;{{item.collectTime | dateFormatter}}</span>
            </div>
            <div>
              <el-dropdown hide-on-click trigger="click">
                <span style="cursor: pointer;font-size: 0.75rem">
                  <i class="el-icon-more"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="deleteSingle(item.branch.branchId)">删除</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </div>
        </el-col>
      </el-row>
      <Pagination :pageSize="12" :total="collection.total" :reqPage="load"/>
    </div>
  </div>
</template>

<script>

import {reqCollectionByUsername,reqDeleteCollection,reqDeleteBulkCollection} from "@/api";

import Pagination from "@/components/Pagination";

export default {
  name: "Collection",
  components: {
    Pagination
  },
  data(){
    return {
      collection: {
        dataList: []
      },
      multiSelectMode: false,
      selectAll: false,
      deleteDisabled: true
    }
  },
  methods: {
    async load(pageNum){
      let result = await reqCollectionByUsername(pageNum);
      if(result.code === 200){
        this.collection = result.data;
        this.collection.dataList.forEach( (element,index,array) => {
          this.$set(array[index],"checked",false);
        });
      }else{
        this.$message.error(result.message);
      }
    },
    goDetail(branchId,index){
      if(!this.multiSelectMode){
        this.$router.push({
          name: "detail",
          params:{
            branchId
          }
        });
      }else{
        this.collection.dataList[index].checked = !this.collection.dataList[index].checked;
      }
    },
    async deleteSingle(branchId){
      let result = await reqDeleteCollection(branchId);
      if(result.code === 200){
        this.$message.success("删除成功");
        await this.load(this.collection.pageNum);
      }else{
        this.$message.success(result.message);
      }
    },
    async deleteBulk(){
      let branchIdList = this.collection.dataList
          .filter(element => element.checked)
          .map( x => x.branch.branchId);
      const formData = new FormData();
      branchIdList.forEach( x => formData.append("branchIdList[]",x));
      let result = await reqDeleteBulkCollection(formData);
      if(result.code === 200){
        this.$message.success("删除成功");
        await this.load(this.collection.pageNum);
      }else{
        this.$message.error(result.message);
      }
    }
  },
  watch: {
    selectAll: {
      handler(newVar,oldVar){
        if(newVar){
          this.collection.dataList.forEach((element)=>{
            element.checked = newVar;
          });
        }else{
          let flag = true;
          this.collection.dataList.forEach((element)=>{
            flag = flag && element.checked;
          });
          if(flag) {
            this.collection.dataList.forEach((element)=>{
              element.checked = newVar;
            });
          }
        }
      }
    },
    multiSelectMode: {
      handler(newVar,oldVar){
        this.selectAll = false;
        this.collection.dataList.forEach((element)=>{
          element.checked = false;
        });
      }
    },
    'collection.dataList': {
      deep: true,
      handler(newVar,oldVar){
        let flag = true;
        let hasChecked = false;
        this.collection.dataList.forEach(element => {
          flag = flag && element.checked;
          hasChecked = hasChecked || element.checked;
        });
        this.selectAll = flag;
        this.deleteDisabled = !hasChecked;
      }
    }
  },
  mounted() {
    this.load(1);
  }
}
</script>

<style lang="scss" scoped>

.collection-container{
  padding: 0 1rem;
  > .top-container{
     .operation{
      text-align: right;
      line-height: 28px;
    }
    > .multi-mode-container{
      > .multi-mode-bottom{
        > * {
          vertical-align: middle;
          margin-right: 0.75rem;
        }
      }
    }
  }
  > .bottom-container{
    padding-bottom: 1rem;
    .item-container{
      margin-bottom: 0.75rem;
    }
    .cover{
      position: relative;
      > img {
        max-width: 100%;
        height: auto;
        border-radius: 5px;
        cursor: pointer;
      }
      > .item-checkbox{
        position: absolute;
        top: 5px;
        right: 5px;
      }
    }
    .title{
      height: 1.5em;
      overflow: hidden;
      margin-bottom: 0.25rem;
    }
    .collect-time{
      >div:first-of-type{
        color: #b9b2b2;
        float: left;
      }
      >div:last-of-type{
        float: right;
      }
    }
  }
}

</style>