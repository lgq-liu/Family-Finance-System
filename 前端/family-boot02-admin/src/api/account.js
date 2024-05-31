import request from '@/utils/request'

export default {
  getAccountById(id) {
    return request({
      url: `/account/accountById/${id}`,
      method: 'get'

    })
  },
  listWhere() {
    return request({
      url: '/account/listWhere',
      method: 'get'
    })
  },
  // 删除方法
  del(id) {
    return request({
      url: '/account/del/' + id,
      method: 'delete'
    })
  },
  // 修改方法
  update(account) {
    return request({
      url: '/account/update',
      method: 'put',
      data: account
    })
  },
  add(account) {
    return request({
      url: '/account/add',
      method: 'post',
      data: account
    })
  },
  // 查询所有用户不分页
  list() {
    return request({
      url: '/account/list',
      method: 'get'
    })
  },
  listData(pageIndex, pageSize, account) { // 查询所有用户信息分页
    return request({
      // 两种写法（1）'/classify/api/list/'+pageNum+'/'+pageSize,
      // 第二种写法
      // url: `/classify/api/list/${pageNum}/${pageSize}`,
      // url: '/users/list',
      url: `/account/list/${pageIndex}/${pageSize}`,
      method: 'post',
      data: account
    })
  }
}
