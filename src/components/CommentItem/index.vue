<template>
  <div class="comment-item">
    <div class="user-icon">
      <img :src="comment.account.profilePicUrl" alt="default-icon">
    </div>
    <div class="comment-body">
      <div class="body-top">
        <span>{{comment.account.nickname}}</span>
      </div>
      <div class="body-middle">
        <span>{{comment.content}}</span>
      </div>
      <div class="body-bottom">
        <span>{{comment.postTime | dateFormatter('yyyy-mm-dd HH:MM:ss')}}</span>
        <span @click="changeCommentBoardStatus">回复</span>
      </div>
      <div v-show="commentBoardShow" class="comment-board">
        <CommentBoard ref="commentInput"
                      :rows="2"
                      :branchId="comment.branchId"
                      :parent="comment.commentId"
                      :replyTo="comment.account.username"
                      :postSuccess="postSuccess"
        />
      </div>
      <div class="comment-children">
        <CommentItem v-for="item in comment.childrenList" :comment="item" :key="item.commentId"/>
      </div>
      <div style="margin-top: 0.5rem;" v-if="comment.totalReply && totalLoaded < comment.totalReply" class="more-comment">
        <el-button @click="loadMore" icon="el-icon-caret-bottom" size="mini" :loading="btnLoading">加载评论</el-button>
      </div>
    </div>
  </div>
</template>


<script>

import {reqCommentByParent} from "@/api";

import CommentBoard from "@/components/CommentBoard";

export default {
  name: "CommentItem",
  props:['comment'],
  data(){
    return {
      commentBoardShow: false,
      totalLoaded: 0,
      btnLoading: false
    }
  },
  methods: {
    changeCommentBoardStatus(){
      this.commentBoardShow = !this.commentBoardShow;
      if(this.commentBoardShow){
        this.$nextTick(function () {
          this.$refs.commentInput.$refs.inputArea.focus();
        });
      }
    },
    postSuccess(newComment){
      this.commentBoardShow = false;
      this.comment.childrenList.push(newComment);
    },
    async loadMore(){
      if(this.totalLoaded <= this.comment.totalReply){
        let nextPage = this.totalLoaded / 10 + 1;
        this.btnLoading = true;
        let result = await reqCommentByParent(this.comment.commentId,nextPage);
        if(result.code === 200){
          this.comment.childrenList.push.apply(this.comment.childrenList,result.data.dataList);
          this.totalLoaded += result.data.pageSize;
        }else{
          this.$message.error(result.message);
        }
        this.btnLoading = false;
      }
    }
  },
  components: {
    CommentBoard
  }
}
</script>

<style lang="scss" scoped>

.comment-item{
  display: flex;
  padding: 1.25rem 0;
  > .user-icon{
    img{
      height: 3rem;
      width: 3rem;
      border-radius: 50%;
      border: 1px solid #c7bfbf
    }
  }

  > .comment-body{
    width: 100%;
    margin-left: 0.75rem;
    > .body-top{
      color: red;
      font-size: 0.85rem;
    }
    > .body-middle{
      font-size: 0.9rem;
    }
    > .body-bottom{
      margin-top: 0.5rem;
      font-size: 0.7rem;
      color: #b09e9e;
      > span{
        margin-right: 0.5rem;
      }
      span:last-of-type{
        cursor: pointer;
      }
    }
    > .comment-board{
      margin-top: 0.75rem;
    }
    > .more-comment{
      > span{
        color: blueviolet;
        cursor: pointer;
        &:hover{
          text-decoration: underline;
        }
      }
    }
  }
}

</style>