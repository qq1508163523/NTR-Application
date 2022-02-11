<template>
  <div class="security-container">
    <div style="text-align: right;margin-right: 1rem;color: #bbb6b6">
      上次修改: {{$store.state.account.account.lastPwModifyTime | dateFormatter}}
    </div>
    <el-row>
      <el-col :span="11" :push="1">
        <el-form :model="securityForm" label-position="right" ref="securityFormRef" status-icon :rules="securityFormRules" label-width="100px">
          <el-form-item prop="oldPassword" label="旧密码:">
            <el-input
                :maxlength="20"
                placeholder="输入旧密码"
                clearable
                size="small"
                v-model="securityForm.oldPassword"
                show-password
                type="password"
                autocomplete="off"
                oninput="value=value.replace(/[^a-z0-9A-Z!@#$%^&*()]/g,'')"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="newPassword" label="新密码:">
            <el-input
                :maxlength="20"
                placeholder="输入新密码"
                clearable
                show-password
                size="small"
                v-model="securityForm.newPassword"
                type="password"
                autocomplete="off"
                oninput="value=value.replace(/[^a-z0-9A-Z!@#$%^&*()]/g,'')"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="reNewPassword" label="确认新密码:">
            <el-input
                :maxlength="20"
                placeholder="确认新密码"
                clearable
                show-password
                size="small"
                v-model="securityForm.reNewPassword"
                type="password"
                autocomplete="off"
                oninput="value=value.replace(/[^a-z0-9A-Z!@#$%^&*()]/g,'')"
            >
            </el-input>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-divider></el-divider>
    <div style="text-align: center">
      <el-button type="primary" @click="sendPasswordChange">修改</el-button>
    </div>


    <el-dialog
        title="提示"
        :visible.sync="confirmDialogVisible"
        width="30%"
    >
      <span>修改成功,点击确认从新登录</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="$router.push({name:'login'})">确&nbsp;定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Security",
  data(){
    return {
      confirmDialogVisible: false,
      securityForm: {
        oldPassword: "",
        newPassword: "",
        reNewPassword: ""
      },
      securityFormRules: {
        oldPassword: [
          {required:true, message:"不能为空"},
          {min: 6, max: 20, message: '长度在6到20之间'}
        ],
        newPassword: [
          {required:true, message:"不能为空"},
          {min: 6, max: 20, message: '长度在6到20之间'}
        ],
        reNewPassword: [
          {validator: this.validateRePassword}
        ]
      }
    }
  },
  methods: {
    sendPasswordChange(){
      this.$refs.securityFormRef.validate( async valid => {
        if(!valid) return;
        const formData = new FormData();
        formData.append("oldPassword",this.securityForm.oldPassword);
        formData.append("newPassword",this.securityForm.newPassword);
        let p = this.$processing(document.querySelector(".security-container"),"处理中");
        try{
          await this.$store.dispatch("account/modifyPassword",formData);
          this.confirmDialogVisible = true;
        }catch (err){
          this.$message.error(err.message);
        }
        p.close();
      });
    },
    validateRePassword(rule,value,callback){
      if(value !== this.securityForm.newPassword){
        callback(new Error("密码不一致"));
      }else{
        callback();
      }
    }
  }
}
</script>

<style scoped>

</style>