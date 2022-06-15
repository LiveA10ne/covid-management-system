<template>
  <div>
    <!--        搜索-->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入学号" suffix-icon="el-icon-search" v-model="no"></el-input>
      <el-input style="width: 200px; margin-left: 5px" placeholder="请输入姓名" suffix-icon="el-icon-search"
                v-model="name"></el-input>
      <el-input style="width: 200px; margin-left: 5px" placeholder="请输入学院" suffix-icon="el-icon-search"
                v-model="college"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>
    </div>

    <!--        添加、导出和批量删除-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">添加<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="exp">导出<i class="el-icon-document"></i></el-button>
      <el-popconfirm
          style="margin-left: 5px"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定删除吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除</el-button>
      </el-popconfirm>
    </div>

    <!--        主体表格-->
    <el-table :data="tableData" @selection-change="handleSelectionChange">

      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="no" label="学号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="college" label="学院"></el-table-column>
      <el-table-column prop="major" label="班级"></el-table-column>
      <el-table-column prop="createTime" label="上传时间" sortable></el-table-column>
      <el-table-column prop="location" label="位置"></el-table-column>
      <el-table-column prop="temperature" label="体温"></el-table-column>
      <el-table-column prop="cough"
                       label="咳嗽"
                       :filters="[{text: '是', value: '是'}, {text: '否', value: '否'}]"
                       :filter-method="filterHandler">
      </el-table-column>
      <el-table-column prop="fever" label="发烧"
                       :filters="[{text: '是', value: '是'}, {text: '否', value: '否'}]"
                       :filter-method="filterHandler">
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm
              style="margin-left: 5px"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="handleDelete(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--        分页-->
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalPages">
      </el-pagination>
    </div>

    <el-dialog title="健康上报" :visible.sync="dialogVisible" width="30%">

      <el-form :model="form" label-width="80px">
        <el-form-item label="学号" >
          <el-input v-model="form.no" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="姓名" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="学院" >
          <el-input v-model="form.college" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="班级" >
          <el-input v-model="form.major" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="位置" >
          <el-input v-model="form.location" autocomplete="off"></el-input>
<!--          <el-button @click="getLocation">获取定位</el-button>-->
        </el-form-item>

        <el-form-item label="体温" >
          <el-input v-model="form.temperature" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="咳嗽" >
          <el-switch v-model="form.cough"
                     active-value="是"
                     inactive-value="否"
                     active-color="#ff4949"
                     inactive-color="#13ce66"
                     active-text="是"
                     inactive-text="否">
          </el-switch>
        </el-form-item>

        <el-form-item label="发烧" >
          <el-switch v-model="form.fever"
                     active-value="是"
                     inactive-value="否"
                     active-color="#ff4949"
                     inactive-color="#13ce66"
                     active-text="是"
                     inactive-text="否">

          </el-switch>
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
       </span>
    </el-dialog>

  </div>
</template>

<script>

import request from "@/utils/request";


export default {
  name: "Health",
  data() {
    return {
      tableData: [],
      pageNum: 0,
      pageSize: 5,
      dialogVisible: false,
      totalPages: 0,
      no: "",
      name: "",
      college: "",
      form: {
        no: '',
        name: '',
        college: '',
        major: '',
        location: '',
        temperature: 0,
        cough: '',
        fever: ''
      },
      multipleSelection: []
    }
  },
  created() {
    this.load();
  },
  methods: {
    // getLocation(){
    //   var native = new BMap.LocalCity();
    //   native.get(res=> {
    //     console.log(res)
    //   });
    // },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleSelectionChange(val){
      this.multipleSelection = val
    },
    handleAdd(){
      this.dialogVisible = true
      this.form = {}
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    handleEdit(row){
      this.form = row
      this.dialogVisible = true
    },
    handleDelete(id){
      request.delete("/health/"+ id).then(res =>{
        if (res){
          this.load()
          this.$message.success("删除成功")
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    load() {
      request.get("/health/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          no: this.no,
          name: this.name,
          college: this.college
        }
      }).then(res => {
        this.tableData = res.records
        this.totalPages = res.total
      })
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      request.post("/health/del/batch",ids).then(res =>{
        if (res){
          this.load()
          this.$message.success("删除成功")
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    reset() {
      this.no = ""
      this.name = ""
      this.college = ""
      this.load()
    },
    save(){
      request.post("/health",this.form).then(res =>{
        if (res){
          this.$message.success("保存成功")
          this.dialogVisible = false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    exp(){
      window.open("http://localhost:9090/health/export")
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    }
  }
}
</script>

<style scoped>

</style>