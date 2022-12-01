<template>
  <div>
    <h1>{{$store.state.username}}</h1>
    数据状态：{{$store.state.userState}}
    计算数据状态：{{$store.getters.getUserState}}
    <el-button @click="addState">状态+1</el-button>
    <el-button @click="getTest">get数据</el-button>
    <el-button @click="postTest">post测试1</el-button>
  </div>
</template>

<script>
export default {
  name: "Index",
  methods: {
    addState(){
      this.$store.commit('setUserState',1);
    },
    getTest(){
      this.$axios.get("/test").then(res=>{
        console.log(res.data)
      })
    },
    postTest(){
      this.$axios.post("/post/test1",{id:1}).then(res=>{
        console.log(res.data)
      })
    }

  }
}
</script>

<style scoped>

</style>
