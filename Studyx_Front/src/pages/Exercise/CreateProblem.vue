<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/problemmanagement'}">测试题目管理</el-breadcrumb-item>
            <!-- <el-breadcrumb-item :to="{ path: '/experimentproblem'}">实验项目题目管理</el-breadcrumb-item> -->
            <el-breadcrumb-item>添加新题目</el-breadcrumb-item>
        </el-breadcrumb>

        <br/>
        <div class="experiment-div">      
            <el-row :gutter="12">  
                <el-col :span="22">
                    <h1 class="experiment-title">题目描述</h1>
                    <el-input
                    type="textarea"
                    :rows="3"
                    placeholder="请输入题目描述(小于100字)"
                    v-model="problemInfo.problemdescription">
                    </el-input>
                </el-col>             
                <el-col :span="22">
                    <h1 class="experiment-title">选项A</h1>
                    <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入选项A(小于50字)"
                    v-model="problemInfo.problemoptiona">
                    </el-input>
                </el-col>   
                <el-col :span="22">
                    <h1 class="experiment-title">选项B</h1>
                    <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入选项B(小于50字)"
                    v-model="problemInfo.problemoptionb">
                    </el-input>
                </el-col>   
                <el-col :span="22">
                    <h1 class="experiment-title">选项C</h1>
                    <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入选项C(小于50字)"
                    v-model="problemInfo.problemoptionc">
                    </el-input>
                </el-col>   
                <el-col :span="22">
                    <h1 class="experiment-title">选项D</h1>
                    <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入选项D(小于50字)"
                    v-model="problemInfo.problemoptiond">
                    </el-input>
                </el-col>   
                <el-col :span="22">
                    <h1 class="experiment-title">题目答案</h1>
                    <el-select 
                    v-model="problemInfo.problemanswer" 
                    placeholder="请选择题目答案"
                    class="problem-select">
                        <el-option
                          v-for="item in options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                </el-col>   
                <el-col :span="22">
                    <br/>
                    <el-button 
                    type="primary" 
                    plain 
                    @click="CreateProblem"
                    >创建题目</el-button>    
                </el-col> 
            </el-row>                
        </div>
    </div>
</template>

<script>
export default {
    //名称
    name: "CreateProblem",
    //数据
    data(){

        return{
            //题目的信息
            problemInfo:{
                problemdescription:"",
                problemno:0,
                experimentno:0,
                experimentname:"",
                problemoptiona:"",
                problemoptionb:"",
                problemoptionc:"",
                problemoptiond:"",
                problemanswer:"",
            },
            //实验的编号
            experimentNo:0,
            //实验的名称
            experimentName:"",
            //下拉框选项
            options: [{
            value: 'A',
            label: 'A'
            }, 
            {
            value: 'B',
            label: 'B'
            }, 
            {
            value: 'C',
            label: 'C'
            }, 
            {
            value: 'D',
            label: 'D'
            }],
        }
    },
    //函数
    methods:{
        //提交题目
        CreateProblem(){
            //检查有无题目描述
            if("" == this.problemInfo.problemdescription){
                this.$message.success("请输入题目描述")
            }
            //加入项目信息
            this.problemInfo.experimentname = this.experimentName
            this.problemInfo.experimentno = this.experimentNo
            //请求数据
            let data = {
                    method: "post",
                    url:"/exercise/createexerciseproblem",
                    data: this.problemInfo
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("新题目创建成功");
                }
                else{
                    this.$message.error("新题目创建失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("新题目创建失败");
            })
        }
    },
    //初始化钩子函数
    mounted(){
        //获取当前的实验信息
        this.experimentNo = this.$route.query.experimentNo
        this.experimentName = this.$route.query.experimentName
        console.log(this.experimentNo)
        console.log(this.experimentName)
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
.problem-select{
    position:relative;
    left: -40%;
}
</style>