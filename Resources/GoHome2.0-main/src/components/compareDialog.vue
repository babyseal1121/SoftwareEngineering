<template>
    <div id="compareDialog">
        <el-dialog
            title="对比" 
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose"
            class="dialogStyle"
            >
            <el-divider><i class="el-icon-warning-outline"></i></el-divider>
            <el-image src="https://z3.ax1x.com/2021/07/13/WEEqvn.png" fit="fill" style="position:absolute;
            left:0px; bottom:0px; border-radius:15px; opacity:20%"></el-image>
            <!--分割，左右各占一半-->
            <el-container>
                <el-main style="width: 50% !important;">
                    <el-carousel trigger="click" height="150px" indicator-position="none">
                        <el-carousel-item v-for="(stayPhoto,index) in stayPhoto1" :key="index">                            
                        <el-image :src="stayPhoto" fit="fill"></el-image>
                    </el-carousel-item></el-carousel> 
                    <div class="big-label" >{{stayName1}}</div>
                    <br>
                    <div class="small-label" >{{stayDescribe1}}</div>
                    <br>
                    <div class="price-label" >￥{{stayPrice1}}</div>
                    <el-rate class="rate-bar"
                        v-model="stayScore1.toFixed(1)"
                        disabled
                        show-score
                        text-color="#ff9900"
                        score-template="{value}">
                    </el-rate>    
                </el-main>
                <el-main style="width: 50% !important;">
                    <el-carousel trigger="click" height="150px" indicator-position="none">
                        <el-carousel-item v-for="(stayPhoto,index) in stayPhoto2" :key="index">                            
                        <el-image :src="stayPhoto" fit="fill"></el-image>
                    </el-carousel-item></el-carousel>
                    <div class="big-label" >{{stayName2}}</div>
                    <div class="small-label" >{{stayDescribe2}}</div>

                    <div class="price-label" >￥{{stayPrice2}}</div>
                    <el-rate class="rate-bar"
                        v-model="stayScore2.toFixed(2)"
                        disabled
                        show-score
                        text-color="#ff9900"
                        score-template="{value}">
                    </el-rate>    

                </el-main>
            </el-container>

            <span slot="footer" class="dialog-footer">
                <div class="nameStyle" 
                style="text-align: center;display: block"
                >通过对比，挑选出你更心意的一家吧！</div>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default{
    props:{
        "dialogVisible":{
            Type:Boolean,
            default:false
        },
        "stayPhoto1":{
            Type:Array,
            default:["https://z3.ax1x.com/2021/07/13/WE1Vl8.png"]
        },
        "stayPhoto2":{
            Type:Array,
            default:["https://z3.ax1x.com/2021/07/13/WE1Vl8.png"]
        },
        "stayScore1":{
            type:Number
        },
        "stayScore2":{
            type:Number
        },
        "stayName1":{
            type:String,
            default:'待加载'
        },
        "stayName2":{
            type:String,
            default:'待加载'
        },
        "stayDescribe1":{
            type:String,
            default:'待加载'
        },
        "stayDescribe2":{
            type:String,
            default:'待加载'
        },
        "stayPrice1":{
            type:Number
        },
        "stayPrice2":{
            type:Number
        },
    },
    methods:{
        handleClose(){
            this.$emit('closeCompareDialog',false);
        }
    }
}
</script>


<style scoped>
@import url("../assets/css/font.css");
.dialogStyle >>> .el-dialog{
    box-shadow: rgba(6, 24, 44, 0.4) 0px 0px 0px 2px, rgba(6, 24, 44, 0.65) 0px 4px 6px -1px,
        rgba(255, 255, 255, 0.08) 0px 1px 0px inset !important;
    border-radius: 10px !important;
    max-height:700px;
}
.dialogStyle >>>.el-dialog__title{
    font-size:22px;
    font-family: "FZHEIBJW";
}
.dialogStyle >>>.el-dialog__body{
    padding: 10px 10px 10px 10px !important;

}
.dialogStyle >>>.el-dialog__footer{
    text-align: center;
}
.el-carousel__container{
    width: 100px;
    height: 100px;
    border-radius: 15px;
}
.nameStyle{
    font-size: 18px;
    padding:3px 3px;
    font-family:"FZFWZhu";
    margin:1px;
    word-wrap: break-word;
    text-align: center;
    overflow : hidden;
}
.infoStyle{
    font-size: 10px;
    color: #999;
    padding:3px 3px;
    font-family:"Lato-Bold","FZHeiBJW";
    margin:1px;
    word-wrap: break-word;
    text-align: left;
    overflow : hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
    width: 320px;
}


/* 小标签 */
.big-label{
    font-size: 20px;
    color: rgb(11, 10, 10);
    text-align: center;
    float:left;
    font-family:"FZHeiBJW","Lato-Bold";
    margin:1px;
    word-wrap: break-word;
    height: 45px;
    width: 120px;
    overflow : hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
}
/* 房源名字 */
.stay-title{
    font-size:18px;
    padding: 1px 6px;
    font-weight: bold;
    text-align: left;
    display:block;
    float:left;
    font-family:"PingFang SC","Lato-Bold";
    flex: 1;  
    height: auto;
    width: 400px;
    overflow : hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}
.small-label{
    font-size: 10px;
    color: #999;
    display:flex;
    padding:6px 6px;
    float:left;
    font-family:"FZHeiBJW","Lato-Bold";
    margin:1px;
    word-wrap: break-word;
    word-break: normal;
}


/* 标签 */
.el-tag{
    float:left;
    white-space: pre-line;
    word-break: break-all;
    margin-right:10px;
    font-family: "FZHeiBJW";
}



/* 标签列表 */
.label-list{
    
    padding:1px 6px;
    margin:1px 6px;
}

/* 评分栏 */
.rate-bar{
    position:relative;
    float:left;
    padding:1px 6px;
    margin:3px;
    bottom: 0px;
}
.el-rate{
    font-family: 'Lato-Bold';
}
/* 评分栏与评论数的分隔符 */
.rate-divide{
    margin:6px;
    float:left;
    color:black
}


.price-label{
    font-size:20px;
    padding: 1px 6px;
    font-weight: bold;
    text-align: center;
    display:block;
    float:left;
    margin-right:10px;
    font-family:"Lato-Bold";
    flex: 1;  

}

    
</style>
    