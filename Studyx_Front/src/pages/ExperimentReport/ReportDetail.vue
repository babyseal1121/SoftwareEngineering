<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/myreport'}">返回</el-breadcrumb-item>
            <el-breadcrumb-item>实验报告详情</el-breadcrumb-item>
        </el-breadcrumb>
        <br/>
        <div class="experiment-div">
            
            <h1 class="experiment-title">
                实验名称：{{this.experimentDetail.experimentname}}
            </h1>
            <h1 class="experiment-title">
                实验编号：{{this.experimentDetail.experimentno}}
            </h1>
            
            <el-row :gutter="12">  
                <el-col :span="22">
                    <h1 class="experiment-title">实验目的</h1>
                    <el-card shadow="hover">
                        <h3 class="experiment-text">{{this.experimentDetail.experimentpurpose}}</h3>
                    </el-card>
                </el-col>             
                <el-col :span="22">
                    <h1 class="experiment-title">相关知识</h1>
                    <el-card shadow="hover">
                        <h3 class="experiment-text">{{this.experimentDetail.experimentbasicknowledge}}</h3>
                    </el-card>
                </el-col>   
                <el-col :span="22">
                    <h1 class="experiment-title">实验步骤</h1>
                    <el-card shadow="hover">
                        <h3 class="experiment-text">{{this.experimentDetail.experimentsteps}}</h3>
                    </el-card>
                </el-col>
                <el-col :span="22">
                    <h1 class="experiment-title">实验现象</h1>
                    <el-card shadow="hover">
                        <h3 class="experiment-text">{{this.reportInfo.experimentphenomenon}}</h3>
                    </el-card>
                </el-col>    
                <el-col :span="22">
                    <h1 class="experiment-title">问题与思考</h1>
                    <el-card shadow="hover">
                        <h3 class="experiment-text">{{this.experimentDetail.experimentquestion}}</h3>
                    </el-card>
                </el-col>   
                <el-col :span="22">
                    <h1 class="experiment-title">请回答问题与思考并记录实验的感悟</h1>
                    <el-card shadow="hover">
                        <h3 class="experiment-text">{{this.reportInfo.experimentperception}}</h3>
                    </el-card>
                </el-col> 
                <el-col :span="22">
                    <h1 class="experiment-title">教师评语</h1>
                    <el-card shadow="hover">
                        <h3 class="experiment-text">{{this.reportInfo.experimentcomments}}</h3>
                    </el-card>
                </el-col> 
                <el-col :span="22">
                    <h1 class="experiment-title">报告评分：{{this.reportInfo.experimentgrade}}</h1>
                </el-col> 
            </el-row>       
        </div>
    </div>
</template>

<script>
export default {
    //名称
    name: "ReportDetail",
    //数据
    data(){

        return{
            //实验编号
            experimentNo:0,
            //实验报告编号
            experimentReportNo:0,
            //实验细节
            experimentDetail:{},
            //报告内容
            reportInfo:{},
        }
    },
    //函数
    methods:{
        //根据实验编号请求信息
        getExperimentDetail(){
            //请求的信息
            let data = {
                method: "get",
                url: "/experiment/getexperiment",
                params: {"experimentNo": this.experimentNo}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.experimentDetail = response.data.result;
               }
               else{
                    this.$message.error("项目数据获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("项目数据获取失败");
            })
        },

        //根据实验报告编号请求信息
        getExperimentReportDetail(){
            //请求的信息
            let data = {
                method: "get",
                url: "/experiment/getexperimentreport",
                params: {"experimentReportNo": this.experimentReportNo}
            }
             //发送请求
             this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.reportInfo = response.data.result;
               }
               else{
                    this.$message.error("报告数据获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("报告数据获取失败");
            })
        }
    },
    //初始时调用
    mounted(){
         //获取实验编号
         this.experimentNo = this.$route.query.experimentno
        //获取实验报告编号
        this.experimentReportNo = this.$route.query.experimentreportno
        //获取实验项目内容
        this.getExperimentDetail()
        //获取实验报告内容
        this.getExperimentReportDetail()
    }
}
</script>

<style scoped>
.experiment-title{
    font-family:"PingFang SC";
    font-size:25px;
    color:black;
    text-align:left
}

.experiment-div{
    width: 100%;
    height: 420px;
}

.experiment-text{
    font-family:"PingFang SC";
    font-size:15px;
    color:black;
    text-align:left;
    width: 95%;
}
</style>