<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
            <el-breadcrumb-item>批改实验报告</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <br/>
        <el-table
            :data="tableData.filter(data => !search || data.experimentname.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="380">
            <el-table-column
            label="实验编号"
            prop="experimentno">
            </el-table-column>
            <el-table-column
            label="实验名称"
            prop="experimentname">
            </el-table-column>
            <el-table-column
            label="完成人ID"
            prop="userid">
            </el-table-column>
            <el-table-column
            label="完成人"
            prop="username">
            </el-table-column>
            <el-table-column
            label="报告编号"
            prop="experimentreportno">
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
                @click="handleEdit(scope.row)">批改报告</el-button>
            </template>
            </el-table-column>
        </el-table>
    </Contentfield>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
export default {
    //组件
    components: { Contentfield,},
    //名称
    name: "CorrectReport",
    //数据
    data(){

        return{
            //存放实验项目信息
            tableData: [],
            //用于搜素指定实验项目
            search: '',
            //存放用户Id
            userId:3
        }
    },
    //函数
    methods:{
        //处理获取书写报告
        handleEdit(row) {
            this.$router.push({path:"/correctreportdetail",query:row})
        },

        //获取报告列表
        getExperimentReportList() {
            //请求的数据
            let data = {
                method: "get",
                url:"/experiment/getclassexperimentreportlist",
                params: {"userId": this.userId}
            }
            //发起请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                    this.tableData = response.data.result
               }
               else{
                    this.$message.error("数据获取失败");
               }
            })
            .catch(failResponse => {
                this.$message.error("数据获取失败");
            })
        },
    },
    //加载时操作
    mounted(){
        //获取报告列表
        this.getExperimentReportList()
    },
}
</script>

<style>

</style>