<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px">
          <b>登 录</b>
      </div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="no">
          <el-input size="medium" placeholder="学号" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.no"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" placeholder="密码" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "Login",
  data(){
    return{
      user: {
        no: '',
        password: ''
      },
      rules: {
        no: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login(){

      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          request.post("/user",this.user).then(res =>{
            if (res.code === '200'){
              this.$message.success("登录成功")
              this.$router.push("/home")
              localStorage.setItem("user",JSON.stringify(res.data))
            }else {
              this.$message.error("学号或密码错误")
            }
          })
        } else {
          return false;
        }
      });

    }
  }
}
</script>

<style scoped>
.wrapper{
  height: 100vh;
  background-image: linear-gradient(to bottom right, rgba(70, 252, 200, 0.54), rgba(251, 123, 63, 0.39));
  overflow: hidden;
}
</style>