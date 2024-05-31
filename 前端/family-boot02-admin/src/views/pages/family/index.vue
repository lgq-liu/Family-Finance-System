<template>
  <div class="app-container">
    <!-- <h>这是我的家庭页面</h> -->
    <!-- 条件查询 -->
    <el-form :inline="true" :model="formFamily" class="demo-form-inline">
      <el-form-item label="家庭编码">
        <el-input v-model="formFamily.familyCode" placeholder="输入名称" />
      </el-form-item>
      <el-form-item label="家庭名称">
        <el-input v-model="formFamily.familyName" placeholder="输入名称" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="getfamilyQuery">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 添加 -->
    <div>
      <el-button type="success" icon="el-icon-plus" @click="add">添加</el-button>
    </div>
    <!-- 表格 -->
    <el-table :data="listData" style="width: 100%">
      <el-table-column label="序号" type="index" width="80px" />
      <el-table-column prop="familyCode" label="家庭编码" />
      <el-table-column prop="familyName" label="家庭名称" />
      <el-table-column prop="createTime" label="创建时间" />
      <el-table-column prop="remark" label="备注" />
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row.fid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <!-- <el-pagination
         @size-change="handleSizeChange"
         @current-change="handleCurrentChange"
         :current-page="currentPage4"
         :page-sizes="[100, 200, 300, 400]"
         :page-size="100"
         layout="total, sizes, prev, pager, next, jumper"
         :total="400">
       </el-pagination> -->

    <el-pagination
      :current-page="pageIndex"
      :page-size="pageSize"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="listDatas"
    />
    <el-dialog
      title="家庭信息添加"
      :visible.sync="familyVisible"
      width="65%"
    >
      <el-form :model="family" label-width="80px">
        <el-form-item label="家庭编码">
          <el-input v-model="family.familyCode" />
        </el-form-item>
        <el-form-item label="家庭名称">
          <el-input v-model="family.familyName" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="family.remark" type="textarea" />
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
import family from '@/api/family'

export default {
  data() {
    return {
      pageIndex: 1, // 当前第几页
      pageSize: 5, // 每页显示多少条
      total: 0, // 总共记录
      family: { // 添加修改对象
        fid: 0,
        familyCode: '',
        familyName: '',
        remark: ''
      },
      familyVisible: false, // 控制添加修改显示
      formFamily: { // 查询条件封装
        familyCode: '',
        familyName: ''
      },
      listData: [] // 接收数据
    }
  },
  created() {
    this.listDatas()
  },
  methods: {
    // 查询按钮事件
    getfamilyQuery() {
      this.$message({
        message: response.message,
        type: 'success'
      })
      this.listDatas()
    },
    // 条件查询带分页
    listDatas(pageIndex = 1) {
      this.pageIndex = pageIndex
      family.listData(this.pageIndex, this.pageSize, this.formFamily).then(response => {
        this.listData = response.data.records
        this.total = response.data.total
        console.log(response.data)
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
          return family.del(id) // 调用删除方法
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
      this.family.fid = row.fid
      this.family.familyCode = row.familyCode
      this.family.familyName = row.familyName
      this.family.remark = row.remark
      this.familyVisible = true
    },
    // 提交数据
    addOrupdate() {
      if (this.family.fid === 0 || this.family.fid == null) {
        family.add(this.family).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.family = {} // 清空对象
          this.familyVisible = false // 关闭窗体
          this.listDatas()// 查询数据
        })
      } else {
        family.update(this.family).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.family = {} // 清空对象
          this.familyVisible = false // 关闭窗体
          this.listDatas()// 查询数据
        })
      }
    },
    // 添加窗体
    add() {
      this.family = {}
      this.familyVisible = true
    }
    // 查询方法
    // listDatas(){
    //   family.list().then(response =>{
    //     this.listData = response.data
    //     console.log(response.data)
    //   })
    // }
  }
}
</script>

<style>
</style>
