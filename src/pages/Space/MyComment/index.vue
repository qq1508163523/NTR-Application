<template>
  <div class="my-comment-container">
    <div class="comment-item" v-for="item in comment.dataList" >
      <div>
        <el-link type="primary" :href="`/#/detail/${item.branch.branchId}`"
                 :underline="false">{{item.branch.translatedName}}</el-link>
      </div>
<!--      <CommentItem :comment="item" :key="item.commentId"/>-->
      <div style="margin-top: 0.75rem">
        <p>{{item.content}}</p>
        <p style="color: #a9a1a1;font-size: 0.75rem;margin-top: 0.5rem">{{item.postTime | dateFormatter('yyyy-mm-dd HH:MM:ss')}}</p>
      </div>
      <el-divider></el-divider>
    </div>
    <Pagination :pageSize="10" :total="comment.total" :reqPage="load"/>
  </div>
</template>

<script>

import {reqCommentByUsername} from "@/api";

import CommentItem from "@/components/CommentItem";
import Pagination from "@/components/Pagination";

export default {
  name: "MyComment",
  components: {
    CommentItem,
    Pagination
  },
  data(){
    return {
      comment: {
        dataList: []
      }
    }
  },
  methods: {
    async load(pageNum){
      let result = await reqCommentByUsername(pageNum);
      if(result.code === 200){
        this.comment = result.data;
      }else{
        this.$message.error(result.message);
      }
    }
  },
  mounted() {
    this.load(1);
  }
}
</script>

<style lang="scss" scoped>

.my-comment-container{
  padding: 0 1rem 1rem 1rem;
}

</style>