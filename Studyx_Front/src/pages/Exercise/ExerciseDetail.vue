<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/myexercise'}">我的测试</el-breadcrumb-item>
            <el-breadcrumb-item>测试详情</el-breadcrumb-item>
        </el-breadcrumb>
        <br/>

        <el-table
            :data="tableData.filter(data => !search || data.problemdescription.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="430">

               <!--展开栏-->
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form label-position="right" inline class="demo-table-expand">
                        <el-form-item label="题目编号">
                            <span>{{ props.row.problemno }}</span>
                        </el-form-item>
                        <el-form-item label="实验编号">
                            <span>{{ props.row.experimentno }}</span>
                        </el-form-item>
                        <el-form-item label="实验名称">
                            <span>{{ props.row.experimentname }}</span>
                        </el-form-item>
                        <el-form-item label="题目描述">
                            <span>{{ props.row.problemdescription }}</span>
                            </el-form-item>
                        <el-form-item label="选项A">
                            <span>{{ props.row.problemoptiona }}</span>
                        </el-form-item>
                        <el-form-item label="选项B">
                            <span>{{ props.row.problemoptionb }}</span>
                        </el-form-item>
                        <el-form-item label="选项C">
                            <span>{{ props.row.problemoptionc }}</span>
                        </el-form-item>
                        <el-form-item label="选项D">
                            <span>{{ props.row.problemoptiond }}</span>
                        </el-form-item>
                        <el-form-item label="题目答案">
                            <span>{{ props.row.problemanswer }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>

            <el-table-column
            label="题目编号"
            prop="problemno">
            </el-table-column>

            <el-table-column
            label="所属实验名称"
            prop="experimentname">
            </el-table-column>

            <el-table-column
            label="题目描述"
            prop="problemdescription">
            </el-table-column>

            <el-table-column
            align="right">
            <template slot="header">
                <el-input
                v-model="search"
                size="mini"
                placeholder="输入题目描述关键字搜索"/>
            </template>
            </el-table-column>
        </el-table>
        <br/>
        <h1 class="experiment-title">
            测试得分：{{this.exerciseResult.exercisegrade}} 测试用时：{{this.exerciseResult.exerciseusetime}}
        </h1>
    </div>
</template>

<script>
export default {
    //名称
    name: "ExerciseDetail",
    //数据
    data(){

        return{
            //存放题目信息
            tableData: [],
            //用于搜素指定题目
            search: '',
            //当前测试编号
            exerciseNo: 0,
            //用户Id
            userId:this.$store.getters.userId,
            //用于存放考试结果
            exerciseResult:{}
        }
    },
    //函数
    methods:{

         //获取当前题目列表
         getProblemList(){
            //请求数据
            let data = {
                method: "get",
                url: "/exercise/getexerciseproblemlist",
                params: {"exerciseNo": this.exerciseNo}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.tableData = response.data.result
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

        //获取当前测试情况
        getExerciseResult(){
            //请求数据
            let data = {
                method: "get",
                url: "/exercise/getexerciseresult",
                params: {
                    "userId": this.userId,
                    "exerciseNo": this.exerciseNo
                }
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.exerciseResult = response.data.result
                }
                else{
                    this.$message.error("数据获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("数据获取失败");
            })
        }
    },
    //初始化钩子函数
    mounted(){
        //实验编号
        this.exerciseNo = this.$route.query.exerciseno
        //获取题目列表
        this.getProblemList()
        //获取测试详情
        this.getExerciseResult()
    }
}
</script>

<style scoped>
.demo-table-expand {
    font-size: 0;
  }
.demo-table-expand label {
width: 90px;
color: #99a9bf;
}
.demo-table-expand .el-form-item {
margin-right: 50px;
margin-bottom: 0;
width: 100%;
height: 100%;
}
.experiment-title{
    font-family:"PingFang SC";
    font-size:25px;
    color:black;
    text-align:left
}
</style>