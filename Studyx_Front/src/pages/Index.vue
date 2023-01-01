<template>
  <div>
   首页
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
      this.$axios.get("/adminmenu").then(res=>{
        console.log(res.data.menu_data)
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
