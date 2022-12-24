<template>
    <Contentfield>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item>我的测试</el-breadcrumb-item>
        </el-breadcrumb>

        <br/>
        <el-table
            :data="tableData.filter(data => !search || data.exercisestarttime.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="380">
            <el-table-column
            label="测试编号"
            prop="exerciseno">
            </el-table-column>

            <el-table-column
            label="测试开始时间"
            prop="exercisestarttime">
            </el-table-column>

            <el-table-column
            label="测试结束时间"
            prop="exerciseendtime">
            </el-table-column>

            <el-table-column
            align="right">
            <template slot="header">
                <el-input
                v-model="search"
                size="mini"
                placeholder="输入起始日期搜索"/>
            </template>
            <template slot-scope="scope">
                <el-button
                size="mini"
                @click="handleEdit(scope.row)">查看详情</el-button>

                <el-button
                size="mini"
                type="success"
                @click="comeToExercise(scope.row)">进入测试</el-button>
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
    name: "MyExercise",
    //数据
    data(){

        return{
            //存放测试信息
            tableData: [],
            //用于搜素指定时间的测试
            search: '',
            //存放用户的Id
            userId: 5
        }
    },
    //函数
    methods: {
        
        //进入测试
        comeToExercise(row){
            if((Date.parse(row.exerciseendtime)) < new Date()){
                this.$message.error("测试已结束")
                return
            }
            if(new Date() < (Date.parse(row.exercisestarttime))){
                this.$message.error("测试尚未开始")
                return
            }
            //加入用户Id
            row["userId"] = this.userId
            this.$router.push({path:'/comeexercise',query:row})
        },

        //查看考试详情
        handleEdit(row){
            if((Date.parse(row.exerciseendtime)) > new Date()){
                this.$message.error("测试尚未结束")
                return
            }
            this.$router.push({path:'/exercisedetail',query:row})
        },

        //获取测试的信息
        getExerciseList(){
            //请求信息
            let data = {
                method: "get",
                url: "/exercise/getexerciselist",
                params: {"userId": this.userId}
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
        //请求测试信息
        this.getExerciseList()
    }
}
</script>

<style>

</style>