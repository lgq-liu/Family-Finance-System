<template>
  <div class="app-container">
    <!--    <h>这是我的账户页面</h>-->
    <!--条件查询-->
    <el-form :inline="true" :model="accountForm" class="demo-form-inline">
      <el-form-item label="账户说明">
        <el-input v-model="accountForm.accountDes" placeholder="输入名称" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="getAccountQuery">查询</el-button>
      </el-form-item>
    </el-form>
    <!--    表格-->
    <el-table :data="accountList" style="width: 100%">
      <el-table-column label="序号" type="index" width="80px" />
      <el-table-column prop="accountDes" label="账户说明" />
      <el-table-column prop="accountMoney" label="总金额" />
      <el-table-column prop="createTime" label="创建时间" />
      <el-table-column prop="remark" label="备注" />
      <el-table-column label="操作" width="280">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row.aid)">删除</el-button>
          <el-button type="success" size="mini" @click="add(scope.row)">分户</el-button>
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
      @current-change="accountLists"
    />
    <!--    修改账户-->
    <el-dialog
      title="修改账户信息"
      :visible.sync="updateVisible"
      width="65%"
    >
      <el-form :model="account" label-width="80px">
        <el-form-item label="账户说明">
          <el-input v-model="account.accountDes" />
        </el-form-item>
        <el-form-item label="总金额">
          <el-input v-model="account.accountMoney" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="account.remark" type="textarea" />
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="updateAccount()">确 定</el-button>
      </span>
    </el-dialog>
    <!--    分户-->
    <el-dialog
      title="分户"
      :visible.sync="accountVisible"
      width="65%"
    >
      <el-form :model="accountType" label-width="80px">
        <el-form-item label="选择账户">
          <el-select v-model="accountType.tid" placeholder="请选择" size="small">
            <el-option
              v-for="item in accountListQuery"
              :key="item.typeName"
              :label="item.typeName"
              :value="item.tid"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="分配金额">
          <el-input v-model="accountType.balanceMoney" type="Number" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="accountType.remark" type="textarea" />
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
import account from '@/api/account'
import typeAccount from '@/api/typeAccount'
import type from '@/api/type'

export default {
  data() {
    return {
      accountId: {
        aid: 0,
        uid: 0,
        accountDes: '',
        accountMoney: 0,
        users: {
          username: '',
          name: ''
        }
      },
      type: {
        accountCode: '',
        accountName: ''
      },
      accountListQuery: [],
      accountType: {
        aid: 0,
        accountDescription: '',
        accountCode: '',
        accountName: '',
        balanceMoney: 0,
        tid: 0,
        classType: 1,
        name: '',
        dvtId: 0,
        remark: ''
      },
      accountList: [], // 接收数据
      pageIndex: 1, // 当前第几页
      pageSize: 5, // 每页显示多少条
      accountForm: {
        accountDes: '' // 封装查询条件
      },
      total: 0, // 总共记录
      account: { // 修改账户
        aid: 0,
        uid: 0,
        accountDes: '',
        accountMoney: 0,
        remark: ''
      },
      updateVisible: false,
      accountVisible: false // 控制修改显示
      // listData: [] // 接收数据
    }
  },
  created() {
    this.accountLists()
  },
  methods: {
    // 条件查询事件
    getAccountQuery() {
      this.accountLists()
    },
    // 条件查询带分页
    accountLists(pageIndex = 1) {
      this.pageIndex = pageIndex
      account.listData(this.pageIndex, this.pageSize, this.accountForm).then(response => {
        this.accountList = response.data.records
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
          return account.del(id) // 调用删除方法
        })
        .then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.accountLists()// 查询数据库
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
      this.account.aid = row.aid
      this.account.uid = row.uid
      this.account.accountDes = row.accountDes
      this.account.accountMoney = row.accountMoney
      this.account.remark = row.remark
      this.updateVisible = true
    },
    // 提交数据
    updateAccount() {
      if (this.account.aid === 0 || this.account.aid == null) {
        account.add(this.account).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.account = {} // 清空对象
          this.accountVisible = false // 关闭窗体
          this.accountLists()// 查询数据
        })
      } else {
        account.update(this.account).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })
          this.account = {} // 清空对象
          this.updateVisible = false // 关闭窗体
          this.accountLists()// 查询数据
        })
      }
    },
    add(row) {
      this.account = {}
      this.accountType.aid = row.aid
      this.accountType.accountDescription = row.accountDes
      this.getAccountIds()
      this.lists()
      this.accountVisible = true
    },
    getTypeById() {
      type.getById(this.accountType.tid).then(response => {
        this.type = response.data
        // this.accountType.accountName = response.data.accountName
      })
    },
    getAccountIds() {
      account.getAccountById(this.accountType.aid).then(response => {
        this.accountId = response.data
        // this.accountType.dvtId = response.data.users.uid
        // this.accountType.name = response.data.users.name
      })
    },
    lists() {
      type.listQuery().then(response => {
        this.accountListQuery = response.data
        // console.log(response.data)
      })
    },
    addAccount() {
      this.getTypeById()
      this.accountType.accountCode = this.type.accountCode
      this.accountType.accountName = this.type.accountName
      this.accountType.dvtId = this.accountId.uid
      this.accountType.name = this.accountId.users.name
      typeAccount.save(this.accountType).then(response => {
        this.$message({
          message: response.message,
          type: 'success'
        })
        this.accountType = {} // 清空对象
        this.accountVisible = false // 关闭窗体
        this.accountLists()// 查询数据
      })
    }
  }
}
</script>

<style>
</style>
