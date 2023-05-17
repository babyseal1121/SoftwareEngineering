<template>
    <div>
        <div>
            <el-breadcrumb separator="/">
            <el-breadcrumb-item>批改实验报告</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <br/>

        <div style="display: flex;">

            <div>
                <el-table
                :data="userTableData"
                style="margin-bottom: 20px;"
                row-key="userid"
                border
                default-expand-all>

                    <el-table-column label="学生信息">
                        <el-table-column
                        prop="username"
                        label="学生姓名"
                        width="150"
                        :filters="[
                        { text: '教师', value: '教师' },]"
                        filter-placement="bottom-end">
                            <template slot-scope="scope">
                                <el-tag
                                    :type="
                                        scope.row.username == '教师'
                                        ? 'success'
                                        : ''"
                                    disable-transitions
                                >{{ scope.row.username }}</el-tag>
                            </template>
                        </el-table-column>
                        
                        <el-table-column
                        prop="userid"
                        label="学生ID"
                        width="50">
                        </el-table-column>
                    </el-table-column>
                </el-table>
            </div>

            <div v-for="(coldata, index) in this.colTableDatas" :key="index">
                <el-table
                :data="coldata"
                style="margin-bottom: 20px;"
                row-key="experimentreportno"
                border
                default-expand-all>

                    <el-table-column :label="experimentInfo[index].experimentname">
                       
                        <el-table-column
                        prop="experimentno"
                        label="实验编号"
                        width="50">
                        </el-table-column>

                        <el-table-column
                        prop="experimentgrade"
                        label="成绩"
                        width="50">
                        </el-table-column>

                        <el-table-column
                        prop="correctstate"
                        label="批改状态"
                        width="100"
                        :filters="[
                        { text: '已批改', value: '已批改' },
                        { text: '未批改', value: '未批改' },
                        { text: '未完成', value: '未完成' },
                        ]"
                        filter-placement="bottom-end">
                            <template slot-scope="scope">
                                <el-tag
                                    :type="
                                        scope.row.correctstate == '未批改'
                                        ? 'warning'
                                        : scope.row.correctstate == '已批改'
                                        ? 'success'
                                        : scope.row.correctstate == '未完成'
                                        ? 'danger'
                                        : ''"
                                    disable-transitions
                                >{{ scope.row.correctstate }}</el-tag>
                            </template>
                        </el-table-column>

                        <el-table-column
                        label="批改报告"
                        width="100">
                            <template slot-scope="scope">
                                <el-button
                                size="mini"
                                @click="handleEdit(scope.row)"
                                :disabled="ifShowButton(scope.row.correctstate)">批改报告</el-button>
                                
                            </template>
                        </el-table-column>
                    </el-table-column>
                </el-table>
            </div>
            
        </div>
        
    </div>
</template>

<script>
import Contentfield from "@/components/AdminCom/Contentfield.vue";
import { Select } from 'element-ui';
export default {
    //组件
    components: { Contentfield,},
    //名称
    name: "CorrectReportUpdate",
    //数据
    data(){

        return{
            // 存放展示人员的信息
            userTableData: [],

            //存放展示的信息
            tableData: [],

            // 将展示的信息分列
            colTableDatas: [],

            //用于存放实验报告的数据
            experimentReportInfo: [],

            //用于存放实验信息
            experimentInfo: [],
       
            //存放用户Id
            userId: this.$store.getters.userId,

            //存放本班人员信息
            classUserTable:[],

            //用于搜素指定实验项目
            search: ''
        }


    },

     //函数
     methods:{

        // 判断是否高亮
        tableRowClassName({row, rowIndex}) {
            if(row.experimentreportno < 0 && row.correctstate == '')
                return "heiglight-row"

            return ''
        },

        // 判断是否应当禁用按钮
        ifShowButton(rowCorrectstate) {
            if(rowCorrectstate == "未完成")
                return true;
            else
                return false;
        },

        //处理获取书写报告
        handleEdit(row) {
            this.$router.push({path:"/correctreportdetail",query:row})
        },

        //获取报告列表
        getExperimentReportList() {
            //请求的数据
            let data = {
                method: "get",
                url:"/experiment/getclassexperimentreportlist",
                params: {"userId": this.userId}
            }
            //发起请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                    this.experimentReportInfo = response.data.result
                    this.getClassUserList();
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

        //获取本班项目列表
        getClassExperimentList(){
            //请求的信息
            let data = {
                method: "get",
                url: "/experiment/getallexperimentinclass",
                params: {"userId": this.userId}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                //如果请求成功
               if(200 == response.data.code){
                //获取数据
                   this.experimentInfo = response.data.result;
                   this.getExperimentReportList()
               }
               else{
                    this.$message.error("班级内实验数据获取失败");
               }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("班级内实验数据获取失败");
            })
        },

        //获取学生列表
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
                    this.ManipulationData()
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

        //处理实验报告的数据
        ManipulationData()
        {
            // 作为编号
            let keydata = -1;
            let eachUser = 0;
            // 复制一份人员信息
            var userInfo = []
            for(eachUser in this.classUserTable)
            {
                if(this.classUserTable[eachUser].userid != this.userId)
                    userInfo.push(this.classUserTable[eachUser])
            }
            this.userTableData = userInfo
            eachUser = 0;

            // 将信息加入表格           
            for(eachUser in userInfo)
            {
                let userTableData = {
                    userid: userInfo[eachUser]["userid"],
                    username: userInfo[eachUser]["username"],
                    experimentReports:[]
                }
                let each = 0;
                for(each in this.experimentInfo)
                {
                    // 查找对应的报告并加入
                    let eachOne = 0;
                    let getre = false
                    for(eachOne in this.experimentReportInfo)
                    {
                        if((this.experimentReportInfo[eachOne]["experimentno"] == this.experimentInfo[each]["experimentno"]) &&
                            (this.experimentReportInfo[eachOne]["userid"] == userInfo[eachUser]["userid"]))
                            {
                                // 修改显示
                                if(this.experimentReportInfo[eachOne]['correctstate'] == true)
                                    this.experimentReportInfo[eachOne]['correctstate'] = "已批改"
                                else
                                    this.experimentReportInfo[eachOne]['correctstate'] = "未批改"
                                userTableData.experimentReports.push(this.experimentReportInfo[eachOne])
                                getre = true
                                break;
                            }
                    }
                    // 如果没找到说明没交
                    if(false == getre)
                    {
                        var dataInfo = {
                            experimentno: this.experimentInfo[each]["experimentno"],
                            experimentname: this.experimentInfo[each]["experimentname"],
                            experimentgrade: 0,
                            correctstate: "未完成",
                            experimentreportno: keydata
                        }
                        keydata = keydata - 1;
                        userTableData.experimentReports.push(dataInfo)
                    }
                }
                // 将信息加入表格
                this.tableData.push(userTableData)
            }
            
            let each = 0
            // 将展示的信息分列
            for(each in this.experimentInfo)
            {
                let datas = []
                let eachOne = 0
                for(eachOne in this.tableData)
                {
                    datas.push(this.tableData[eachOne].experimentReports[each])
                }
                this.colTableDatas.push(datas)
            }
            console.log(this.colTableDatas)
        },

        //处理实验报告的数据
        // ManipulationData()
        // {
        //     let each = 0;
        //     let keyData = -1

        //     for (each in this.experimentInfo)
        //     {
        //         // 为了填充表格加入不含有的项
        //         this.experimentInfo[each]["userid"] = null;
        //         this.experimentInfo[each]["username"] = null;
        //         this.experimentInfo[each]["experimentgrade"] = null;
        //         this.experimentInfo[each]["children"] = []
        //         this.experimentInfo[each]["correctstate"] = '';
        //         // 用于当作key
        //         this.experimentInfo[each]["experimentreportno"] = keyData;
        //         keyData = keyData - 1;

        //         let eachOne = 0;
        //         let eachUser = 0;

        //         // 复制一份人员信息
        //         var userInfo = []
        //         for(eachUser in this.classUserTable)
        //         {
        //             if(this.classUserTable[eachUser].userid != this.userId)
        //                 userInfo.push(this.classUserTable[eachUser])
        //         }
        //         eachUser = 0;

        //         // 查找对应的报告并加入
        //         for(eachOne in this.experimentReportInfo)
        //         {
        //             if(this.experimentReportInfo[eachOne]["userid"] == this.userId)
        //                 continue
        //             if(this.experimentReportInfo[eachOne]["experimentno"] == this.experimentInfo[each]["experimentno"])
        //             {
        //                 eachUser = 0;
        //                 //标记已经完成
        //                 for(eachUser in userInfo)
        //                 {
        //                     if(userInfo[eachUser]["userid"] == this.experimentReportInfo[eachOne]["userid"])
        //                     {
                                
        //                         userInfo.splice(eachUser, 1)
        //                         break;
        //                     }
        //                 }
        //                 //修改数据便于展示
        //                 this.experimentReportInfo[eachOne]["experimentno"] = null;
        //                 this.experimentReportInfo[eachOne]["experimentname"] = null;
        //                 if(this.experimentReportInfo[eachOne]['correctstate'] == true)
        //                     this.experimentReportInfo[eachOne]['correctstate'] = "已批改"
        //                 else
        //                     this.experimentReportInfo[eachOne]['correctstate'] = "未批改"
        //                 this.experimentInfo[each]["children"].push(this.experimentReportInfo[eachOne])                       
        //             }
        //         }

        //         eachUser = 0;
        //         // 将未完成的人加入
        //         for(eachUser in userInfo)
        //         {
        //             var dataInfo = {
        //                 userid: userInfo[eachUser]["userid"],
        //                 username: userInfo[eachUser]["username"],
        //                 experimentgrade: 0,
        //                 correctstate: "未完成",
        //                 experimentreportno: keyData
        //             }
        //             keyData = keyData - 1
        //             this.experimentInfo[each]["children"].push(dataInfo)
        //         }

        //         this.tableData = this.experimentInfo;
        //     }
        // }
    },
    //加载时操作
    mounted(){
        //获取实验列表
        this.getClassExperimentList()
    },

}
</script>

<style>
.el-table .heiglight-row {
    background: #ddecff;
}
</style>