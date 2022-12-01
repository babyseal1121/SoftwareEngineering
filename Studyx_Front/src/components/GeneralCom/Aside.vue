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

        <el-menu-item :index="child.path" v-for="child in item.child" :key="child.name" >
          <i :class="child.icon" @click="selectMenu(item)"></i>{{child.name}}
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      menu_data: [
        {
          name: '一级菜单1',
          icon: 'el-icon-location',
          path: '/index/menu1',
          child: [
            {
              name: '二级菜单1-1',
              icon: 'el-icon-user',
              path: '/index/menu11'
            },
            {
              name: '二级菜单1-2',
              icon: 'el-icon-user-solid',
              path: '/index/menu12'
            }
          ]
        },
        {
          name: '一级菜单2',
          icon: 'el-icon-document',
          path: '/index/menu2',
          child: [
            {
              name: '二级菜单2-1',
              icon: 'el-icon-star-on',
              path: '/index/menu21'
            },
            {
              name: '二级菜单2-2',
              icon: 'el-icon-star-off',
              path: '/index/menu22'
            }
          ]
        },
        {
          name: '成绩管理',
          icon: 'el-icon-setting',
          path: '/myinfo',
          child: [
            {
              name: '成绩权重设置',
              icon: 'el-icon-s-help',
              path: '/gradeset'
            },
            {
              name: '查看成绩',
              icon: 'el-icon-help',
              path: '/myinfo'
            }
          ]
        }
      ]
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
      this.$store.commit("addEditableTabs",item);
    },
  },
}
</script>

<style scoped>

</style>
