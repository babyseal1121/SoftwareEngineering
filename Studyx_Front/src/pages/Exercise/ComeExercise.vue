<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/myexercise'}">我的测试</el-breadcrumb-item>
            <el-breadcrumb-item>测试</el-breadcrumb-item>
        </el-breadcrumb>
        <br/>

        <div v-for="each in this.tableData" :key="tableData.indexOf(each)">
            <el-card shadow="hover">
                <h3 class="experiment-text">题目描述：{{each.problemdescription}}</h3>
                <br/>
                <h3 class="experiment-text">选项A: {{each.problemoptiona}}</h3>
                <h3 class="experiment-text">选项B: {{each.problemoptionb}}</h3>
                <h3 class="experiment-text">选项C: {{each.problemoptionc}}</h3>
                <h3 class="experiment-text">选项D: {{each.problemoptiond}}</h3>
                <br/>
                <el-select 
                v-model="problemAnswer[tableData.indexOf(each)].Ans" 
                placeholder="请选择题目答案"
                class="problem-select">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                </el-select>
            </el-card>
            <br/>
        </div>

        <el-button 
        type="warning"
        plain 
        @click="SubmitExercise"
        >提交测试</el-button>    

        <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
            <span>要提交测试吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleSubmit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    //名称
    name: "ComeExercise",
    //数据
    data(){

        return{
            //存放题目信息
            tableData: [],
            //当前测试编号
            exerciseNo: 0,
            //用户Id
            userId:0,
            //用于存放考试结果
            exerciseResult:{
                userid:0,
                exerciseno:0,
                exerciseusetime:0,
                exercisegrade:0
            },
            //记录是否已经提交
            ifSubmit:false,
            //记录开始测试的时间
            startTime:0,
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
            //存放答案
            problemAnswer:[],
            //弹窗是否可见
            dialogVisible: false,
            //记录结束时间
            endTime:""
        }
    },
    //函数
    methods:{

        //选择提交测试
        SubmitExercise(){
            //检查是否超时
            if((new Date) > (Date.parse(this.endTime))){
                this.$message.error("测试已经结束")
                return
            }
            //检查是否都完成
            let each = 0
            for(each in this.problemAnswer){
                if(this.problemAnswer[each].Ans == ""){
                    let ansno = parseInt(each) + 1
                    let errmessage = "第" + ansno + "题尚未完成"
                    this.$message.error(errmessage)
                    return
                }
            }
            //检查是否已经提交过了
            if(this.ifSubmit){
                this.$message.error("请勿重复提交")
                return
            }
            this.dialogVisible = true;
        },

        //判卷
        checkAns(){
            //清空记录
            let each = 0
            this.exerciseResult.exercisegrade = 0
            //逐个比对
            for(each in this.problemAnswer){
                //如果答案正确
                if(this.problemAnswer[each].Ans == this.tableData[each].problemanswer){
                    //加一分
                    this.exerciseResult.exercisegrade += 1
                }
            }
        },

        //提交测试
        handleSubmit(){
            this.dialogVisible = false;
            //处理所用时间
            let useTime = parseInt(((new Date()) - this.startTime) / 1000)
            this.exerciseResult.exerciseusetime = useTime
            //检查答案获取评分
            this.checkAns()
            //加入人员Id
            this.exerciseResult.userid = this.userId
            //加入考试编号
            this.exerciseResult.exerciseno = this.exerciseNo
            //请求数据
            let data = {
                method: "post",
                url: "/exercise/submitexercise",
                data:this.exerciseResult
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    let sumessage = "提交成功，用时" + this.exerciseResult.exerciseusetime + "秒"
                    this.$message.success(sumessage)
                    this.ifSubmit = true
                }
                else{
                    this.$message.error( response.data.result);
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("提交失败");
            })
        },

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
                    this.saveAnswer(this.tableData)
                    //加入存放答案的地方
                    this.$message.success("测试开始，开始计时")
                    this.startTime = new Date()
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

        //暂时存放选择的答案
        saveAnswer(tableData){
            let each = 0
            for(each in tableData){
                this.problemAnswer.push({"Ans":""})
            }
        },
    },
    //初始化钩子函数
    mounted(){
        //实验编号
        this.exerciseNo = this.$route.query.exerciseno
        //获取用户Id
        this.userId = this.$route.query.userId
        //获取结束时间
        this.endTime = this.$route.query.exerciseendtime
        //获取题目列表
        this.getProblemList()
    }
}
</script>

<style scoped>
.experiment-text{
    font-family:"PingFang SC";
    font-size:15px;
    color:black;
    text-align:left;
    width: 95%;
}
</style>