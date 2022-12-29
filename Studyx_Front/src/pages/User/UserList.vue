<template>
  <Contentfield>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/admin/adminborrow">用户管理</a></el-breadcrumb-item>
      </el-breadcrumb>
      <el-table
          :data="tableData"
          style="width: 100%"
          :default-sort="{ prop: 'date', order: 'descending' }"
      >
        <el-table-column prop="id" label="用户id" sortable width="150">
        </el-table-column>
        <el-table-column prop="username" label="用户名" width="120">
        </el-table-column>
        <el-table-column prop="mail" label="电子邮箱"  width="200">
        </el-table-column>
        <el-table-column prop="status" label="账号状态" width="150"> </el-table-column>
        <el-table-column
            prop="level"
            label="权限等级"
            width="150"
            :filters="[
            { text: '管理员', value: '管理员' },
            { text: '责任教师', value: '责任教师' },
            { text: '教师', value: '教师' },
            { text: '助教', value: '助教' },
            { text: '学生', value: '学生' },
            { text: '未激活', value: '未激活' },
            { text: '未授权', value: '未授权' },
          ]"
            :filter-method="filterTag"
            filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
                :type="
                scope.row.level == '责任教师'
                  ? ''
                  : scope.row.level == '教师'
                  ? 'success'
                  : scope.row.level == '管理员'
                  ? 'danger'
                  : scope.row.level == '助教'
                  ? 'success'
                  : scope.row.level == '学生'
                  ? 'warning'
                  : scope.row.level == '未激活'
                  ? 'info'
                  : scope.row.level == '未授权'
                  ? 'info'
                  : 'primary'
              "
                disable-transitions
            >{{ scope.row.level }}</el-tag
            >
          </template>
        </el-table-column>
        <!-- <el-table-column prop="status" label="当前状态" width="150">
          </el-table-column> -->
        <el-table-column label="操作按钮" width="400px">

          <template slot-scope="scope">
            <el-button size="mini"
                       type="warning"
                       @click="dialogVisible = true"
                       plain
                       v-show="scope.row.level!='未激活'"
            >账号授权</el-button
            >
            <el-dialog
                title="修改用户权限"
                :visible.sync="dialogVisible"
                width="30%"
                >
              <el-select v-model="value" placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
              <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="changelevel(scope.$index, scope.row)">确 定</el-button>
            </span>
            </el-dialog>
            <el-button
                size="mini"
                @click="deleteuser(scope.$index, scope.row)"
            >账号注销</el-button>
            <el-button v-if="sending === '已发送'"
                       type="warning"
                       size="mini"
                       @click="changestatus(scope.$index, scope.row); refresh()"
                       v-show="scope.row.level=='未激活'">已发送</el-button>
            <el-button v-else size="mini"
                       type="danger"
                       @click="changestatus(scope.$index, scope.row); refresh()"
                       v-show="scope.row.level=='未激活'">重新发送激活邮件</el-button>
          </template>

        </el-table-column>
      </el-table>
    </div>
  </Contentfield>
</template>

<script>
//import Bookcard from "@/components/MypageCom/MyBorrowCom/Bookcard.vue";
import Contentfield from "@/components/AdminCom/Contentfield.vue";
export default {
  components: { Contentfield,},
  data() {
    return {
      id: "",
      username: "",
      level: "",
      mail:"",
      status:"",
      tableData: [],
      sending:"未发送",
      dialogVisible: false,
      options: [{
        value: '管理员',
        label: '管理员'
      }, {
        value: '责任教师',
        label: '责任教师'
      }, {
        value: '教师',
        label: '教师'
      }, {
        value: '助教',
        label: '助教'
      }, {
        value: '学生',
        label: '学生'
      }],
      value: '',
    };
  },
  mounted() {
    this.getdata();
  },
  methods: {
    changestatus(index, row) {
      this.$message("邮件正在发送，请稍等");
      //alert(this.regUser.regPhone)
      this.$axios({
        url: "/sms",
        method: "post",
        data: row.mail,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        // console.log(res.data.code)
        if (res.data != null) {
          this.$message.success("邮件发送成功");
          this.findVerification = res.data;
        } else {
          console.log(res.data);
          this.$message.error("验证码发送失败");
          return;
        }
      });
      this.sending="已发送"
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
          .get('/userlist')
          .then(resp => {
            if (resp && resp.data.code === 200) {
              _this.tableData = resp.data.result
            }
          })
          .catch(failResponse => {
            this.$message.error("数据发送失败");
          })
    },
    changeLevel(index, row) {
      var _this = this;
      this.$message.error("授权成功！"+row.id);
      this.$axios
          .post("/changeLevel", {
        userid: row.id,}).then(successResponse => {
        if (successResponse.data.code === 200) {
          this.$message.error("授权成功！");
          this.getdata()//实时显示评论
        }
        else{
          this.$message.error("授权失败");
        }
      }).catch(failResponse => {
            this.$message.error("授权失败！");
          })
    },
    deleteuser(index,row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
            .post('/delete', {id: row.id}).then(res => {

          if (res.data.code == "200") {
            this.$message({
              type: 'info',
              message: '已删除'
            })
            this.getdata();
          }
          else {
            this.$message({
              type: 'info',
              message: '删除失败'
            })
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    filterTag(value, row) {
      return row.status === value;
    },
  },
};
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
