<template>
  <div style="height: 100%;">
    <el-menu background-color="#545c64" text-color="#ffffff"
             active-text-color="#ffd04b" class="el-menu-vertical-demo"
             router>
      <!-- <el-menu-item :index="item.path" v-for="item in menu_data" :key="item.name">
           <i :class="item.icon"></i>{{item.name}}
       </el-menu-item>-->
      <el-submenu :index="item.path" v-for="item in menu_data" :key="item.name">
        <template slot="title"><i :class="item.icon"></i><span>{{item.name}}</span></template>
        <el-menu-item :index="child.path" v-for="child in item.child" :key="child.name" @click="selectMenu(child)" >
          <i :class="child.icon" ></i>{{child.name}}
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import navData from "@/utils/navData";
export default {

  name: "Aside",
  data(){
    return {}
  },
  computed:{
    menu_data:{
      get(){
        return this.$store.state.menu_data
      }
    }
  },


  methods: {
    handleOpen(key, keyPath) {
      console.log("打开：",key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log("关闭：",key, keyPath);
    },
    handSelect(key, keyPath) {
      console.log("选择：",key, keyPath);
    },
    selectMenu(item){
      this.$router.push({path:item.path})
      this.$store.commit("addEditableTabs",item);
    },
  },
}
</script>

<style scoped>

</style>
