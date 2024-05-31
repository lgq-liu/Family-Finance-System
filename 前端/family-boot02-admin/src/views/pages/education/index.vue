<template>
  <div class="app-container">
    <!-- <h>这是我的学历页面</h> -->
    <!-- 条件查询 -->
    <el-form :inline="true" :model="formEducation" class="demo-form-inline">
      <el-form-item label="学历编码">
        <el-input v-model="formEducation.educationCode" placeholder="输入名称" />
      </el-form-item>
      <el-form-item label="学历名称">
        <el-input v-model="formEducation.educationName" placeholder="输入名称" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="getEducationQuery">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 添加 -->
    <div>
      <el-button type="success" icon="el-icon-plus" @click="add">添加</el-button>
    </div>
    <!-- 表格 -->
    <el-table :data="listData" style="width: 100%">
      <el-table-column label="序号" type="index" width="80px">
      </el-table-column>
      <el-table-column prop="educationCode" label="学历编码">
      </el-table-column>
      <el-table-column prop="educationName" label="学历名称">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row.eid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="pageIndex"
      :page-size="pageSize"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="listDatas"
    />
    <el-dialog
      title="学历信息添加"
      :visible.sync="educationVisible"
      width="65%"
    >
      <el-form :model="education" label-width="80px">
        <el-form-item label="学历编码">
          <el-input v-model="education.educationCode" />
        </el-form-item>
        <el-form-item label="学历名称">
          <el-input v-model="education.educationName" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="education.remark" type="textarea" />
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
import education from '@/api/education'

export default {
  data() {
    return {
      pageIndex: 1, // 当前第几页
      pageSize: 5, // 每页显示多少条
      total: 0, // 总共记录
      education: { // 添加修改对象
        eid: 0,
        educationCode: '',
        educationName: '',
        remark: ''
      },
      educationVisible: false, // 控制添加修改显示
      formEducation: { // 查询条件封装
        educationCode: '',
        educationName: ''
      },
      listData: [] // 接收数据
    }
  },
  created() {
    this.listDatas()
  },
  methods: {
    // 查询按钮事件
    getEducationQuery() {
      this.listDatas()
    },
    // 条件查询带分页
    listDatas(pageIndex = 1) {
      this.pageIndex = pageIndex
      education.listData(this.pageIndex, this.pageSize, this.formEducation).then(response => {
        this.listData = response.data.records
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
          return education.del(id) // 调用删除方法
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
      this.education.eid = row.eid
      this.education.educationCode = row.educationCode
      this.education.educationName = row.educationName
      this.education.remark = row.remark
      this.educationVisible = true
    },
    // 提交数据
    addOrupdate() {
      if (this.education.eid === 0 || this.education.eid == null) {
        education.add(this.education).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.education = {} // 清空对象
          this.educationVisible = false // 关闭窗体
          this.listDatas()// 查询数据
        })
      } else {
        education.update(this.education).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.education = {} // 清空对象
          this.educationVisible = false // 关闭窗体
          this.listDatas()// 查询数据
        })
      }
    },
    // 添加窗体
    add() {
      this.education = {}
      this.educationVisible = true
    }
  }
}
</script>

<style>
</style>
