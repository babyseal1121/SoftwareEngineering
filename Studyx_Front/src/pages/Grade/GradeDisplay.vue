<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item >责任教师页面</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/admin/adminborrow">查看成绩</a></el-breadcrumb-item>
    </el-breadcrumb>

    <h4>当前成绩计算公式: 100 * 当前出勤分占比 * (出勤次数 / 总次数) + 100 * 当前出勤分占比 * (当前分数 / 总分数)</h4>

    <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column
          prop="classno"
          label="班级号"
          width="150"
          :filters="[
            { text: '5班', value: 5 },
            { text: '6班', value: 6 },
          ]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
      >

      </el-table-column>
      <el-table-column prop="userid" label="学生id" width="120">
      </el-table-column>
      <el-table-column prop="attandancegrade" label="考勤成绩"  width="200">
      </el-table-column>
      <el-table-column prop="experimentgrade" label="实验成绩" width="150"> </el-table-column>
      <el-table-column prop="totalgrade" label="总成绩" width="150"> </el-table-column>
      <!-- <el-table-column prop="status" label="当前状态" width="150">
        </el-table-column> -->
    </el-table>
  </div>
</template>

<script>
export default {
  name: "GradeDisplay",
  data() {
    return {
      classno: "",
      attandancenum: "",
      attandanceweight: "",
      experimentnum:"",
      experimentweight:"",
      tableData: [],
      sending:"未发送",
      dialogVisible: false,
      value: '',
      dialogFormVisible: false,
      dataForm: {
        classno:'',
        attandanceweight:'',
        attandancenum:'',
        experimentnum:'',
        experimentweight:'',
      }
    };
  },
  mounted() {
    this.getdata();
  },
  methods: {
    add(){
      this.dialogFormVisible = true;
    },
    confirm(){//确定添加
      var _this = this;
      this.$axios
          .post("/changegradeset", {
            classno: this.dataForm.classno,
            attandanceweight:this.dataForm.attandanceweight,
            attandancenum:this.dataForm. attandancenum,
            experimentnum:this.dataForm.experimentnum,
            experimentweight:this.dataForm.experimentweight
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$message.success("修改权重成功"+successResponse.data.result.classno);
              this.getdata();
            }
            else{
              this.$message.error("修改权重失败");
            }
          }).catch(failResponse => {
        this.$message.error("接口调用失败！"+this.dataForm.classno);
      })
      this.dialogFormVisible = false;
    },
    cancel(){//取消
      this.dialogFormVisible = false;
    },
    mod(row){
      //将选中的行内容赋值给表单对象
      this.dataForm.classno=row.classno;
      //显示编辑窗口
      this.dialogFormVisible = true;
      this.modFlag = true;
    },
    handleClose(done) {
      this.$confirm('确认修改用户权限？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    getdata() {
      var _this = this
      this.$axios
          .get('/gradelist')
          .then(resp => {
            if (resp && resp.data.code === 200) {
              _this.tableData = resp.data.result
            }
          })
          .catch(failResponse => {
            this.$message.error("数据发送失败");
          })
    },

    filterTag(value, row) {
      return row.classno === value;
    },
  },

}
</script>

<style scoped>
.rwd-table {
  position: absolute;
  top: 150px;
  left: 300px;
}
.el-breadcrumb {
  height: 50px;
}
</style>
