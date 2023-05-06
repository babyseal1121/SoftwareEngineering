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
        style="width: 100%;margin-bottom: 20px;"
        row-key="experimentreportno"
        border
        default-expand-all
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}">

           
            <el-table-column
            prop="experimentname"
            label="实验名称">
            </el-table-column>

            <el-table-column
            prop="experimentno"
            label="实验编号"
            width="100">
            </el-table-column>

            <el-table-column
            prop="userid"
            label="学生ID"
            width="100">
            </el-table-column>

            <el-table-column
            prop="username"
            label="学生姓名"
            width="150">
            </el-table-column>

            <el-table-column
            prop="experimentgrade"
            label="成绩"
            width="100">
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
                v-show="ifShowButton(scope.row.experimentreportno)"
                @click="handleEdit(scope.row)">批改报告</el-button>
            </template>
            </el-table-column>
            
        </el-table>
        
    </Contentfield>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
import { Select } from 'element-ui';
export default {
    //组件
    components: { Contentfield,},
    //名称
    name: "CorrectReportUpdate",
    //数据
    data(){

        return{
            //存放展示的信息
            tableData: [],

            //用于存放实验报告的数据
            experimentReportInfo: [],

            //用于存放实验信息
            experimentInfo: [],
       
            //存放用户Id
            userId: this.$store.getters.userId,

            //用于搜素指定实验项目
            search: ''
        }


    },

     //函数
     methods:{
        // 判断是否应当显示按钮
        ifShowButton(rowExperimentreportno) {
            if(rowExperimentreportno < 0)
                return false;
            else
                return true;
        },

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
                    this.experimentReportInfo = response.data.result
                    this.ManipulationData();
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

        //获取本班项目列表
        getClassExperimentList(){
            //请求的信息
            let data = {
                method: "get",
                url: "/experiment/getallexperimentinclass",
                params: {"userId": this.userId}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.experimentInfo = response.data.result;
                   this.getExperimentReportList()
               }
               else{
                    this.$message.error("班级内实验数据获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级内实验数据获取失败");
            })
        },

        //处理实验报告的数据
        ManipulationData()
        {
            let each = 0;

            for (each in this.experimentInfo)
            {
                // 为了填充表格加入不含有的项
                this.experimentInfo[each]["userid"] = null;
                this.experimentInfo[each]["username"] = null;
                this.experimentInfo[each]["experimentgrade"] = null;
                this.experimentInfo[each]["children"] = []
                // 用于当作key
                this.experimentInfo[each]["experimentreportno"] = (each + 1) * -1;

                let eachOne = 0;

                // 查找对应的报告并加入
                for(eachOne in this.experimentReportInfo)
                {
                    if(this.experimentReportInfo[eachOne]["experimentno"] == this.experimentInfo[each]["experimentno"])
                    {
                        this.experimentReportInfo[eachOne]["experimentno"] = null;
                        this.experimentReportInfo[eachOne]["experimentname"] = null;
                        this.experimentInfo[each]["children"].push(this.experimentReportInfo[eachOne])
                    }
                }

                this.tableData = this.experimentInfo;
            }
        }
    },
    //加载时操作
    mounted(){
        //获取实验列表
        this.getClassExperimentList()
    },

}
</script>

<style>

</style>