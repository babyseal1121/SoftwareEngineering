<template>
    <Contentfield>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/managefile'}">返回</el-breadcrumb-item>
            <el-breadcrumb-item>实验项目对应文件</el-breadcrumb-item>
        </el-breadcrumb>
        <br/>

        <el-table
            :data="tableData.filter(data => !search || data.materialname.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            height="330">

            <el-table-column
            label="文件名称"
            prop="materialname">
            </el-table-column>

            <el-table-column
            label="文件编号"
            prop="materialno">
            </el-table-column>

            <el-table-column
            label="上传时间"
            prop="materialuploadtime">
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
                type="warning"
                @click="handleMove(scope.row)">移动</el-button>

                <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row)">删除</el-button>
            </template>
            </el-table-column>
        </el-table>

        <div class="move-file-select">
            <h1>请选择要移动到的位置</h1>
            <el-select v-model="newPath" placeholder="请选择要移动到的位置">
                <el-option
                v-for="item in experimentList"
                :key="item.value"
                :label="item.label"
                :value="item.label">
                </el-option>
            </el-select>
        </div>

        <el-dialog
        title="提示"
        :visible.sync="fileDialogVisible"
        width="30%">
            <span>要删除这个文件吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="fileDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doDelete">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog
        title="提示"
        :visible.sync="moveDialogVisible"
        width="30%">
            <span>要移动这个文件到"{{this.newPath}}"吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="moveDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doMove">确 定</el-button>
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
    name: "ManageExperimentFile",

    //数据
    data(){

        return{
            //存放文件信息
            tableData: [],
            //用于搜素指定文件
            search: '',
            //存放新路径
            newPath:"",
            //存放当前路径
            nowPath:"",
            //是否删除弹窗
            fileDialogVisible:false,
            //要删除的信息
            deleteInfo:{},
            //是否进行移动
            moveDialogVisible:false,
            //要移动的信息
            moveInfo:{},
            //存放当前实验列表便于移动
            experimentList:[]
        }
    },

    //函数
    methods: {
        
        //进行删除
        doDelete(){
            //结束弹窗
            this.fileDialogVisible = false
            //请求信息
            let data = {
                method: "post",
                url: "/studymaterial/deletestudymaterial",
                params: {"materialNo": this.deleteInfo.materialno}
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){

                    this.$message.success("文件删除成功");
                }
                else{
                    this.$message.error("文件删除失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("文件删除失败");
            })
        },

        //进行移动
        doMove(){
            //结束弹窗
            this.moveDialogVisible = false
             //请求信息
             let data = {
                method: "post",
                url: "/studymaterial/changestudymaterialpath",
                params: {
                    "materialNo": this.moveInfo.materialno,
                    "materialPath": "/" + this.newPath + "/"
                }
            }
            //发送请求
            this.$axios.request(data)
            .then(response => {
                    //如果请求成功
                if(200 == response.data.code){

                    this.$message.success("文件移动成功");
                }
                else{
                    this.$message.error("文件移动失败");
                }
            })
            .catch(failResponse => {
                console.log(failResponse)
                this.$message.error("文件移动失败");
            })
        },

        //处理删除
        handleDelete(row){
            this.deleteInfo = row
            this.fileDialogVisible = true
        },

        //处理移动
        handleMove(row){
            this.moveInfo = row
            this.moveDialogVisible = true
        },

        //获取文件列表
        getStudyMaterialList(){
            //请求信息
            let data = {
                method: "get",
                url: "/studymaterial/getstudymateriallist",
                params: {"experimentName": this.nowPath}
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
        },
    },

    //初始化钩子函数
    mounted() {
        //获取当前路径(/实验项目名/)
        this.nowPath = "/" + this.$route.query.experimentname + "/"
        //获取默认移动位置防止文件丢失
        this.newPath = this.$route.query.experimentname
        //获取实验列表
        this.experimentList = this.$route.query.list
        //获取文件列表
        this.getStudyMaterialList()
    },
}
</script>

<style scoped>

.move-file-select{
    position:relative;
    left: -40.5%;
}
</style>