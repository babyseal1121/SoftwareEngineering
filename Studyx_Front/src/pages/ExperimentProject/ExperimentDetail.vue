<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: this.backPath }">返回</el-breadcrumb-item>
            <el-breadcrumb-item>实验信息详情页面</el-breadcrumb-item>
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
                    <h1 class="experiment-title">问题与思考</h1>
                    <el-card shadow="hover">
                        <h3 class="experiment-text">{{this.experimentDetail.experimentquestion}}</h3>
                    </el-card>
                </el-col>   
            </el-row>       
        </div>
    </div>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
export default {
    //组件
    components: { Contentfield,},
    //名称
    name:"ExperimentDetail",
    //数据
    data(){

        return{
            //实验编号
            experimentNo:0,
            //实验细节
            experimentDetail:{},
            //返回的路径
            backPath:""
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
        }
    },
    mounted(){
        //获取实验编号
        this.experimentNo = this.$route.query.experimentno
        //获取返回路径
        this.backPath = this.$route.query.path
        //请求信息
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
</style>