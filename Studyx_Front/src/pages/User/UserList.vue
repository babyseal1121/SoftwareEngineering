<template>
  <div>
    <div id="button_div">
      <el-input id='search-input' size='small' label-width='100px'
                prefix-icon="el-icon-search" placeholder="请输入名字..."
                v-model='search_name'>

      </el-input>
      <el-button type="primary" size='small' class="search-button" @click="search">搜索</el-button>
    </div>
    <el-table
        :data="userData"
        style="width: 100%"
        border
        :default-sort = "{prop: 'date', order: 'descending'}"
        header-row-class-name="table_header_class"
        height="360"
    >
      <el-table-column
          type="index"
          width="50">
      </el-table-column>
      <el-table-column
          prop="date"
          label="日期"
          sortable
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          sortable
          width="180">
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址">
      </el-table-column>
      <el-table-column
          prop="tag"
          label="标签">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.tag === '家' ? 'primary' : 'success'"
              disable-transitions>{{scope.row.tag}}</el-tag>
        </template>
      </el-table-column>`
    </el-table>

  </div>
</template>

<script>
export default {
  name: "UserList"
}
</script>

<style scoped>
/deep/ .table_header_class th{
  background-color: #91a8b1 !important;
  color:white;
}
</style>
