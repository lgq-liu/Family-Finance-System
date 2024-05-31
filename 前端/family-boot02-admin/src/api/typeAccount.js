import request from '@/utils/request'

export default {
  // 添加
  save(account) {
    return request({
      url: '/typeAccount/save',
      method: 'post',
      data: account
    })
  },
  // 删除方法
  del(id) {
    return request({
      url: '/typeAccount/del/' + id,
      method: 'delete'
    })
  },
  // 修改方法
  update(typeAccount) {
    return request({
      url: '/typeAccount/update',
      method: 'put',
      data: typeAccount
    })
  },
  add(typeAccount) {
    return request({
      url: '/typeAccount/add',
      method: 'post',
      data: typeAccount
    })
  },
  // 查询所有用户不分页
  list() {
    return request({
      url: '/typeAccount/list',
      method: 'get'
    })
  },
  listData(pageIndex, pageSize, typeAccount) { // 查询所有用户信息分页
    return request({
      // 两种写法（1）'/classify/api/list/'+pageNum+'/'+pageSize,
      // 第二种写法
      // url: `/classify/api/list/${pageNum}/${pageSize}`,
      // url: '/users/list',
      url: `/typeAccount/list/${pageIndex}/${pageSize}`,
      method: 'post',
      data: typeAccount
    })
  }
}
