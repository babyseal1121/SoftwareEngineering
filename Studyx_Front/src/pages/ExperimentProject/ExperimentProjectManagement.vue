<template>
    <Contentfield>
        <div>
          <el-breadcrumb separator="/">
            <el-breadcrumb-item>实验项目管理</el-breadcrumb-item>
            <!-- <el-breadcrumb-item :to="{ path: '/experimentdetail' }">添加实验项目</el-breadcrumb-item> -->
          </el-breadcrumb>
        </div>
       
        <br/>
        <el-table
            :data="tableData.filter(data => !search || data.experimentname.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="380">
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
                v-model="search"
                size="mini"
                placeholder="输入关键字搜索"/>
            </template>
            <template slot-scope="scope">
                <el-button
                size="mini"
                @click="handleEdit(scope.row)">详情</el-button>

                <el-button
                size="mini"
                type="danger"
                @click="handleClickDelete(scope.row)">删除</el-button>

            </template>
            </el-table-column>
        </el-table>
        <br/>
        <!--新增实验项目-->
        <el-button 
        type="primary" 
        plain 
        class="experiment-button"
        @click="comeToCreateExperimentProject"
        >新增实验项目</el-button>

        <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
            <span>要删除这个实验吗</span>
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
    name:"ExperimentProjectManagement",
    //数据
    data(){
        return{
            //存放实验项目信息
            tableData: [],
            //用于搜素指定实验项目
            search: '',
            //弹窗是否可见
            dialogVisible: false,
            //存储要删除的信息
            deleteInfo:{}
        }
    },
    //函数
    methods:{

        //处理获取详情
        handleEdit(row) {
            row["path"] = "/experimentprojectmanagement"
            this.$router.push({path:'/experimentdetail',query:row})
        },

        //处理点击删除
        handleClickDelete(row){
            this.dialogVisible = true;
            this.deleteInfo = row;
        },

        //处理删除
        handleDelete() {
            this.dialogVisible = false
            this.deleteExperimentProject(this.deleteInfo.experimentno)
        },

        //根据编号删除项目
        deleteExperimentProject(experimentNo){
            //请求信息
            let data = {
                method: "post",
                url: "/experiment/deleteaexperiment",
                params: {"experimentNo": experimentNo}
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

        //获取列表
        getExperimentList() {
            //发起请求
            this.$axios.get('/experiment/getexperimentlist')
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                    this.tableData = response.data.result
               }
               else{
                    this.$message.error("数据获取失败");
               }
            })
            .catch(failResponse => {
                this.$message.error("数据获取失败");
            })
        },

        //前往创建实验报告页面
        comeToCreateExperimentProject(){
            this.$router.push({path:"/createexperimentproject"})
        }

    },
    //加载时操作
    mounted(){
        //获取列表
        this.getExperimentList()
    },
}
</script>

<style scoped>
.experiment-button{
    position:absolute;
    left: 18.2%;
}
</style>