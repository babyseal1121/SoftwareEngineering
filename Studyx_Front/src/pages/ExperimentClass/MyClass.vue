<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
              <el-breadcrumb-item>我的班级</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <br/>

        <h1 style="text-align:left">班级名称：{{this.experimentClassInfo.experimentclassname}}</h1>
        <h1 style="text-align:left">课程时间：{{this.experimentClassInfo.classstarttime}}</h1>
        <h1 style="text-align:left">班级编号：{{this.experimentClassNo}}</h1>

        <h1 style="text-align:left">班级内实验项目列表</h1>
        <el-table
            :data="classExperimentTable.filter(data => !classSearch || data.experimentname.toLowerCase().includes(classSearch.toLowerCase()))"
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
            align="right">
            <template slot="header">
                <el-input
                v-model="classSearch"
                size="mini"
                placeholder="输入关键字搜索"/>
            </template>
            </el-table-column>
        </el-table>

        <h1 style="text-align:left">班级内人员列表</h1>
        <el-table
        :data="classUserTable.filter(data => !classSearch || data.username.toLowerCase().includes(classSearch.toLowerCase()))"
        style="width: 100%"
        height="400">

            <el-table-column
            label="学生ID"
            prop="userid">
            </el-table-column>

            <el-table-column
            label="学生名称"
            prop="username">
            </el-table-column>

            <el-table-column
            prop="level"
            label="学生职务"
            width="150"
            :filters="[
            { text: '助教', value: '助教' },
            { text: '学生', value: '学生' },]"
            filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                        :type="
                        scope.row.level == '助教'
                            ? 'warning'
                            : scope.row.level == '学生'
                            ? 'success'
                            : 'primary'"
                        disable-transitions
                    >{{ scope.row.level }}</el-tag>
                </template>
            </el-table-column>

            <el-table-column
            label="学生状态"
            prop="status">
            </el-table-column>

            <el-table-column
            align="right">

                <template slot="header">
                    <el-input
                    v-model="classSearch"
                    size="mini"
                    placeholder="输入学生名称搜索"/>
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
    name: "MyClass",
    //数据
    data(){

        return{
            //用户Id
            userId:5,
            //存储班级
            experimentClassNo:0,
            //存储班级详情
            experimentClassInfo:{},
            //存放班级报告信息
            classExperimentTable:[],
            //搜索报告
            classSearch:"",
            //存放班级人员信息
            classUserTable:[],
            //搜索人员
            classSearch:""
        }
    },
    //函数
    methods:{
        //获取当前班级
        getExperimentClass(){
            //请求的信息
            let data = {
                method: "get",
                url: "/class/findexperimentclass",
                params: {"userId": this.userId}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.experimentClassNo = response.data.result;
               }
               else{
                    this.$message.error("班级获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级获取失败");
            })
        },

        //获取班级详情
        getExperimentClassInfo(){
            //请求的信息
            let data = {
                method: "get",
                url: "/class/getexperimentclass",
                params: {"userId": this.userId}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.experimentClassInfo = response.data.result;
               }
               else{
                    this.$message.error("班级详情获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级详情获取失败");
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
                   this.classExperimentTable = response.data.result;
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

         //获取本班人员列表
         getClassUserList(){
            //请求的信息
            let data = {
                method: "get",
                url: "/experiment/getallstudentinclass",
                params: {"userId": this.userId}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.classUserTable = response.data.result;
               }
               else{
                    this.$message.error("班级内人员信息获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级内人员信息获取失败");
            })
        },
    },
    //初始化钩子函数
    mounted(){
        //获取当前班级
        this.getExperimentClass()
        //获取班级详细信息
        this.getExperimentClassInfo()
        //获取本班项目列表
        this.getClassExperimentList()
        //获取本班人员
        this.getClassUserList()
    }
}
</script>

<style>

</style>