<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
              <el-breadcrumb-item>班级实验项目管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <br/>

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
            <template slot-scope="scope">

                <el-button
                size="mini"
                type="danger"
                @click="handleClickDelete(scope.row)">删除</el-button>

                <el-button
                size="mini"
                @click="handleCheck(scope.row)">查看详情</el-button>

            </template>
            </el-table-column>
        </el-table>

        <h1 style="text-align:left">全部实验报告列表</h1>
        <el-table
            :data="allExperimentTable.filter(data => !allSearch || data.experimentname.toLowerCase().includes(allSearch.toLowerCase()))"
            style="width: 100%"
            height="400"
            highlight-current-row
            @current-change="handleSelectExperimentChange">

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
                v-model="allSearch"
                size="mini"
                placeholder="输入项目名称搜索"/>
            </template>
            <template slot-scope="scope">
                <el-button
                size="mini"
                @click="handleCheck(scope.row)">查看详情</el-button>
            </template>
            </el-table-column>
        </el-table>

        <br/>
        <el-button 
        type="primary" 
        plain 
        class="experiment-button"
        @click="addExperiment"
        >加入实验报告</el-button> 

        <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
            <span>要删除这个实验吗</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleDelete">确 定</el-button>
            </span>
        </el-dialog>
    </Contentfield>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
export default {
    //组件
    components: { Contentfield,},
    //名称
    name: "ClassExperiment",
    //数据
    data(){

        return{
            //用户Id
            userId:3,
            //存储班级
            experimentClassNo:0,
            //班级内实验报告列表
            classExperimentTable:[],
            //班级内查找报告
            classSearch:"",
            //所有实验报告列表
            allExperimentTable:[],
            //搜索所有实验报告列表
            allSearch:"",
            //存储要加入的实验报告
            addExperimentList:{},
            //是否显示弹窗
            dialogVisible:false,
            //存储要删除的数据
            deletInfo:{}
        }
    },
    //函数
    methods:{

        //处理报告查看详情
        handleCheck(row){
            row["path"] = "/classexperiment"
            this.$router.push({path:'/experimentdetail',query:row})
        },

        //处理删除
        handleDelete(){
            this.dialogVisible = false
             //发送的数据
             let data = {
                method: "post",
                url:"/class/deleteclassexperiment",
                params: {"experimentNo": this.deletInfo.experimentno, "experimentClassNo": this.experimentClassNo}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("删除实验报告成功");
                }
                else{
                    this.$message.error("删除实验报告失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("删除实验报告失败");
            })
        },

        //向班级加入实验报告
        addExperiment(){
            //判断是否选择实验报告
            if({} == this.addExperimentList){
                this.$message.error("请选择要加入的实验报告")
                return
            }
            //发送的数据
            let data = {
                method: "post",
                url:"/experiment/publishexperiment",
                params: {"experimentNo": this.addExperimentList.experimentno, "experimentClassNo": this.experimentClassNo}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("加入实验报告成功");
                }
                else{
                    this.$message.error("加入实验报告失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("加入实验报告失败");
            })
        },

        //处理增加实验项目
        handleSelectExperimentChange(val){
            this.addExperimentList = val
        },

        //处理点击删除
        handleClickDelete(row){
            this.dialogVisible = true
            this.deletInfo = row
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

        //获取所有实验项目
        getexperimentList(){
            //请求信息
            let data = {
                method: "get",
                url: "/experiment/getexperimentlist",
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.allExperimentTable = response.data.result
                }
                else{
                    this.$message.error("所有实验项目数据获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("所有实验项目数据获取失败");
            })
        },

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
        }
    },
    //初始化钩子函数
    mounted(){
        //获取班级内实验列表
        this.getClassExperimentList()
        //获取所有实验项目列表
        this.getexperimentList()
        //获取当前班级
        this.getExperimentClass()
    }
}
</script>

<style scoped>
.experiment-button{
    position:relative;
    left: -44.4%;
}
</style>