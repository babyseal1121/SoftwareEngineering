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
        <el-table-column label="操作按钮" width="200px">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="warning"
                       @click="borrowcon(scope.$index, scope.row)"
                       plain
            >账号授权</el-button
            >
            <el-button
                size="mini"

                @click="returncon(scope.$index, scope.row)"
            >账号注销</el-button
            >
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
    };
  },
  mounted() {
    this.getdata();
  },
  methods: {
    getdata() {
      var _this = this
      this.$axios
          .get('/userlist')
          .then(resp => {
            if (resp && resp.data.code === 200) {
              _this.tableData = resp.data.result
              this.$message.success("共"+_this.tableData.length+"条");
            }
          })
          .catch(failResponse => {
            this.$message.error("数据发送失败");
          })
    },
    borrowcon(index, row) {
      var _this = this;
      if (row.status == "visit") {
        _this.$message({
          showClose: true,
          message: "该账号未激活，等待激活后授权",
          type: "warning",
        });
      } else {
        this.$axios.post("/changeborrow", {
          userid: row.userid,
          bookid: row.bookid,
          status: "授权中",
        });
        _this.$message({
          showClose: true,
          message: "授权成功",
          type: "success",
        });
      }
    },
    returncon(index, row) {
      var _this = this;
      console.log(index, row);
      if (row.level != "未激活") {
        _this.$message({
          showClose: true,
          message: "该账号未激活",
          type: "warning",
        });
      } else {
        this.$axios.post("/changeborrow", {
          userid: row.userid,
          bookid: row.bookid,
          status: "已结束",
        });
        _this.$message({
          showClose: true,
          message: "归还成功",
          type: "success",
        });
      }
    },
    filterTag(value, row) {
      return row.status === value;
    },
    viewbookdetail(index, row) {
      let _this = this;
      this.$axios({
        url: "/user/getbookdetail",
        method: "post",
        data: row.bookid,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        console.log(res);
        _this.donatetime = res.data.donatetime;
        _this.isbn = res.data.isbn;
        _this.userid = res.data.userid;
      });
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
