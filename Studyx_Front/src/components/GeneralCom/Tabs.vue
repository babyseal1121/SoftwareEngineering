<template>
  <div>
    <el-tabs v-model="editableTabsValue" type="card" @tab-click="clickTab" closable @tab-remove="removeTab">
      <el-tab-pane
          :key="item.name"
          v-for="item in editableTabs"
          :label="item.title"
          :name="item.name"
      >

      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "Tabs.vue",
  data() {
    return {

    }
  },
  computed:{
    editableTabsValue:{
      get(){
        return this.$store.state.editableTabsValue;
      },
      set(val){
        this.$store.state.editableTabsValue=val;
      }
    },
    editableTabs:{
      get(){
        return this.$store.state.editableTabs;
      },
      set(val){
        this.$store.state.editableTabs=val;
      }
    }
  },
  methods: {
    clickTab1(target){
      this.$router.push({name:target.name})
    },

    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if(activeName!="首页"){
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
              }
            }
          });
        }
        this.editableTabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
      }
     // this.$router.push({name:activeName})
    }

  },
}
</script>

<style scoped>

</style>
