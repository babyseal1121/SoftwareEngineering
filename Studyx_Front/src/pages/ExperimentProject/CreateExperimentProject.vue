<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/experimentprojectmanagement'}">返回</el-breadcrumb-item>
            <el-breadcrumb-item>创建新的实验项目</el-breadcrumb-item>
        </el-breadcrumb>

        <br/>
        <div class="experiment-div">
            
            <h1 class="experiment-title">
                实验名称
            </h1>
            <el-input
            placeholder="请输入实验名称"
            v-model="experimentInfo.experimentname"
            clearable
            class="experiment-text">
            </el-input>        
            <el-row :gutter="12">  
                <el-col :span="22">
                    <h1 class="experiment-title">实验目的</h1>
                    <el-input
                    type="textarea"
                    :rows="6"
                    placeholder="请输入实验目的"
                    v-model="experimentInfo.experimentpurpose">
                    </el-input>
                </el-col>             
                <el-col :span="22">
                    <h1 class="experiment-title">相关知识</h1>
                    <el-input
                    type="textarea"
                    :rows="6"
                    placeholder="请输入实验的相关知识"
                    v-model="experimentInfo.experimentbasicknowledge">
                    </el-input>
                </el-col>   
                <el-col :span="22">
                    <h1 class="experiment-title">实验步骤</h1>
                    <el-input
                    type="textarea"
                    :rows="6"
                    placeholder="请输入实验步骤"
                    v-model="experimentInfo.experimentsteps">
                    </el-input>
                </el-col>   
                <el-col :span="22">
                    <h1 class="experiment-title">问题与思考</h1>
                    <el-input
                    type="textarea"
                    :rows="6"
                    placeholder="请输入问题与思考"
                    v-model="experimentInfo.experimentquestion">
                    </el-input>
                </el-col>  
                <el-col :span="22">
                    <br/>
                    <el-button 
                    type="primary" 
                    plain 
                    class="experiment-button"
                    @click="submitExperimentProject"
                    >提交实验项目</el-button>    
                </el-col> 
            </el-row>                
        </div>
    </div> 
</template>

<script>
export default {
    //名称
    name:"CreateExperimentProject",
    //数据
    data(){

        return{
            experimentInfo:{
                experimentno:0,
                experimentname:"",
                experimentpurpose:"",
                experimentbasicknowledge:"",
                experimentsteps:"",
                experimentquestion:""
            }
        }
    },
    //函数
    methods: {
        //检查实验项目完整性
        checkExperimentProject(){
            if(this.experimentInfo.experimentname == ""){
                this.$message.error("实验项目的名称不可为空");
                return false
            }
            else if(this.experimentInfo.experimentpurpose == ""){
                this.$message.error("实验目的不可为空");
                return false
            }
            else if(this.experimentInfo.experimentbasicknowledge == ""){
                this.$message.error("实验项目的相关知识不可为空");
                return false
            }
            else if(this.experimentInfo.experimentsteps == ""){
                this.$message.error("实验项目的步骤不可为空");
                return false
            }
            else if(this.experimentInfo.experimentquestion == ""){
                this.$message.error("实验项目的问题与思考不可为空");
                return false
            }
            else{
                return true
            }
        },

        //提交新的实验项目
        submitExperimentProject(){
             //检查实验项目完整性
            if(this.checkExperimentProject()){

                //发送的数据
                let data = {
                    method: "post",
                    url:"/experiment/createexperiment",
                    data: this.experimentInfo
                }
                //发送请求
                this.$axios.request(data)
                .then(response => {
                        //如果请求成功
                    if(200 == response.data.code){
                        //获取数据
                        this.$message.success("提交成功");
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
        }
    },
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
    width: 91%;
    position:relative;
    left:-4.4%;
}

.experiment-button{
    position:relative;
    left: -44.4%;
}
</style>