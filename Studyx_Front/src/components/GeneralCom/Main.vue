<template>
  <div>
    您好，{{$store.getters.username}}
    <!-- <Tabs/>-->
    <Tabs/>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Main"
}
</script>

<style scoped>
.el-icon-user{
  font-size: 30px;
  color: green;
}
.el-icon-search{
  font-size: 20px;
  color: black;
}
</style>
