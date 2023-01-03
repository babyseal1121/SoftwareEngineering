<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/showstudyfile'}">返回</el-breadcrumb-item>
                <el-breadcrumb-item>实验项目对应文件</el-breadcrumb-item>
            </el-breadcrumb>
            <br/>

            <el-table
            :data="tableData.filter(data => !search || data.materialname.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="330">

                <el-table-column
                label="文件名称"
                prop="materialname">
                </el-table-column>

                <el-table-column
                label="文件编号"
                prop="materialno">
                </el-table-column>

                <el-table-column
                label="上传时间"
                prop="materialuploadtime">
                </el-table-column>

                <el-table-column
                align="right">
                <template slot="header">
                    <el-input
                    v-model="search"
                    size="mini"
                    placeholder="输入关键字搜索"/>
                </template>
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.row)">下载</el-button>
                </template>
                </el-table-column>
            </el-table>

            <br/>

            <el-upload
            class="upload-button"
            ref="upload"
            action=""
            :limit="1"
            :on-exceed="handleExceed"
            :file-list="fileList"
            :http-request="onUpload"
            :auto-upload="false">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传</el-button>
                <div slot="tip" class="el-upload__tip">文件不要超过10Mb</div>
            </el-upload>
        </div>
    </Contentfield>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
export default {

    //组件
    components: { Contentfield,},

    //名称
    name: "ExperimentFile",

    //数据
    data(){

        return{
            //存放文件信息
            tableData: [],
            //用于搜素指定文件
            search: '',
            //存放当前路径
            nowPath:"",
            //存放当前上传文件列表
            fileList:[],
            //存放当前时间
            nowTime:""
        }
    },

    //函数
    methods: {
        
        //处理下载
        handleEdit(row) {
            let link = document.createElement('a');
            link.style.display = 'none';
            link.href = "http://1.117.227.19:8443/api/studymaterial/getstudymaterial?materialNo=" + row.materialno;
            link.download = row.materialname;
            //document.body.appendChild(link);
            link.click();
        },

        //获取文件列表
        getStudyMaterialList(){
            //请求信息
            let data = {
                method: "get",
                url: "/studymaterial/getstudymateriallist",
                params: {"experimentName": this.nowPath}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.tableData = response.data.result
                }
                else{
                    this.$message.error("数据获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("数据获取失败");
            })
        },

        //限制只能上传一个文件
        handleExceed(){
            this.$message.warning("每次只能上传一个文件");
        },

        //选择上传文件
        submitUpload(){
            this.$refs.upload.submit();
        },

        //获取当前时间
        getNowTime(){
            //加入时间戳
            let nowDate = new Date()
            let year = nowDate.getFullYear()
            let month = nowDate.getMonth() + 1
            let day = nowDate.getDate()
            this.nowTime =  year + "-" + month + "-" + day
        },

        //处理上传文件
        onUpload(file){
            //获取当前时间
            this.getNowTime()
            //获取上传文件内容
            let formData = new FormData();
            formData.append("studyFile", file.file);
            //请求信息
            let data = {
                method: "post",
                url: "/studymaterial/submitstudymaterial",
                data: formData,
                params: {
                    "fileName": file.file.name,
                    "pathName": this.nowPath,
                    "uploadTime": this.nowTime
                },
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
                }
                else{
                    this.$message.error("文件上传失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("文件上传失败");
            })
        }
    },

    //初始化钩子函数
    mounted(){
        //获取当前路径(/实验项目名/)
        this.nowPath = "/" + this.$route.query.experimentname + "/"
        //获取文件列表
        this.getStudyMaterialList()
    }
}
</script>

<style scoped>

.upload-button{
    position:relative;
    left: -43.5%;
}
</style>