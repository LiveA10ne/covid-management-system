<template>
<div>
  <el-card style="width: 500px">
    <el-form :model="user">
      <el-form-item prop="no">
        <el-input size="medium" placeholder="学号" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.no"></el-input>
      </el-form-item>
      <el-form-item prop="name">
        <el-input size="medium" placeholder="姓名" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small"  @click="save">保存</el-button>
        <el-button type="danger" size="small"  @click="dialogVisible = true">修改密码</el-button>
      </el-form-item>
    </el-form>

  </el-card>

  <el-dialog
      title="修改密码"
      :visible.sync="dialogVisible"
      width="30%">

    <el-form :model="form" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="原密码" prop="password">
        <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input type="password" v-model="form.newPassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="form.confirmPassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="changePassword">提交</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

</div>

</template>

<script>

import request from "@/utils/request";

export default {
  name: "Person",
  data(){
    let checkPwd=((rules,value,callback)=>{
      if(value === ''){
        callback(new Error("请输入确认密码"));
      }else if(value !== this.form.newPassword){
        callback(new Error("两次密码不一致"));
      }else{
        callback();
      }
    })
    let checkOldPwd=((rules,value,callback)=>{
      if(value === ''){
        callback(new Error("请输入确认密码"));
      }else if(value !== this.user.password){
        callback(new Error("密码错误"));
      }else{
        callback();
      }
    })
    return{
      dialogVisible: false,
      user: localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
      form: {
        password:'',
        newPassword:'',
        confirmPassword:'',
      },
      rules: {
        password: [
          { required: true, validator: checkOldPwd, trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        newPassword: [
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
    save(){
      request.post("/user/save",this.user).then(res =>{
        if (res.code === '200'){
          this.$message.success("保存成功")
        }else {
          this.$message.success("保存失败")
        }
      })
    },
    changePassword(){
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          request.post("/user/save",{
            no: this.user.no,
            name: this.user.name,
            password: this.form.newPassword
          }).then(res => {
            if (res.code === '200'){
              this.$message.success("修改成功")
              this.dialogVisible = false
            }else {
              this.$message.success("修改失败")
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

</style>