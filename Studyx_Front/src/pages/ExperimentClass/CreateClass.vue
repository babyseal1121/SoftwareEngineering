<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/classmanagement'}">返回</el-breadcrumb-item>
            <el-breadcrumb-item>创建新的班级</el-breadcrumb-item>
        </el-breadcrumb>
        <br/>

        <h1 class="experiment-title">课程开始时间</h1>
        <el-input
        type="textarea"
        :rows="3"
        placeholder="描述课程开始时间，推荐格式为：年份 + 学期 + 第几周 + 上课时间(小于50字)"
        v-model="classStartTime">
        </el-input>

        <h1 class="experiment-title">课程名称</h1>
        <el-input
        type="textarea"
        :rows="3"
        placeholder="描述课程名称，推荐格式为：学年 + 上/下 + 课程名 + 教师名 + 附加信息(小于50字)"
        v-model="className">
        </el-input>

        <h1 class="experiment-title">选择课程的教师</h1>
        <el-table
        :data="teacherTableData.filter(data => !searchTeacher || data.username.toLowerCase().includes(searchTeacher.toLowerCase()))"
        style="width: 100%"
        height="400"
        highlight-current-row
        @current-change="handleSeletTeacher">

            <el-table-column
            label="教师ID"
            prop="userid">
            </el-table-column>

            <el-table-column
            label="教师名称"
            prop="username">
            </el-table-column>

            <el-table-column
            prop="level"
            label="教师职务"
            width="150"
            :filters="[
            { text: '管理员', value: '管理员' },
            { text: '责任教师', value: '责任教师' }]"
            filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                        :type="
                        scope.row.level == '责任教师'
                        ? 'warning'
                        : scope.row.level == '教师'
                        ? 'primary'
                        : 'primary'"
                        disable-transitions
                    >{{ scope.row.level }}</el-tag>
                </template>
            </el-table-column>

            <el-table-column
            label="教师状态"
            prop="status">
            </el-table-column>

            <el-table-column
            align="right">
                <template slot="header">
                    <el-input
                    v-model="searchTeacher"
                    size="mini"
                    placeholder="输入项目名称搜索"/>
                </template>
            </el-table-column>
        </el-table>

        <h1 class="experiment-title">选择课程的学生</h1>
        <el-table
        :data="studentTableData.filter(data => !searchStudent || data.username.toLowerCase().includes(searchStudent.toLowerCase()))"
        style="width: 100%"
        height="400"
        @selection-change="handleSelectStudent">

        
            <el-table-column
            type="selection"
            width="55">
            </el-table-column>

            <el-table-column
            label="学生ID"
            prop="userid">
            </el-table-column>

            <el-table-column
            label="学生名称"
            prop="username">
            </el-table-column>

            <el-table-column
            prop="level"
            label="学生职务"
            width="150"
            :filters="[
            { text: '助教', value: '助教' },
            { text: '学生', value: '学生' },]"
            filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                        :type="
                        scope.row.level == '助教'
                            ? 'warning'
                            : scope.row.level == '学生'
                            ? 'success'
                            : 'primary'"
                        disable-transitions
                    >{{ scope.row.level }}</el-tag>
                </template>
            </el-table-column>

            <el-table-column
            label="学生状态"
            prop="status">
            </el-table-column>


            <el-table-column
            align="right">
                <template slot="header">
                    <el-input
                    v-model="searchStudent"
                    size="mini"
                    placeholder="输入学生名称搜索"/>
                </template>
            </el-table-column>
        </el-table>

        <h1 class="experiment-title">选择课程的项目</h1>
        <el-table
            :data="experimentTableData.filter(data => !searchExperiment || data.experimentname.toLowerCase().includes(searchExperiment.toLowerCase()))"
            style="width: 100%"
            height="400"
            @selection-change="handleSelectExperimentChange">

            
            <el-table-column
            type="selection"
            width="55">
            </el-table-column>

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
                v-model="searchExperiment"
                size="mini"
                placeholder="输入项目名称搜索"/>
            </template>
            </el-table-column>
        </el-table>

        <br/>
        <br/>
        <el-button 
        type="primary" 
        plain 
        class="experiment-button"
        @click="createClass"
        >创建新的班级</el-button>    

    </div>
</template>

<script>
export default {
    //名称
    name: "CreateClass",
    //数据
    data(){

        return{
            //课程的信息
            classInfo:{
                experimentclassno:0,
                //教师工号
                teacheridnumber:0,
                //开班时间
                classstarttime:"",
                //课程名称
                experimentclassname:"",
                //参课的学生
                usersNo:[],
                //课程内的实验项目
                experimentsNo:[]
            },

            //用于搜素指定实验项目
            searchExperiment: '',
            //记录实验项目的信息
            experimentTableData:[],
            //记录选择的实验项目
            experimentChoiceList:[],

            //用于搜素指定名称教师
            searchTeacher: '',
            //记录教师的信息
            teacherTableData:[],
            //记录选择的教师
            teacherChoice:{},

            //用于搜素指定名称学生
            searchStudent: '',
            //记录学生的信息
            studentTableData:[],
            //记录选择的学生
            studentChoice:[],

            //记录课程开始时间
            classStartTime:"",
            //记录课程名称
            className:""
        }
    },
    //函数
    methods:{
        
        //处理数据
        dataManagement(){
            //处理开课时间
            if("" == this.classStartTime){
                this.$message.error("请输入开课时间")
                return false
            }
            this.classInfo.classstarttime = this.classStartTime
            //处理课程名称
            if("" == this.className){
                this.$message.error("请输入课程名称")
                return false
            }
            this.classInfo.experimentclassname = this.className
            //处理教师信息
            if({} == this.teacherChoice){
                this.$message.error("请选择任课教师")
                return false
            }
            this.classInfo.teacheridnumber = this.teacherChoice.userid
            //处理学生信息
            if(0 == this.studentChoice.length){
                this.$message.error("请选择参加课程的学生")
                return false
            }
            let each = 0
            for(each in this.studentChoice){
                this.classInfo.usersNo.push(this.studentChoice[each].userid)
            }
            //老师也要加进去
            this.classInfo.usersNo.push(this.teacherChoice.userid)
            //处理项目信息
            if(0 == this.experimentChoiceList.length){
                this.$message.error("请选择课程中的项目")
                return false
            }
            each = 0
            for(each in this.experimentChoiceList){
                this.classInfo.experimentsNo.push(this.experimentChoiceList[each].experimentno)
            }

            return true
        },

        //创建新的班级
        createClass(){
            //处理数据，判断数据是否完整
            if(!this.dataManagement()){
                return
            }
            //发送的数据
            let data = {
                method: "post",
                url:"/class/createexperimentclass",
                data: this.classInfo
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("创建新班级成功");
                }
                else{
                    this.$message.error("创建新班级失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("创建新班级失败");
            })
        },

        //处理获取实验项目
        handleSelectExperimentChange(val){
            this.experimentChoiceList = val
        },

        //处理获取教师
        handleSeletTeacher(val){
            this.teacherChoice = val
        },

        //处理获取学生
        handleSelectStudent(val){
            this.studentChoice = val
            console.log(this.studentChoice)
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
                    this.experimentTableData = response.data.result
                }
                else{
                    this.$message.error("实验项目数据获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("实验项目数据获取失败");
            })
        },

        //获取未加入班级的教师
        getTeacherList(){
            //请求信息
            let data = {
                method: "get",
                url: "/experiment/teachernoinclass",
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.teacherTableData = response.data.result
                }
                else{
                    this.$message.error("教师数据获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("教师数据获取失败");
            })
        },

         //获取未加入班级的学生
         getStudentList(){
            //请求信息
            let data = {
                method: "get",
                url: "/experiment/studentnoinclass",
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.studentTableData = response.data.result
                }
                else{
                    this.$message.error("学生数据获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("学生数据获取失败");
            })
        }
    },
    //初始化钩子函数
    mounted(){
        //获取实验项目的数据
        this.getexperimentList()
        //获取未加入班级的教师的信息
        this.getTeacherList()
        //获取未加入班级的学生的信息
        this.getStudentList()
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
.experiment-button{
    position:relative;
    left: -44.8%;
}
</style>