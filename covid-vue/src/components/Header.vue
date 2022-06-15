<template>
  <div style="line-height: 60px; display: flex">

<!--    展开按钮-->
    <div style="flex: 1; font-size: 20px">

      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>

    </div>

    <el-dropdown style="width: 70px; cursor: pointer">
      <span>{{user.name}}<i class="el-icon-arrow-down" style="margin-left: 5px"></i></span>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link style="text-decoration: none" @click="" to="/person">个人信息</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link style="text-decoration: none" @click="logout" to="/login">退出</router-link>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Function
  },
  data(){
    return{
      user: localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}
    }
  },
  methods:{
    logout(){
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
  }
}
</script>

<style scoped>

</style>