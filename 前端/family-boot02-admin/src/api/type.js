import request from '@/utils/request'

export default {
  // 按照ID查询
  getById(tid) {
    return request({
      url: `/type/getById/${tid}`,
      method: 'get'

    })
  },
  // 分户
  listQuery() {
    return request({
      url: `/type/listQuery`,
      method: 'get'
    })
  },
  listData(pageIndex, pageSize, type) { // 查询所有账户类型信息分页
    return request({
      url: `/type/list/${pageIndex}/${pageSize}`,
      method: 'post',
      data: type
    })
  },
  // 删除方法
  del(id) {
    return request({
      url: '/type/del/' + id,
      method: 'delete'
    })
  },
  // 修改方法
  update(type) {
    return request({
      url: '/type/update',
      method: 'put',
      data: type
    })
  },
  // 添加方法
  add(type) {
    return request({
      url: '/type/add',
      method: 'post',
      data: type
    })
  }
}
