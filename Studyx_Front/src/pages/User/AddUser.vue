<template>
  <Contentfield>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/admin/adminborrow">添加用户</a></el-breadcrumb-item>
      </el-breadcrumb>

      <div class="flex-display">

        <el-button size="small" type="primary" class="el-btn"@click="add">
          创建新用户
        </el-button>
        <el-upload
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-success="handleChange"
            :file-list="fileList"
            class="el-upload"
        >
          <el-tooltip class="item" effect="dark" content="只能上传xlsx文件，且不超过5MB" placement="top-start">
            <el-button size="small" type="primary" class="el-btn">
              导入用户信息
            </el-button>
          </el-tooltip>
        </el-upload>
      </div>
      <el-table v-if="tableHead.length" :data="tableData[0]" style="width: 100%">
        <el-table-column
            v-for="(data, key) in tableHead"
            :prop="data"
            :label="data"
            :key="key"
            width="180"
        >
        </el-table-column>
      </el-table>
    </div>

  </Contentfield>

</template>


<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import { read, utils } from "xlsx";

Vue.use(ElementUI);

export default {
  name: "AddUser",
  components: { Contentfield,},
  data() {
    return {
      fileList: [], //上传文件列表
      tableHead: [], //表头
      tableData: [] // 表数据
    };
  },
  methods: {
    add(){
      this.$message.success("test");
    },
    handleChange(res, file, fileList) {
      // 将文件放入
      for (let i = 0; i < fileList.length; i++) {
        if (file.name != fileList[i].name) {
          this.fileList.push({
            name: file.name,
            url: "",
            uid: file.uid
          });
        }
      }

      // this.fileList = fileList.slice(-3);
      const files = { 0: file };
      this.readExcel(files);
    },
    readExcel(file) {
      const fileReader = new FileReader();

      fileReader.onload = ev => {
        try {
          const data = ev.target.result;
          const workbook = read(data, { type: "binary" });
          const params = [];
          // 取对应表生成json表格内容
          workbook.SheetNames.forEach(item => {
            params.push({
              name: item,
              dataList: utils.sheet_to_json(workbook.Sheets[item])
            });
            this.tableData.push(utils.sheet_to_json(workbook.Sheets[item]));
          });
          // 该算法仅针对表头无合并的情况
          if (this.tableData.length > 0) {
            // 获取excel中第一个表格数据tableData[0][0]，并且将表头提取出来
            for (const key in this.tableData[0][0]) {
              this.tableHead.push(key);
            }
          }
          return params;
          // 重写数据
        } catch (e) {
          console.log("error:" + e);
          return false;
        }
      };
      fileReader.readAsBinaryString(file[0].raw);
    }
  }
}
</script>
<style  scoped>
.upload-demo {
  width: 100%;
}
.flex-display {
  margin: 50px 30px;
  width: 100%;
  display: flex;
  justify-content: flex-start;

}
.left-box {
//margin: 20 30;
  height: 36px;
  line-height: 36px;
}
.el-upload {
  margin-left: 40px;

}
.el-btn {
  font-size: 16px;
}
.el-upload-tip {
  display: inline;
  font-size: 12px;
}
.file-ipt {
  width: 200px;
  height: 36px;
  line-height: 36px;
  button {
    background-color: #409eff;
  }
}
input #file-upload-button {
  background-color: #409eff;
}
</style>
