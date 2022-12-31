<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
            <el-breadcrumb-item>我的实验报告</el-breadcrumb-item>
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
            label="完成人ID"
            prop="userid">
            </el-table-column>
            <el-table-column
            label="完成人"
            prop="username">
            </el-table-column>
            <el-table-column
            label="报告编号"
            prop="experimentreportno">
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
                @click="handleEdit(scope.row)">查看详情</el-button>
            </template>
            </el-table-column>
        </el-table>
    </Contentfield>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
export default {
    //组件
    components: { Contentfield,},
    //名称
    name: "MyReport",
    //数据
    data(){

        return{
            //用户Id
            userId: this.$store.getters.userId,
            //存放实验项目信息
            tableData: [],
            //用于搜素指定实验项目
            search: '',
        }
    },
    //函数
    methods:{

        //处理获取详情
        handleEdit(row) {
            this.$router.push({path:"/reportdetail",query:row})
        },

        getReportDetail(){
            //请求信息
            let data = {
                method: "get",
                url:"/experiment/getexperimentreportlist",
                params: {"userId": this.userId}
            }
            //发送请求
            this.$axios.request(data)
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
        }
    },
    //起始时调用
    mounted(){
        this.getReportDetail()
    }
}
</script>

<style scoped>

</style>