<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/correctreportupdate'}">返回</el-breadcrumb-item>
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
                    <h1 class="experiment-title">请评分</h1>
                    <el-input-number 
                    v-model="experimentGrade" 
                    :min="0" 
                    :max="10" 
                    class="experiment-input-box"
                    label="描述文字"></el-input-number>
                </el-col> 
                <el-col :span="22">
                    <h1 class="experiment-title">请输入评语</h1>
                    <el-input
                    type="textarea"
                    :rows="4"
                    placeholder="请输入评语(小于100字)"
                    v-model="experimentComments">
                    </el-input>
                </el-col> 
                <el-col :span="22">
                    <br/>
                    <el-button 
                    type="primary" 
                    plain 
                    class="experiment-button"
                    @click="submitExperimentReportCorrect"
                    >提交批改</el-button>    

                    <el-button 
                    type="primary" 
                    plain 
                    class="get-file-button"
                    @click="getExperimentReportFile"
                    >查看报告</el-button>    
                </el-col> 
            </el-row>       
        </div>
    </div>
    
</template>

<script>
export default {
    //名称
    name:"CorrectReportDetail",
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
            //报告得分
            experimentGrade:0,
            //报告评价
            experimentComments:"",
            //报告文件路径
            reportPath:""
        }
    },
    //函数
    methods:{
        
        //获取实验报告文件
        getExperimentReportFile(){
            // 判断是否存在对应文件
            if("none" == this.reportPath)
            {
                this.$message.error("本报告未上传文件");
                return
            }
            let link = document.createElement('a');
            link.style.display = 'none';
            link.href = "http://localhost:8443/api/experiment/getexperimentreportfile?reportPath=" + this.reportPath;
            link.download = (this.reportPath.split("/"))[3];
            //document.body.appendChild(link);
            link.click();
        },

        //提交批改
        submitExperimentReportCorrect(){
            //判断是否有评语
            if("" == this.experimentComments){
                this.$message.error("请输入评语")
                return
            }
            //请求的信息
            let data = {
                method: "post",
                url:"/experiment/appraiseexperimentreport",
                params: {
                    "experimentReportNo": this.experimentReportNo,
                    "experimentGrade": this.experimentGrade,
                    "experimentComments": this.experimentComments
                }
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.$message.success("提交批改成功")
               }
               else{
                    this.$message.error("提交批改失败")
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("提交批改失败")
            })
        },

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
                    this.experimentNo = this.reportInfo.experimentno
                    this.experimentGrade = this.reportInfo.experimentgrade
                    this.experimentComments = this.reportInfo.experimentcomments
                    //获取报告路径
                    this.reportPath = this.reportInfo.reportpath
                    //获取实验项目内容
                    this.getExperimentDetail()
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
    mounted(){
        //获取实验报告编号
        this.experimentReportNo = this.$route.query.experimentreportno
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

.experiment-button{
    position:relative;
    left: -40.6%;
}

.get-file-button{
    position:relative;
    right: -40.6%;
}

.experiment-input-box{
    position:relative;
    left: -42%;
}
</style>