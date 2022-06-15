<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 430px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px">
          <b>注 册</b>
      </div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="no">
          <el-input size="medium" placeholder="学号" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.no"></el-input>
        </el-form-item>
        <el-form-item prop="name">
          <el-input size="medium" placeholder="姓名" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" placeholder="密码" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input size="medium" placeholder="确认密码" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="register">注册</el-button>
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/login')">返回登陆</el-button>
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
    let checkPwd=((rules,value,callback)=>{
      if(value === ''){
        callback(new Error("请输入确认密码"));
      }else if(value !== this.user.password){
        callback(new Error("两次密码不一致"));
      }else{
        callback();
      }
    })
    return{
      user: {
        no: '',
        password: '',
        name:'',
        confirmPassword:''
      },
      rules: {
        no: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        confirmPassword:[
          { required: true, validator: checkPwd, trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    register(){
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          request.post("/user/register",{
            no: this.user.no,
            password: this.user.password,
            name: this.user.name
          }).then(res =>{
            if (res.code === '200'){
              this.$message.success("注册成功")
            }else {
              this.$message.error("用户已存在")
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