<template>
  <div class="container">
    <el-container style="height: 100%">
      <el-header><Header/></el-header>
      <el-container>
        <el-aside width="200px"><Aside/></el-aside>
        <el-main>
          <Main/>
        </el-main>
      </el-container>
      <el-footer><Footer/></el-footer>
    </el-container>
  </div>
</template>

<script>
import Header from "../components/GeneralCom/Header";
import Main from "../components/GeneralCom/Main";
import Footer from "../components/GeneralCom/Footer";
import Aside from "../components/GeneralCom/Aside";
import Tabs from "../components/GeneralCom/Tabs";
import {calcRoutes} from "../router/index";

export default {
  name: "General",
  components:{Header,Main,Footer,Aside,Tabs},
  props: {
    msg: String
  },
  created(){calcRoutes()},


}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
}

body > .el-container {
  margin-bottom: 40px;
}
.container{
  position: absolute;
  top:0;
  left: 0;
  width: 100%;
  height:100%;
}
</style>
