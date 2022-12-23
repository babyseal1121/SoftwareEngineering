<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/submitreport'}">返回</el-breadcrumb-item>
            <el-breadcrumb-item>完成实验报告</el-breadcrumb-item>
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
                        <el-input
                        type="textarea"
                        :rows="6"
                        placeholder="请输入实验现象(小于900字)"
                        v-model="reportInfo.experimentphenomenon">
                        </el-input>
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
                        <el-input
                        type="textarea"
                        :rows="6"
                        placeholder="请回答问题与思考并记录实验的感悟(小于400字)"
                        v-model="reportInfo.experimentperception">
                        </el-input>
                    </el-card>
                </el-col> 
                <el-col :span="22">
                    <br/>
                    <el-button 
                    type="primary" 
                    plain 
                    class="experiment-button"
                    @click="submitExperimentReport"
                    >提交实验报告</el-button>    
                </el-col> 
            </el-row>       
        </div>
    </div>
</template>

<script>
export default {
    //名称
    name: "FinishReport",
    //数据
    data(){

        return{
            //记录已经提交
            experimentSubmit:false,
            //实验编号
            experimentNo:0,
            //实验细节
            experimentDetail:{},
            //报告内容
            reportInfo:{
                experimentno:0,
                userid:0,
                username:"student",
                experimentphenomenon:"",
                experimentperception:"",
                experimentgrade:0,
                experimentcomments:"",
                experimentsubmissontime:"",
                experimentreportno:0,
                experimentname:""
            }
        }
    },
    //函数
    methods: {
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
                    this.$message.error("数据获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("详情数据获取失败");
            })
        },
        //提交实验报告
        submitExperimentReport(){
            //检查是否已经提交
            if(this.experimentSubmit){
                this.$message.error("不可重复提交")
                return
            }
            //检查是否填写,如果为空
            if("" == this.reportInfo.experimentphenomenon){
                this.$message.error("请完善实验现象")
                return
            }
            else if("" == this.reportInfo.experimentperception){
                this.$message.error("请完善实验感悟")
                return
            }
            //加入时间戳
            let nowDate = new Date()
            let year = nowDate.getFullYear()
            let month = nowDate.getMonth() + 1
            let day = nowDate.getDate()
            this.reportInfo.experimentsubmissontime =  year + "-" + month + "-" + day
            console.log(this.reportInfo.experimentsubmissontime)
            //请求的信息
            let data = {
                    method: "post",
                    url:"/experiment/submitexperimentreport",
                    data: this.reportInfo
                }
            //发送申请
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                    this.$message.success("提交成功")
                    this.experimentSubmit = true;
               }
               else{
                    this.$message.error("提交失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("提交失败");
            })
        }
    },
    //加载时
    mounted(){
        //获取实验编号
        this.experimentNo = this.$route.query.experimentno
        this.reportInfo.experimentno = this.$route.query.experimentno
        //获取实验名称
        this.reportInfo.experimentname = this.$route.query.experimentname
        //获取实验人编号
        this.reportInfo.userid = this.$route.query.userId
        //获取实验详情
        this.getExperimentDetail()
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
    left: -44.4%;
}
</style>