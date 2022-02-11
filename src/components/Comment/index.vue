<template>
  <div class="comment-container">
    <div class="icon-title" style="padding-bottom: 1rem">
      <span>
        <i class="el-icon-edit-outline" style="font-size: 2rem"></i>
      </span>
      <span style="font-size: 1.25rem;font-weight: bold">评论</span>
      <el-divider direction="vertical"></el-divider>
      <span style="font-size: 1.25rem;font-weight: normal">{{commentRoot.total}}</span>
    </div>
    <div class="comment-edit-container">
      <CommentBoard :branchId="branchId" :rows="4" :replyTo="null" :parent="-1" :postSuccess="postSuccess" />
    </div>
<!--    <Pagination :pageSize="10" :total="commentRoot.total" :reqPage="loadComment"/>-->
    <div class="comment-item-container">
      <CommentItem v-for="item in commentRoot.dataList" :comment="item" :key="item.commentId"/>
    </div>
    <Pagination :pageSize="10" :total="commentRoot.total" :reqPage="loadComment"/>
  </div>
</template>

<script>

import CommentItem from "@/components/CommentItem";
import CommentBoard from "@/components/CommentBoard";
import Pagination from "@/components/Pagination";

export default {
  name: "Comment",
  props: ['commentRoot','branchId','loadComment'],
  components: {
    CommentItem,
    CommentBoard,
    Pagination
  },
  data(){
    return{
      btnLoading: false
    }
  },
  methods: {
    postSuccess(newComment) {
      this.commentRoot.dataList.unshift(newComment);
    }
  }
}
</script>

<style lang="scss" scoped>

.comment-container{
  padding: 1rem;
  > .comment-edit-container{
    padding: 2.5rem 0;
    border-top: 1px solid #c7bfbf;
  }

  > .comment-item-container{
    > * {
      border-top: 1px solid #ece8e8;
    }
  }

}

</style>