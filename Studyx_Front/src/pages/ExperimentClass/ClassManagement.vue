<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
              <el-breadcrumb-item>班级管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <br/>
        <el-table
            :data="tableData.filter(data => !search || data.experimentclassname.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="380">

            <el-table-column
            label="班级编号"
            prop="experimentclassno">
            </el-table-column>

            <el-table-column
            label="班级名称"
            prop="experimentclassname">
            </el-table-column>

            <el-table-column
            label="开课时间"
            prop="classstarttime">
            </el-table-column>

            <el-table-column
            label="教师Id"
            prop="teacheridnumber">
            </el-table-column>

            <el-table-column
            align="right"
            width="250">
            <template slot="header">
                <el-input
                v-model="search"
                size="mini"
                placeholder="输入班级名称搜索"/>
            </template>
            <template slot-scope="scope">

                <el-button
                size="mini"
                type="danger"
                @click="handleClickDelete(scope.row)">删除</el-button>

                <el-button
                size="mini"
                type="danger"
                @click="handleClickPigeonhole(scope.row)">归档</el-button>

                <el-button
                size="mini"
                type="success"
                @click="handleClickDownloadZip(scope.row)">下载文档</el-button>

            </template>
            </el-table-column>
        </el-table>

        <br/>
        <!--新增班级-->
        <el-button 
        type="primary" 
        plain 
        class="experiment-button"
        @click="comeToCreateClass"
        >新建班级</el-button>

        <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
            <span>要删除这个班级吗</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleDelete">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog
        title="提示"
        :visible.sync="pigeonholeDialogVisible"
        width="30%">
            <span>要将这个班级归档吗</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="pigeonholeDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handlePigeonhole">确 定</el-button>
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
    name: "ClassManagement",
    //数据
    data(){

        return{
            //存放班级的信息
            tableData: [],
            //用于搜素指定班级
            search: '',
            //删除班级弹窗是否可见
            dialogVisible: false,
            //存储要删除的信息
            deleteInfo:{},
            //归档班级弹窗是否可见
            pigeonholeDialogVisible:false,
            //存储要归档的信息
            pigeonholeInfo:{}
        }
    },
    //函数
    methods:{

        //处理点击下载文件
        handleClickDownloadZip(row){
            let link = document.createElement('a');
            link.style.display = 'none';
            link.href = "http://localhost:8443/api/experiment/getexperimentinclasszip?experimentClassNo=" + row.experimentclassno;
            link.download = (row.experimentclassname + ".zip");
            link.click();
        },

        //处理点击归档
        handleClickPigeonhole(row){
            this.pigeonholeDialogVisible = true;
            this.pigeonholeInfo = row;
        },

        //前往新建班级
        comeToCreateClass(){
            this.$router.push({path:"/createclass"})
        },

        //处理点击删除
        handleClickDelete(row){
            this.dialogVisible = true;
            this.deleteInfo = row;
        },

        //处理归档
        handlePigeonhole(){
            this.pigeonholeDialogVisible = false;
            //发送的数据
            let data = {
                method: "post",
                url:"/class/pigeonholeexperimentclass",
                params: {"experimentClassNo": this.pigeonholeInfo.experimentclassno}
            }
             //发送请求
             this.$axios.request(data)
            .then(response => {
                //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    let backInfo = response.data.result
                    if(backInfo == "班级归档成功")
                        this.$message.success(backInfo);
                    else
                        this.$message.warning(backInfo);
                }
                else{
                    this.$message.error("班级归档失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级归档失败");
            })
        },

        //处理删除
        handleDelete(){
            this.dialogVisible = false;
            //发送的数据
            let data = {
                method: "post",
                url:"/class/deleteexperimentclass",
                params: {"experimentClassNo": this.deleteInfo.experimentclassno}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){
                    //获取数据
                    this.$message.success("删除班级成功");
                }
                else{
                    this.$message.error("删除班级失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("删除班级失败");
            })
        },

        //获取班级列表
        getClassList(){
            //请求信息
            let data = {
                method: "get",
                url: "/class/getexperimentclasslist",
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
                    this.$message.error("获取班级信息失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("获取班级信息失败");
            })
        },
    },
    //初始化钩子函数
    mounted(){
        //获取班级编号
        this.getClassList()
    }
}
</script>

<style>
.experiment-button{
    position:relative;
    left: -45.6%;
}
</style>