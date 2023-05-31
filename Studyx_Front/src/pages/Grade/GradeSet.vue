<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item >责任教师页面</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/admin/adminborrow">成绩权重</a></el-breadcrumb-item>
    </el-breadcrumb>
    <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column prop="classno" label="班级号" sortable width="150">
      </el-table-column>
      <el-table-column prop="attandancenum" label="考勤次数" width="120">
      </el-table-column>
      <el-table-column prop="attandanceweight" label="考勤权重"  width="200">
      </el-table-column>
      <el-table-column prop="experimentnum" label="实验次数" width="150"> </el-table-column>
      <el-table-column prop="experimentweight" label="实验权重" width="150"> </el-table-column>
      <!-- <el-table-column prop="status" label="当前状态" width="150">
        </el-table-column> -->
      <el-table-column label="操作按钮" width="400px">

        <template slot-scope="scope">
          <el-button size="mini"
                     type="success"
                     @click="mod(scope.row)"
                     plain
          >修改成绩权重</el-button
          >

          <el-dialog
              title="修改成绩权重"
              :visible.sync="dialogFormVisible">
            <form class="basic-grey"
                  id="myform"
                  :model="dataForm"
                  :label-width="formLabelWidth"
                  :rules="rules"
                  ref="dataForm">
              <h1>
                修改信息
                <span>请按照下方规则修改权重信息(若不填写则不会修改)</span>
              </h1>
              <label>
                <span>考勤次数:</span>
                <input
                    v-model="dataForm.attandancenum"
                    id="attandancenum"
                    type="text"
                    name="attandancenum"
                    placeholder="请输入该班级的考勤次数"
                />
              </label>

              <label>
                <span>考勤权重:</span>
                <input
                    v-model="dataForm.attandanceweight"
                    id="attandanceweight"
                    type="text"
                    name="attandanceweight"
                    placeholder="请输入该班级的考勤权重（0-1）"
                />
              </label>

              <!-- <label>
                <span>实验次数:</span>
                <input
                    v-model="dataForm.experimentnum"
                    id="experimentnum"
                    type="text"
                    name="experimentnum"
                    placeholder="请输入该班级的实验次数"
                />
              </label> -->


              <label>
                <span>实验权重:</span>
                <input
                    v-model="dataForm.experimentweight"
                    id="experimentweight"
                    type="text"
                    name="experimentweight"
                    placeholder="请输入该班级的实验权重（0-1）"
                />
              </label>


              <label>
                <!-- <span>&nbsp;</span>
                <input
                  type="button"
                  class="button"
                  style="background: white; color: #e27575; margin-right: 20px"
                  value="清空"
                  @click="cleardata"
                /> -->
                <span>&nbsp;</span>
                <input
                    type="button"
                    class="button"
                    value="确定"
                    @click="confirm"
                />
              </label>
            </form>
          </el-dialog>

          <el-button size="mini"
                     type="warning"
                     @click="calculate(scope.row)"
                     plain
          >计算成绩</el-button
          >
        </template>

      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "GradeSet",
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
    calculate(row){
      //将选中的行内容赋值给表单对象
      //显示编辑窗口
      var _this = this;
      this.$axios
          .post("/calculate", {
            classno: row.classno,
            })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$message.success("计算成功!共"+successResponse.data.result.length+"人");
              this.getdata();
            }
            else{
              this.$message.error("计算失败");
            }
          }).catch(failResponse => {
        this.$message.error("接口调用失败！");
      })
      this.dialogFormVisible = false;

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
          .get('/gradeset')
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
      return row.status === value;
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
.basic-grey {
  margin-left: auto;
  margin-right: auto;
  max-width: 500px;
  background: #f7f7f7;
  padding: 25px 15px 25px 10px;
  font: 12px Georgia, "Times New Roman", Times, serif;
  color: #888;
  text-shadow: 1px 1px 1px #fff;
  border: 1px solid #e4e4e4;
}

.basic-grey h1 {
  font-size: 25px;
  padding: 0px 0px 10px 40px;
  display: block;
  border-bottom: 1px solid #e4e4e4;
  margin: -10px -15px 30px -10px;
  color: #888;
}

.basic-grey h1 > span {
  display: block;
  font-size: 11px;
}

.basic-grey label {
  display: block;
  margin: 0px;
}

.basic-grey label > span {
  float: left;
  width: 20%;
  text-align: right;
  padding-right: 10px;
  margin-top: 10px;
  color: #888;
}

.basic-grey input[type="text"],
.basic-grey input[type="password"],
.basic-grey input[type="email"],
.basic-grey textarea,
.basic-grey select {
  border: 1px solid #dadada;
  color: #888;
  height: 30px;
  margin-bottom: 16px;
  margin-right: 6px;
  margin-top: 2px;
  outline: 0 none;
  padding: 3px 3px 3px 5px;
  width: 70%;
  font-size: 12px;
  line-height: 15px;
  box-shadow: inset 0px 1px 4px #ececec;
  -moz-box-shadow: inset 0px 1px 4px #ececec;
  -webkit-box-shadow: inset 0px 1px 4px #ececec;
}

.basic-grey textarea {
  padding: 5px 3px 3px 5px;
}

.basic-grey select {
  background: #fff;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  text-indent: 0.01px;
  text-overflow: "";
  width: 70%;
  height: 35px;
  line-height: 25px;
}

.basic-grey textarea {
  height: 100px;
}
.basic-grey .button {
  background: #e27575;
  border: none;
  padding: 10px 25px 10px 25px;
  color: #fff;
  box-shadow: 1px 1px 5px #b6b6b6;
  border-radius: 3px;
  text-shadow: 1px 1px 1px #9e3f3f;
  cursor: pointer;
}

.basic-grey .button:hover {
  background: #cf7a7a;
}

</style>
