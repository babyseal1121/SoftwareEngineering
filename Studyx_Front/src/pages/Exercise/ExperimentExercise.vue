<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/problemmanagement'}">发布测试</el-breadcrumb-item>
            <el-breadcrumb-item>选择题目</el-breadcrumb-item>
        </el-breadcrumb>

        <br/>
        <el-table
            :data="tableData.filter(data => !search || data.problemdescription.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="400"
            @selection-change="handleSelectionChange">

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
            type="selection"
            width="55">
            </el-table-column>

            <el-table-column
            align="right">
            <template slot="header">
                <el-input
                v-model="search"
                size="mini"
                placeholder="输入关键字搜索"/>
            </template>
            </el-table-column>
        </el-table>

        <!--选择时间-->
        <br/>
        <el-form ref="form" :model="form" label-width="80px">    
            <el-form-item label="测试时间">
              <el-col :span="3">
                <el-date-picker 
                type="date" 
                value-format="yyyy-MM-dd"
                placeholder="选择开始日期" 
                v-model="form.date1" 
                style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col class="line" :span="1">-</el-col>
              <el-col :span="3">
                <el-time-picker 
                value-format="HH:mm:ss" 
                placeholder="选择开始时间" 
                v-model="form.date2" 
                style="width: 100%;"></el-time-picker>
              </el-col>
              <el-col class="line" :span="2">--</el-col>
              <el-col :span="3">
                <el-date-picker 
                type="date"
                value-format="yyyy-MM-dd" 
                placeholder="选择结束日期" 
                v-model="form.date3" 
                style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col class="line" :span="1">-</el-col>
              <el-col :span="3">
                <el-time-picker 
                placeholder="选择结束时间" 
                value-format="HH:mm:ss" 
                v-model="form.date4" 
                style="width: 100%;"></el-time-picker>
              </el-col>
            </el-form-item>
          </el-form>

        <br/>
        <!--发布测试-->
        <el-button 
        type="primary" 
        plain 
        class="experiment-button"
        @click="publishExercise"
        >发布测试</el-button>
    </div>

    
</template>

<script>
export default {
    //名称
    name: "ExperimentExercise",
    //数据
    data(){

        return{
            //存储班级信息
            classInfo:{},
            //存储项目编号
            experimentNo:0,
            //用于搜素指定题目
            search: '',
            //存放题目信息
            tableData: [],
            //存放教师Id
            userId:this.$store.getters.userId,
            //存放选中的题目
            problemInfo: [],
           
            //存放考试信息
            exerciseInfo:{
                //考试编号
                exerciseno:0,
                //考试开始时间
                exercisestarttime:"",
                //考试结束时间
                exerciseendtime:"",
                //存储人员信息
                usersNo:[],
                //存放选择的题目
                problemNo:[]
            },

            //时间选择信息
            form: {
                date1: '',
                date2: '',        
                date3: '',
                date4: '',  
            }
        }
    },
    //函数
    methods: {
        //发布前数据处理
        DataManagement(){
            //处理时间信息
            this.exerciseInfo.exercisestarttime = this.form.date1 + " " + this.form.date2
            this.exerciseInfo.exerciseendtime = this.form.date3 + " " + this.form.date4
            let each = 0
            //处理题目信息
            for(each in this.problemInfo){
                this.exerciseInfo.problemNo.push(this.problemInfo[each].problemno)
            }
        },

        //发布测试
        publishExercise(){
            //判断是否选择了题目
            if(0 == this.problemInfo.length){
                this.$message.error("请选择题目")
                return
            }
            //判断日期是否完善
            else if(("" == this.form.date1) || ("" == this.form.date2) || ("" == this.form.date3) || ("" == this.form.date4)){
                this.$message.error("请完善起止时间信息")
                return
            }
            //处理数据
            this.DataManagement()
            //发送的数据
            let data = {
                    method: "post",
                    url:"/exercise/publishexercise",
                    data: this.exerciseInfo
                }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("发布测试成功");
                }
                else{
                    this.$message.error("发布测试失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("发布测试失败");
            })
        },

        //处理选中
        handleSelectionChange(val){
            this.problemInfo = val
        },

        //获取当前题目列表
        getProblemList(){
            //请求数据
            let data = {
                method: "get",
                url: "/exercise/getexperimentproblemlist",
                params: {"experimentNo": this.experimentNo}
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
                    this.$message.error("题目信息获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("题目信息获取失败");
            })
        },

        //获取教师班级信息
        getClassInfo(){
             //请求数据
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
                    this.classInfo = response.data.result
                    //获取人员数据
                    this.exerciseInfo.usersNo = this.classInfo.usersNo
                }
                else{
                    this.$message.error("班级信息获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级信息获取失败");
            })
        }
    },
    //初始化钩子函数
    mounted(){
        //获取当前项目号
        this.experimentNo = this.$route.query.experimentno
        //获取题目列表
        this.getProblemList()
        //获取班级信息
        this.getClassInfo()
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
.experiment-button{
    position:relative;
    left: -46%;
}
</style>