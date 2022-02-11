<template>
  <div class="register-container">
    <div class="icon-title">
      <span>用户注册</span>
    </div>
    <el-steps align-center :active="active" finish-status="success">
      <el-step title="邮箱验证"></el-step>
      <el-step title="账号密码设置"></el-step>
    </el-steps>
    <div class="step-box-container">
      <!--   code verification form   -->
      <el-form v-if="!active" :model="verificationForm" ref="verifyFormRef" status-icon :rules="verificationRules" label-width="0">
        <el-form-item prop="email">
          <el-input
              maxlength="64"
              placeholder="输入邮箱"
              clearable
              v-model.trim="verificationForm.email"
              prefix-icon="el-icon-message"
              type="text"
              autocomplete="off"
          >
          </el-input>
        </el-form-item>
        <el-form-item prop="verificationCode">
          <el-input placeholder="输入验证码"
                    maxlength="6"
                    clearable
                    v-model="verificationForm.verificationCode"
                    type="text"
                    autocomplete="off"
                    oninput="value=value.replace(/[^0-9]/g,'')"
          >
            <el-button
                @click="sendVerificationCode"
                :disabled="verificationCodeBtnDisabled"
                slot="append"
            >
              {{verificationCodeBtnText}}
            </el-button>
          </el-input>
        </el-form-item>
        <el-form-item class="register-btn" >
          <el-button @click="verifyCode" type="primary" size="small">下一步</el-button>
        </el-form-item>
      </el-form>
      <!--   registration form   -->
      <el-form v-else-if="active===1" :model="registerForm" ref="registerFormRef" status-icon :rules="registerFormRules" label-width="0">
        <el-form-item>
          <el-input
              v-model="verificationForm.email"
              prefix-icon="el-icon-message"
              type="text"
              disabled
              autocomplete="off"
          >
          </el-input>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
              placeholder="输入账号"
              maxlength="20"
              clearable
              v-model="registerForm.username"
              prefix-icon="el-icon-user"
              type="text"
              autocomplete="off"
              oninput="value=value.replace(/[^a-z0-9]/g,'')"
          >
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              placeholder="输入密码"
              maxlength="20"
              clearable
              v-model="registerForm.password"
              prefix-icon="el-icon-lock"
              type="password"
              autocomplete="off"
              show-password
              oninput="value=value.replace(/[^a-z0-9A-Z!@#$%^&*()]/g,'')"
          >
          </el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input
              placeholder="确认密码"
              maxlength="20"
              clearable
              v-model="registerForm.rePassword"
              prefix-icon="el-icon-lock"
              type="password"
              autocomplete="off"
              show-password
              oninput="value=value.replace(/[^a-z0-9A-Z!@#$%^&*()]/g,'')"
          >
          </el-input>
        </el-form-item>
        <el-form-item class="register-btn">
          <el-button @click="registerAccount" type="primary" size="small">注册</el-button>
        </el-form-item>
      </el-form>
      <div v-else>
        <el-result icon="success" title="注册成功" >
          <template slot="extra">
            <el-button @click="$router.push({name:'login'})" type="primary" size="medium">前往登录</el-button>
          </template>
        </el-result>
      </div>
    </div>
  </div>
</template>

<script>

import {reqVerificationCode, reqVerifyCode,reqRegisterAccount} from "@/api";

export default {
  name: "Register",
  data(){
    return {
      active: 0,
      verificationForm: {
        email: "",
        verificationCode: ""
      },
      registerForm: {
        username: "",
        password: "",
        rePassword: ""
      },
      verificationCodeBtnText: "发送验证码",
      verificationCodeBtnDisabled: false,
      verificationRules: {
        email: [
          {required:true,message: "邮箱不能为空", trigger: "blur"},
          {validator: this.validateEmail, trigger: 'blur'}
        ],
        verificationCode: [
          {required:true,message: "验证码不能为空"}
        ]
      },
      registerFormRules: {
        username: [
          {required:true,message: "账号不能为空",trigger: "blur"},
          { min: 6, max: 20, message: '账号长度在6到20', trigger: 'blur' }
        ],
        password: [
          {required:true,message: "账号不能为空",trigger: "blur"},
          { min: 6, max: 20, message: '密码长度在6到20', trigger: 'blur' }
        ],
        rePassword: [
          {validator: this.validateRePassword}
        ]
      }
    }
  },
  methods: {
    sendVerificationCode(){
      this.$refs.verifyFormRef.validateField("email", async error => {
        if(error) return;
        let result = await reqVerificationCode(this.verificationForm.email);
        if(result.code === 200){
          this.openSuccess();
          this.verificationCodeBtnDisabled = true;
          this.resendCountDown();
        }else{
          this.openError("验证码发送失败!"+result.message);
        }
      });
    },
    verifyCode(){
      this.$refs.verifyFormRef.validate( async valid => {
        if(!valid) return;
        const fromData = new FormData();
        fromData.append("email",this.verificationForm.email);
        fromData.append("verificationCode",this.verificationForm.verificationCode+"");
        let result = await reqVerifyCode(fromData);
        if(result.code === 200){
          this.active = 1;
        }else{
          this.openError(result.message);
        }
      });
    },
    registerAccount(){
      this.$refs.registerFormRef.validate(async valid => {
        if(!valid) return;
        const data = {
          username: this.registerForm.username,
          password: this.registerForm.password,
          email: this.verificationForm.email
        };
        let result = await reqRegisterAccount(data);
        if(result.code === 200){
          this.active = 2;
        }else{
          this.openError(result.message);
        }
      });
    },
    validateEmail(rule,value,callback){
      if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
        callback(new Error("邮箱格式错误"));
      }else{
        callback();
      }
    },
    validateRePassword(rule,value,callback){
      if(value !== this.registerForm.password){
        callback(new Error("密码不一致"));
      }else{
        callback();
      }
    },
    resendCountDown(){
      let start = 45;
      let timer = setInterval(()=>{
        if(start <= 0){
          clearInterval(timer);
          this.verificationCodeBtnDisabled = false;
          this.verificationCodeBtnText = "发送验证码";
        }else{
          this.verificationCodeBtnText = start+"s";
        }
        start--;
      },1000);
    },
    openSuccess() {
      this.$message({
        showClose: true,
        message: '验证码发送成功',
        type: 'success'
      });
    },
    openError(msg) {
      this.$message({
        showClose: true,
        message: msg,
        type: 'error'
      });
    },
  }
}
</script>

<style lang="scss" scoped>

.register-container{
  background-color: white;
  padding: 1rem;
  width: 400px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: 5px;
  box-shadow: 2px 2px 2px #c9c6c6;
  > .icon-title{
    text-align: center;
    margin-bottom: 1.75rem;
  }
  > .step-box-container{
    margin-top: 1.75rem;
    .register-btn{
      text-align: center;
    }
  }
}

</style>