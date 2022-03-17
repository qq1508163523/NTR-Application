<template>
  <div class="login-container">
    <div class="icon-title">
      <span>用户登录</span>
    </div>
    <el-form :model="loginForm" ref="loginFormRef" status-icon :rules="loginFormRules" label-width="0">
      <el-form-item prop="username">
        <el-input
            :maxlength="20"
            placeholder="输入账号"
            clearable
            v-model="loginForm.username"
            prefix-icon="el-icon-user"
            type="text"
            autocomplete="off"
            @keyup.enter.native="login"
            oninput="value=value.replace(/[^a-z0-9]/g,'')"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
            :maxlength="20"
            placeholder="输入密码"
            clearable
            show-password
            v-model="loginForm.password"
            prefix-icon="el-icon-lock"
            type="password"
            autocomplete="off"
            @keyup.enter.native="login"
            oninput="value=value.replace(/[^a-z0-9A-Z!@#$%^&*()]/g,'')"
        >
        </el-input>
      </el-form-item>
      <el-form-item>
        <button class="login-btn el-button--primary" @click="login" type="button">登录</button>
      </el-form-item>
    </el-form>
    <div class="login-bottom">
      <span>还没有账号?</span>
      <span>
        <router-link to="/register">注册</router-link>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "",
        password: ""
      },
      loginFormRules: {
        username: [
          {required: true, message: "账号不能为空", trigger: "blur"}
        ],
        password: [
          {required: true, message: "密码不能为空", trigger: "blur"}
        ]
      }
    }
  },
  methods: {
    login(){
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return;
        try {
          await this.$store.dispatch("account/userLogin", this.loginForm);
          await this.$store.dispatch("account/getAccount");
          await this.$router.push("/"); // go to home page if authentication success
        } catch (err) {
          this.$message.error(err.message)
        }
      });
    }
  }
}
</script>

<style lang="scss" scoped>

.login-container{
  background-color: white;
  padding: 1rem;
  width: 25rem;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: 0.3125rem;
  box-shadow: 2px 2px 2px #c9c6c6;
  > .icon-title{
    text-align: center;
    margin-bottom: 1.75rem;
  }
  .login-btn{
    margin-top: 1rem;
    width: 100%;
    color: white;
    padding: 0.5rem 0;
    cursor: pointer;
    border-radius: 5px;
    border: none;
  }
  .login-bottom{
    padding-top: 1.75rem;
    border-top: 1px solid #cbc4c4;
    text-align: center;
    span:last-of-type{
      font-size: 0.9rem;
      color: blueviolet;
      cursor: pointer;
      &:hover{
        text-decoration: underline;
      }
    }
  }
}


</style>