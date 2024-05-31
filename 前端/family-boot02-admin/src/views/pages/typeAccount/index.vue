<!--<template>-->
<!--  <div class="app-container">-->
<!--    &lt;!&ndash;    <h>这是我的转账页面</h>&ndash;&gt;-->
<!--    &lt;!&ndash;条件查询&ndash;&gt;-->
<!--    <el-form :inline="true" :model="typeAccountForm" class="demo-form-inline">-->
<!--      <el-form-item label="姓名">-->
<!--        <el-input v-model="typeAccountForm.name" placeholder="输入内容"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="账户编码">-->
<!--        <el-input v-model="typeAccountForm.accountCode" placeholder="输入内容"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="账户名称">-->
<!--        <el-input v-model="typeAccountForm.accountName" placeholder="输入内容"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="small" @click="getTypeAccountQuery">查询</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
<!--    &lt;!&ndash;    表格&ndash;&gt;-->
<!--    <el-table :data="typeAccountList" style="width: 100%">-->
<!--      <el-table-column label="序号" type="index" width="80px"/>-->
<!--      <el-table-column prop="accountDescription" label="账户描述"/>-->
<!--      <el-table-column prop="accountCode" label="账户编码"/>-->
<!--      <el-table-column prop="accountName" label="账户名称"/>-->
<!--      <el-table-column prop="balanceMoney" label="账户余额"/>-->
<!--      <el-table-column prop="accrualMoney" label="发生额"/>-->
<!--      <el-table-column prop="name" label="姓名"/>-->
<!--      <el-table-column prop="divertName" label="发生对象"/>-->
<!--      <el-table-column prop="divertTime" label="发生时间"/>-->
<!--      <el-table-column prop="createTime" label="创建时间"/>-->
<!--      <el-table-column prop="remark" label="备注"/>-->
<!--      <el-table-column label="操作" width="280">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button type="primary" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>-->
<!--          <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row.aid)">删除</el-button>-->
<!--          <el-button type="primary" size="mini" @click="add(scope.row)">收入/支出</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
<!--    &lt;!&ndash;    分页&ndash;&gt;-->
<!--    <el-pagination-->
<!--      :current-page="pageIndex"-->
<!--      :page-size="pageSize"-->
<!--      :total="total"-->
<!--      style="padding: 30px 0; text-align: center;"-->
<!--      layout="total, prev, pager, next, jumper"-->
<!--      @current-change="typeAccountLists"-->
<!--    />-->
<!--    &lt;!&ndash; 收入/支出窗体 &ndash;&gt;-->
<!--    <el-dialog-->
<!--      title="修改账户信息"-->
<!--      :visible.sync="typeAccountVisible"-->
<!--      width="65%"-->
<!--    >-->
<!--      <el-form :model="typeAccount" label-width="80px">-->
<!--        <el-form-item label="账户说明">-->
<!--          <el-input v-model="typeAccount.accountDescription"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="选择账户">-->
<!--          <el-select v-model="typeAccount.aid" placeholder="请选择" size="small">-->
<!--            <el-option-->
<!--              v-for="item in accountList"-->
<!--              :key="item.accountDes"-->
<!--              :label="item.accountDes"-->
<!--              :value="item.aid"-->
<!--            />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="收入/支出">-->
<!--          <el-radio-group v-model="typeAccount.type" size="small">-->
<!--            <el-radio label="0">支出</el-radio>-->
<!--            <el-radio label="1">收入</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="发生额">-->
<!--          <el-input v-model="typeAccount.accrualMoney" type="Number"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注">-->
<!--          <el-input v-model="typeAccount.remark" type="textarea"/>-->
<!--        </el-form-item>-->
<!--      </el-form>-->

<!--      <span slot="footer" class="dialog-footer">-->
<!--        &lt;!&ndash; <el-button @click="dialogVisible = false">取 消</el-button> &ndash;&gt;-->
<!--        <el-button type="primary" @click="addAccount()">确 定</el-button>-->
<!--      </span>-->
<!--    </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import typeAccount from '@/api/typeAccount'-->
<!--import account from '@/api/account'-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      typeAccountList: [], // 接收数据-->
<!--      pageIndex: 1, // 当前第几页-->
<!--      pageSize: 5, // 每页显示多少条-->
<!--      typeAccountForm: {-->
<!--        accountCode: '', // 封装查询条件-->
<!--        accountName: '',-->
<!--        name: ''-->
<!--      },-->
<!--      total: 0, // 总共记录-->
<!--      accountList: [], // 接收查询账户及用户-->
<!--      typeAccount: { // 收入/支出信息-->
<!--        ttid: 0,-->
<!--        aid: 0,-->
<!--        accountDescription: '',-->
<!--        accountCode: '',-->
<!--        accountName: '',-->
<!--        accrualMoney: 0,-->
<!--        balanceMoney: 0,-->
<!--        dvtId: 0,-->
<!--        outId: 0,-->
<!--        name: '',-->
<!--        tid: 0,-->
<!--        type: 0,-->
<!--        remark: ''-->
<!--      },-->
<!--      typeAccountVisible: false // 控制修改显示-->
<!--      // listData: [] // 接收数据-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.typeAccountLists()-->
<!--  },-->
<!--  methods: {-->
<!--    // 条件查询事件-->
<!--    getTypeAccountQuery() {-->
<!--      this.typeAccountLists()-->
<!--    },-->
<!--    // 条件查询带分页-->
<!--    typeAccountLists(pageIndex = 1) {-->
<!--      this.pageIndex = pageIndex-->
<!--      typeAccount.listData(this.pageIndex, this.pageSize, this.typeAccountForm).then(response => {-->
<!--        this.typeAccountList = response.data.records-->
<!--        this.total = response.data.total-->
<!--        // console.log(response.data)-->
<!--      })-->
<!--    },-->
<!--    // // 修改打开窗体-->
<!--    // update(row) {-->
<!--    //   this.typeAccount.aid = row.aid-->
<!--    //   this.typeAccount.uid = row.uid-->
<!--    //   this.typeAccount.typeAccountDes = row.typeAccountDes-->
<!--    //   this.typeAccount.typeAccountMoney = row.typeAccountMoney-->
<!--    //   this.typeAccount.remark = row.remark-->
<!--    //   this.typeAccountVisible = true-->
<!--    // },-->
<!--    // // 提交数据-->
<!--    // updateAccount() {-->
<!--    //     typeAccount.update(this.typeAccount).then(response => {-->
<!--    //       this.$message({-->
<!--    //         message: response.message,-->
<!--    //         type: 'success'-->
<!--    //       })-->
<!--    //       this.typeAccount = {} // 清空对象-->
<!--    //       this.typeAccountVisible = false // 关闭窗体-->
<!--    //       this.typeAccountLists()// 查询数据-->
<!--    //     })-->
<!--    //   }-->
<!--    // },-->
<!--    // 添加窗体-->
<!--    add(row) {-->
<!--      this.typeAccount = {}-->
<!--      this.typeAccount.ttid = row.ttid-->
<!--      this.typeAccount.name = row.name-->
<!--      this.typeAccount.accountCode = row.accountCode-->
<!--      this.typeAccount.accountName = row.accountName-->
<!--      this.accountListWhere()-->
<!--      this.typeAccountVisible = true-->
<!--    },-->
<!--    accountListWhere() {-->
<!--      account.listWhere().then(response => {-->
<!--        this.accountList = response.data-->
<!--      })-->
<!--    },-->
<!--    addAccount() {-->
<!--      typeAccount.add(this.typeAccount).then(response => {-->
<!--        this.$message({-->
<!--          message: response.message,-->
<!--          type: 'success'-->
<!--        })-->
<!--        this.typeAccount = {} // 清空对象-->
<!--        this.typeAccountVisible = false // 关闭窗体-->
<!--        this.typeAccountLists()// 查询数据-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->
<!--<style>-->
<!--</style>-->
<template>
  <div class="app-container">
    <!-- <h>这是我的转账页面</h> -->
    <!-- 条件查询 -->
    <el-form :inline="true" :model="typeAccountQuery" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="typeAccountQuery.name" placeholder="输入名称"></el-input>
      </el-form-item>
      <el-form-item label="账户编码">
        <el-input v-model="typeAccountQuery.accountCode" placeholder="输入名称"></el-input>
      </el-form-item>
      <el-form-item label="账户名称">
        <el-input v-model="typeAccountQuery.accountName" placeholder="输入名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="getUsersQuery">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 收入或者支出 -->
    <!-- <div>
      <el-button type="success" icon="el-icon-star-on"  @click="add">收入/支出</el-button>
    </div> -->
    <!-- 表格 -->
    <el-table :data="typeAccountList" style="width: 100%">
      <el-table-column label="序号" type="index" width="80px">
      </el-table-column>
      <el-table-column prop="name" label="姓名">
      </el-table-column>
      <el-table-column prop="accountDescription" label="账户描述">
      </el-table-column>
      <el-table-column prop="accountCode" label="账户编码">
      </el-table-column>
      <el-table-column prop="accountName" label="账户名称">
      </el-table-column>
      <el-table-column prop="balanceMoney" label="账户余额">
      </el-table-column>
      <el-table-column prop="accrualMoney" label="发生额">
      </el-table-column>

      <el-table-column prop="divertName" label="发生对象">
      </el-table-column>
      <el-table-column prop="divertTime" label="发生时间">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间">
      </el-table-column>
      <el-table-column label="操作" width="280">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row.ttid)">删除</el-button>
          <span v-if="scope.row.classType ===1">
                <el-button type="success" size="mini" @click="add(scope.row)">收入/支出</el-button>
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
    <!-- 收入支出窗体 -->

    <el-dialog
      title="收入/支出"
      :visible.sync="accountVisible"
      width="65%"
    >
      <el-form :model="accountType" label-width="80px">
        <el-form-item label="账户描述">
          <el-input v-model="accountType.accountDescription"></el-input>
        </el-form-item>
        <el-form-item label="选择账户">
          <el-select v-model="accountType.aid" placeholder="请选择" size="small">
            <el-option
              v-for="item in accountList"
              :key="item.accountDes"
              :label="item.accountDes"
              :value="item.aid"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收入/支出">
          <el-radio-group v-model="accountType.type" size="small">
            <el-radio label="0">支出</el-radio>
            <el-radio label="1">收入</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="发生额">
          <el-input v-model="accountType.accrualMoney" type="Number"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="accountType.remark"></el-input>
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
import typeAccount from '@/api/typeAccount'
import account from '@/api/account'

export default {
  data() {
    return {
      accountList: [], // 接收查询账户及用户
      accountType: {
        ttid: 0,
        aid: 0,
        accountDescription: '',
        accountCode: '',
        accountName: '',
        accrualMoney: '',
        balanceMoney: '',
        dvtId: 0,
        outId: 0,
        name: '',
        tid: 0,
        type: 0,
        remark: ''
      },
      accountVisible: false,
      typeAccountList: [], // 接收数据
      pageIndex: 1,
      pageSize: 10,
      total: 0,
      typeAccountQuery: { // 封装查询对象
        accountCode: '',
        accountName: '',
        name: ''
      }
    }
  },
  created() {
    this.listDatas()
  },
  methods: {
    addAccount() {
      typeAccount.add(this.accountType).then(response => {
        this.$message({
          message: response.message,
          type: 'success'
        })
        this.accountType = {} // 清空对象
        this.accountVisible = false // 关闭窗体
        this.listDatas()// 查询数据
      })
    },
    add(row) {
      this.accountListWhere()
      this.accountType.ttid = row.ttid
      // this.accountType.aid = row.aid
      this.accountType.name = row.name
      this.accountType.accountCode = row.accountCode
      this.accountType.accountName = row.accountName
      this.accountVisible = true
    },
    accountListWhere() {
      account.listWhere().then(response => {
        this.accountList = response.data
      })
    },
    getUsersQuery() {
      this.listDatas()
    },
    listDatas(pageIndex = 1) {
      this.pageIndex = pageIndex
      typeAccount.listData(this.pageIndex, this.pageSize, this.typeAccountQuery).then(response => {
        this.typeAccountList = response.data.records
        this.total = response.data.total
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
          return typeAccount.del(id) // 调用删除方法
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
    }
  }
}
</script>
<style>
</style>

