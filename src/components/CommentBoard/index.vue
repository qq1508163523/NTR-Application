<template>
  <div class="comment-edit-container">
    <div class="user-icon">
        <span>
          <img :src="$store.state.account.account.profilePicUrl" alt="user-icon">
        </span>
    </div>
    <div class="comment-board">
      <div class="board">
        <el-input
            type="textarea"
            placeholder="来一条评论吧"
            v-model.trim="content"
            maxlength="256"
            show-word-limit
            :rows="rows"
            :disabled="!$store.state.account.account.username"
            ref="inputArea"
        >
        </el-input>
        <div v-if="!$store.state.account.account.username" class="login-tip">
          请先<router-link to="/login">登录</router-link>
        </div>
      </div>
      <div>
        <el-button @click="insert"
                   type="primary"
                   :disabled="!$store.state.account.account.username"
                   size="small"
        >
          评论
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>

import {reqInsertComment} from "@/api";

export default {
  name: "index",
  props: ['rows','replyTo','parent','branchId','postSuccess'],
  data(){
    return{
      content: "",
    }
  },
  methods: {
    async insert(){
      if(this.content){
        const data = {
          content:this.content,
          replyTo:this.replyTo,
          parent:this.parent,
          branchId:this.branchId
        }
        // console.log(this.commentList);
       let result = await reqInsertComment(data);
       if(result.code === 200){
         this.$message.success("评论成功");
         this.postSuccess(result.data);
         this.content = '';
       }else{
         this.$message.error(result.message);
       }
      }
    }
  }
}
</script>

<style lang="scss" scoped>

.comment-edit-container{
  display: flex;
  > .user-icon{
    > span{
      img{
        height: 36px;
        width: 36px;
        border-radius: 50%;
        border: 1px solid #c7bfbf;
        text-align: center;
      }
    }
  }
  > .comment-board{
    width: 100%;
    margin-left: 0.33rem;
    >.board{
      position: relative;
      >.login-tip{
        position: absolute;
        left: 50%;
        top: 50%;
        font-size: 0.9rem;
        transform: translate(-50%, -50%);
        > a{
          color: blue;
          &:hover{
            text-decoration: underline;
          }
        }
      }
    }
    > div:last-of-type{
      margin-top: 0.6rem;
      text-align: right;
    }
  }
}

</style>