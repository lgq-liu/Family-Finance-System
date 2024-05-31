<template>
  <div class="app-container">
    <!-- <h>这是我的家庭页面</h> -->
    <!-- 条件查询 -->
    <el-form :inline="true" :model="usersFrom" class="demo-form-inline">
      <el-form-item label="用户名">
        <el-input v-model="usersFrom.username" placeholder="输入名称"/>
      </el-form-item>
      <el-form-item label="真实姓名">
        <el-input v-model="usersFrom.name" placeholder="输入名称"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="getUsersQuery">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 添加 -->
    <div>
      <el-button type="success" icon="el-icon-plus" @click="add">添加用户</el-button>
    </div>
    <!-- 表格 -->
    <el-table :data="listUsers" style="width: 100%">
      <el-table-column label="序号" type="index" width="80px"/>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="name" label="真实姓名"/>
      <el-table-column prop="sex" label="性别">
        <template slot-scope="scope">
            <span v-if="scope.row.sex===0">
              <el-tag type="success">男</el-tag>
            </span>
          <span v-if="scope.row.sex===1">
              <el-tag type="danger">女</el-tag>
            </span>
        </template>
      </el-table-column>
      <el-table-column prop="occupation" label="职业"/>
      <el-table-column prop="family.familyName" label="家庭"/>
      <el-table-column prop="education.educationName" label="学历"/>
      <el-table-column prop="description" label="描述"/>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column prop="remark" label="备注"/>
      <el-table-column label="操作" width="280">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row.fid)">删除</el-button>
          <span v-if="scope.row.isType===0">
            <el-button type="success" size="mini" @click="createAccount(scope.row)">开通账户</el-button>
          </span>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="pageIndex"
      :page-size="pageSize"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="listDatas"
    />
    <!-- 添加窗体 -->
    <el-dialog
      title="添加用户"
      :visible.sync="usersVisible"
      width="65%"
    >
      <el-form :model="users" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="users.username" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="users.password" type="password" />
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="users.name" />
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="users.sex" size="small">
            <el-radio label="0">男</el-radio>
            <el-radio label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="选择家庭">
          <el-select v-model="users.fid" placeholder="请选择" size="small">
            <el-option
              v-for="item in listFamily"
              :key="item.fid"
              :label="item.familyName"
              :value="item.fid"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="选择学历">
          <el-select v-model="users.eid" placeholder="请选择" size="small">
            <el-option
              v-for="item in listEducation"
              :key="item.eid"
              :label="item.educationName"
              :value="item.eid"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="职业">
          <el-input v-model="users.occupation" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="users.description" type="textarea" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="users.remark" type="textarea" />
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="addOrUpdateUsers">确 定</el-button>
      </span>
    </el-dialog>
    <!--    开通账户-->
    <el-dialog
      title="开通账户"
      :visible.sync="accountVisible"
      width="65%"
    >
      <el-form :model="account" label-width="80px">
        <el-form-item label="账户说明">
          <el-input v-model="account.accountDes"/>
        </el-form-item>
        <el-form-item label="总金额">
          <el-input v-model="account.accountMoney" type="Number"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="account.remark" type="textarea"/>
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="addAccount">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import education from '@/api/education'
import users from '@/api/users'
import account from '@/api/account'
import family from '@/api/family'

export default {
  data() {
    return {
      account: {
        aid: 0,
        uid: 0,
        accountDes: '',
        accountMoney: 0,
        remark: ''
      },
      accountVisible: false, // 开通账户窗体是否显示
      listFamily: [], // 家庭
      listEducation: [], // 接收学历
      usersVisible: false, // 窗体是否显示
      total: 0, // 总记录
      listUsers: [], // 接收数据
      pageIndex: 1, // 当前第几页
      pageSize: 5, // 每页显示多少条
      users: { // 绑定添加
        uid: 0,
        username: '',
        password: '',
        name: '',
        sex: 0,
        fid: 0,
        eid: 0,
        occupation: '',
        description: '',
        remark: ''
      },

      usersFrom: { // 查询条件
        username: '',
        name: ''
      }
    }
  },
  created() {
    this.listDatas()
    this.familyLists()
    this.listEducations()
  },
  methods: {
    // 提交开通账户数据
    addAccount() {
      account.add(this.account).then(response => {
        this.$message({
          message: response.message,
          type: 'success'
        })
        this.account = {} // 清空对象
        this.accountVisible = false // 关闭窗体
        this.listDatas() // 刷新数据库
      })
    },
    // 开通账户
    createAccount(row) {
      this.account.uid = row.uid
      this.accountVisible = true
    },
    // 查询家庭
    familyLists() {
      family.list().then(response => {
        this.listFamily = response.data
        //    console.log(response.data)
      })
    },
    // 学历查询
    listEducations() {
      education.list().then(response => {
        this.listEducation = response.data
        //    console.log(response.data)
      })
    },
    // 添加事件
    add() {
      this.familyLists()
      this.listEducations()
      this.users = {} // 添加之前先清空对象
      this.usersVisible = true
    },
    // 查询按钮事件
    getUsersQuery() {
      this.listDatas()
    },
    // 查询数据带分页
    listDatas(pageIndex = 1) {
      this.pageIndex = pageIndex
      users.listData(this.pageIndex, this.pageSize, this.usersFrom).then(response => {
        this.listUsers = response.data.records
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
          return users.del(id) // 调用删除方法
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
      this.users.uid = row.uid
      this.users.username = row.username
      this.users.password = row.password
      this.users.name = row.name
      this.users.sex = row.sex
      this.users.fid = row.fid
      this.users.eid = row.eid
      this.users.occupation = row.occupation
      this.users.description = row.description
      this.users.remark = row.remark
      this.usersVisible = true
    },
    // 添加修改用户的数据提交
    addOrUpdateUsers() {
      if (this.users.uid === 0 || this.users.uid == null) {
        users.add(this.users).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.users = {} // 清空对象
          this.usersVisible = false // 关闭窗体
          this.listDatas()// 查询数据
        })
      } else {
        users.update(this.users).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.users = {} // 清空对象
          this.usersVisible = false // 关闭窗体
          this.listDatas()// 查询数据
        })
      }
    }
  }
}
</script>

<style>
</style>

