<template>
  <Contentfield>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/admin/adminborrow">添加用户</a></el-breadcrumb-item>
      </el-breadcrumb>

      <div class="flex-display">

        <!-- <el-button size="small" type="primary" class="el-btn" @click="add">
          创建新用户
        </el-button> -->

        <el-upload    
          ref="upload"       
          action=""
          :limit="1"
          :on-exceed="handleExceed"
          :file-list="fileList"
          :http-request="onUpload"
          :auto-upload="false"
          class="el-upload">            
          <el-tooltip class="item" effect="dark" content="只能上传xlsx文件，且不超过5MB" placement="top-start">
            <el-button size="small" type="primary" class="el-btn">
              导入用户信息
            </el-button>
          </el-tooltip>
        </el-upload>

        <el-button style="margin-left: 10px;" size="small" type="success" class="el-btn" @click="submitUpload">上传</el-button>
        
        <el-button size="small" type="primary" class="el-btn" @click="handleGetTemplate">
          获取信息导入模板
        </el-button>

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
      //上传文件列表
      fileList: [], 
      //表头
      tableHead: [], 
      //表数据
      tableData: []
    };
  },

  methods: {

     //选择上传文件
    submitUpload(){
        this.$refs.upload.submit();
    },

    //处理上传文件
    onUpload(file){
        //获取上传文件内容
        let formData = new FormData();
        formData.append("file", file.file);
        //请求信息
        let data = {
            method: "post",
            url: "/batchupload",
            data: formData,
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        //发送请求
        this.$axios.request(data)
            .then(response => {
                //如果请求成功
                if(200 == response.data.code){
                    this.$message.success("文件上传成功");
                    let res = ""
                    this.handleChange(res, file, this.fileList)
                }
                else{
                    this.$message.error("文件上传失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("文件上传失败");
          })
    },

    //限制只能上传一个文件
    handleExceed(){
        this.$message.warning("每次只能上传一个文件");
    },

    //获取表模板
    handleGetTemplate(){
        let link = document.createElement('a');
        link.style.display = 'none';
        link.href = "/static/file/template.xlsx"
        link.download = "template.xlsx";
        link.click();
    },

    //很可爱的一个函数
    add(){
      this.$message.success("test");
    },

    //上传成功？
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
      const files = { 0: file };
      this.readExcel(files);
    },

    //查看上传的内容
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
      fileReader.readAsBinaryString(file[0].file);
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
  margin: 20 30;
  height: 36px;
  line-height: 36px;
}

.el-upload {
  position:relative;
  left: -2.5%;
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
}

input #file-upload-button {
  background-color: #409eff;
}
</style>
