<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
              <el-breadcrumb-item>班级人员管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <br/>

        <h1 style="text-align:left">班级内人员列表</h1>
        <el-table
        :data="classUserTable.filter(data => !classSearch || data.username.toLowerCase().includes(classSearch.toLowerCase()))"
        style="width: 100%"
        height="400">

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
                    v-model="classSearch"
                    size="mini"
                    placeholder="输入学生名称搜索"/>
                </template>

                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    type="danger"
                    @click="handleClickDelete(scope.row)">删除</el-button>
                </template>

            </el-table-column>
        </el-table>

        <h1 style="text-align:left">未加入班级人员</h1>
        <el-table
        :data="allUserTable.filter(data => !allSearch || data.username.toLowerCase().includes(allSearch.toLowerCase()))"
        style="width: 100%"
        height="400"
        highlight-current-row
        @current-change="handleSelectUserChange">

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
                    v-model="allSearch"
                    size="mini"
                    placeholder="输入学生名称搜索"/>
                </template>
            </el-table-column>
        </el-table>

        <br/>
        <el-button 
        type="primary" 
        plain 
        class="experiment-button"
        @click="addClassUser"
        >加入人员</el-button>

        <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
            <span>要删除这个人员吗</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleDelete">确 定</el-button>
            </span>
        </el-dialog>
    </Contentfield>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
export default {
    //组件
    components: { Contentfield,},
    //名称
    name: "ClassStudent",
    //数据
    data(){

        return{
            //用户Id
            userId:this.$store.getters.userId,
            //存储班级
            experimentClassNo:0,
            //班级内人员列表
            classUserTable:[],
            //班级内查找人员
            classSearch:"",
            //所有未加入班级人员列表
            allUserTable:[],
            //搜索所有未加入班级人员
            allSearch:"",
            //存储要加入的人员
            addUser:{},
            //是否显示弹窗
            dialogVisible:false,
            //存储要删除的数据
            deletInfo:{}
        }
    },
    //函数
    methods:{

        //处理加入人员
        addClassUser(){
            //判断是否选择人员
            if({} == this.addUser){
                this.$message.error("请选择要加入的人员")
                return
            }
            //发送的数据
            let data = {
                method: "post",
                url:"/class/addclassmember",
                params: {"userId": this.addUser.userid, "experimentClassNo": this.experimentClassNo}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("加入人员成功");
                }
                else{
                    this.$message.error("加入人员失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("加入人员失败");
            })
        },

        //处理删除
        handleDelete(){
            this.dialogVisible = false
             //发送的数据
             let data = {
                method: "post",
                url:"/class/deleteclassmember",
                params: {"userId": this.deletInfo.userid, "experimentClassNo": this.experimentClassNo}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("删除人员成功");
                }
                else{
                    this.$message.error("删除人员失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("删除人员失败");
            })
        },

        //处理点击删除
        handleClickDelete(row){
            if((row.level == "教师") || (row.level == "责任教师")){
                this.$message.error("不可删除教师")
                return
            }
            this.dialogVisible = true
            this.deletInfo = row
        },

        //处理选择加入人员
        handleSelectUserChange(val){
            this.addUser = val
        },

        //获取当前班级
        getExperimentClass(){
            //请求的信息
            let data = {
                method: "get",
                url: "/class/findexperimentclass",
                params: {"userId": this.userId}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.experimentClassNo = response.data.result;
               }
               else{
                    this.$message.error("班级获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级获取失败");
            })
        },

        //获取本班人员列表
        getClassUserList(){
            //请求的信息
            let data = {
                method: "get",
                url: "/experiment/getallstudentinclass",
                params: {"userId": this.userId}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.classUserTable = response.data.result;
               }
               else{
                    this.$message.error("班级内人员信息获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级内人员信息获取失败");
            })
        },

        //获取所有未加入班级的人员
        getAllUserList(){
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
                    this.allUserTable = response.data.result
                }
                else{
                    this.$message.error("所有实验项目数据获取失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("所有实验项目数据获取失败");
            })
        },
    },
    //初始化钩子函数
    mounted(){
        //获取当前班级
        this.getExperimentClass()
        //获取当前班级人员列表
        this.getClassUserList()
        //获取未加入班级的人员的列表
        this.getAllUserList()
    }
}
</script>

<style scoped>
.experiment-button{
    position:relative;
    left: -45.6%;
}
</style>