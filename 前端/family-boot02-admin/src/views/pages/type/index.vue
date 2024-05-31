<template>
  <div class="app-container">
    <!-- <h>这是我的账户类型页面</h> -->
    <!-- 条件查询 -->
    <el-form :inline="true" :model="typeForm" class="demo-form-inline">
      <el-form-item label="账户类型编码">
        <el-input v-model="typeForm.typeCode" placeholder="输入名称" />
      </el-form-item>
      <el-form-item label="账户类型名称">
        <el-input v-model="typeForm.typeName" placeholder="输入名称" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="getTypeQuery">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 添加 -->
    <div>
      <el-button type="success" icon="el-icon-plus" @click="add">添加</el-button>
    </div>
    <!-- 表格 -->
    <el-table :data="typeList" style="width: 100%">
      <el-table-column label="序号" type="index" width="80px">
      </el-table-column>
      <el-table-column prop="typeCode" label="账户类型编码">
      </el-table-column>
      <el-table-column prop="typeName" label="账户类型名称">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row.tid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--    分页-->
    <el-pagination
      :current-page="pageIndex"
      :page-size="pageSize"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="listDatas"
    />
    <el-dialog
      title="账户类型添加"
      :visible.sync="typeVisible"
      width="65%"
    >
      <el-form :model="type" label-width="100px">
        <el-form-item label="账户类型编码">
          <el-input v-model="type.typeCode" />
        </el-form-item>
        <el-form-item label="账户类型名称">
          <el-input v-model="type.typeName" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="type.remark" type="textarea" />
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="addOrupdate()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import type from '@/api/type'

export default {
  data() {
    return {
      pageIndex: 1, // 当前第几页
      pageSize: 5, // 每页显示多少条
      total: 0, // 总共记录
      typeList: [], // 接收查询数据
      type: { // 添加修改对象
        tid: 0,
        typeCode: '',
        typeName: '',
        remark: ''
      },
      typeVisible: false, // 控制添加修改显示
      typeForm: { // 查询条件封装
        typeCode: '',
        typeName: ''
      }
    }
  },
  created() {
    this.listDatas()
  },
  methods: {
    // 查询按钮事件
    getTypeQuery() {
      this.listDatas()
    },
    // 条件查询带分页
    listDatas(pageIndex = 1) {
      this.pageIndex = pageIndex
      type.listData(this.pageIndex, this.pageSize, this.typeForm).then(response => {
        this.typeList = response.data.records
        this.total = response.data.total
        // console.log(response.data)
      })
    },
    // 删除方法
    del(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          return type.del(id) // 调用删除方法
        })
        .then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.listDatas()// 查询数据库
        })
        .catch(error => {
          console.log('catch的error', error)
          if (error === 'cancel') {
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          }
        })
    },
    // 修改打开窗体
    update(row) {
      this.type.tid = row.tid
      this.type.typeCode = row.typeCode
      this.type.typeName = row.typeName
      this.type.remark = row.remark
      this.typeVisible = true
    },
    // 提交数据
    addOrupdate() {
      if (this.type.tid === 0 || this.type.tid == null) {
        type.add(this.type).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.type = {} // 清空对象
          this.typeVisible = false // 关闭窗体
          this.listDatas()// 查询数据
        })
      } else {
        type.update(this.type).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.type = {} // 清空对象
          this.typeVisible = false // 关闭窗体
          this.listDatas()// 查询数据
        })
      }
    },
    // 添加窗体
    add() {
      this.type = {}
      this.typeVisible = true
    }
  }
}
</script>

<style>
</style>
