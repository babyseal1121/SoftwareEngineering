<template>
  <div style="margin-bottom: 30px;display: flex;justify-content: center;align-items: center">

    <el-row>
      <el-input
          @keyup.enter.native="test"
          placeholder="搜索实验..."
          prefix-icon="el-icon-search"
          size="small"
          style="width: 400px;margin-right: 10px"
          v-model="keywords">
      </el-input>
      <el-button size="small" type="primary" icon="el-icon-search" @click="test">搜索</el-button>
    </el-row>
    <br>
    <el-row>

        <span class="pl"> 实验编号：</span>:
        <span class="pl">{{exercise.exerciseno}}</span>
        <br>
        <span class="pl">开始时间：</span>
        <span class="pl">{{exercise.exercisestarttime}}</span>
        <br>
        <span class="pl">结束时间：</span>
        <span class="pl">{{exercise.exerciseendtime}}</span><br>
        <br>
        <br>

    </el-row>
    <div style="margin-bottom: 20px;">
      <el-button
          size="small"
          @click="addTab(editableTabsValue)"
      >
        add tab
      </el-button>
    </div>
    <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
      <el-tab-pane
          v-for="(item, index) in editableTabs"
          :key="item.name"
          :label="item.title"
          :name="item.name"
      >
        {{item.content}}
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>
export default {
  name: "Test",
  data () {
    return {
      keywords: '',
      exercise:{},
      editableTabsValue: '2',
      editableTabs: [{
        title: 'Tab 1',
        name: '1',
        content: 'Tab 1 content'
      }, {
        title: 'Tab 2',
        name: '2',
        content: 'Tab 2 content'
      }],
      tabIndex: 2,
    }
  },
  methods: {
    test () {
      var _this=this;
      this.$axios
          .get('/test?no='+this.keywords,{
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              _this.exercise = successResponse.data.result
              //_this.isbn=_this.books[0].isbn
              this.$message.success("检索成功！");
            }
            else{
              this.$message.error("检索失败");
            }
          })
          .catch(failResponse => {
            his.$message.error("数据发送失败");
          })
    },
    addTab(targetName) {
      let newTabName = ++this.tabIndex + '';
      this.editableTabs.push({
        title: 'New Tab',
        name: newTabName,
        content: 'New Tab content'
      });
      this.editableTabsValue = newTabName;
    },
    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
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
    },
  }
}
</script>

<style scoped>

</style>
