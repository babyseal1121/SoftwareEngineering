<template>
<Contentfield>
    <div>
        <el-breadcrumb separator="/">
        <el-breadcrumb-item>学习资料</el-breadcrumb-item>
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
            @click="handleEdit(scope.row)">查看相应资料</el-button>
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
    name: "ShowStudyFile",

    //数据
    data() {

        return{
            //存放实验项目信息
            tableData: [],
            //用于搜素指定实验项目
            search: '',
        }
    },

    //函数
    methods: {

        //处理查看相应资料
        handleEdit(row) {
            this.$router.push({path:'/experimentfile',query:row})
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
    mounted() {
        //获取实验项目信息
        this.getexperimentList()
    },
};
</script>
  
<style>

</style>