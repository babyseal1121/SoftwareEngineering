<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/problemmanagement'}">测试题目管理</el-breadcrumb-item>
            <el-breadcrumb-item>实验项目题目管理</el-breadcrumb-item>
        </el-breadcrumb>
        <br/>
        <el-table
            :data="tableData.filter(data => !search || data.problemdescription.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="495">

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
                placeholder="输入关键字搜索"/>
            </template>
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row)">删除</el-button>
            </template>
            </el-table-column>
        </el-table>

        <br/>
        <!--新增题目-->
        <el-button 
        type="primary" 
        plain 
        class="experiment-button"
        @click="comeToCreateProblem"
        >新增题目</el-button>

        <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
            <span>要删除这个题目吗</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleSureDelete">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
export default {
    //组件
    components: { Contentfield,},
    //名称
    name: "ExperimentProblem",
    //数据
    data(){

        return{
            //存放题目信息
            tableData: [],
            //用于搜素指定题目
            search: '',
            //当前实验项目号
            experimentNo: 0,
            //当前的实验项目的名称
            experimentName:"",
            //弹窗是否可见
            dialogVisible: false,
            //存储要删除的信息
            deleteInfo:{}
        }
    },
    //函数
    methods:{

        //创建新题目
        comeToCreateProblem(){
            let data = {
                "experimentNo": this.experimentNo,
                "experimentName": this.experimentName
            }
            this.$router.push({path:"/createproblem", query:data})
        },

        //处理删除题目
        handleDelete(row){
            this.deleteInfo = row
            this.dialogVisible = true
        },

        //删除题目
        handleSureDelete(){
            //结束弹窗
            this.dialogVisible = false
            //请求信息
            let data = {
                method: "post",
                url: "/exercise/deleteexerciseproblem",
                params: {"problemNo": this.deleteInfo.problemno}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("删除成功");
                }
                else{
                    this.$message.error("删除失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("删除失败");
            })
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
        //获取当前项目号
        this.experimentNo = this.$route.query.experimentno
        //获取当前项目名称
        this.experimentName = this.$route.query.experimentname
        //获取题目列表
        this.getProblemList()
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