import request from '@/utils/request'

export default {
  // 查询所有用户不分页
  list() {
    return request({
      url: '/users/list',
      method: 'get'
    })
  },
  listData(pageIndex, pageSize, users) { // 查询所有用户信息分页
    return request({
      // 两种写法（1）'/classify/api/list/'+pageNum+'/'+pageSize,
      // 第二种写法
      // url: `/classify/api/list/${pageNum}/${pageSize}`,
      // url: '/users/list',
      url: `/users/list/${pageIndex}/${pageSize}`,
      method: 'post',
      data: users
    })
  },
  // 删除方法
  del(id) {
    return request({
      url: '/users/del/' + id,
      method: 'delete'
    })
  },
  // 修改方法
  update(users) {
    return request({
      url: '/users/update',
      method: 'put',
      data: users
    })
  },
  // 添加方法
  add(users) {
    return request({
      url: '/users/add',
      method: 'post',
      data: users
    })
  }
}
