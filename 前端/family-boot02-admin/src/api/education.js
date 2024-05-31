import request from '@/utils/request'

export default {
  // 删除方法
  del(id) {
    return request({
      url: '/education/del/' + id,
      method: 'delete'
    })
  },
  // 修改方法
  update(education) {
    return request({
      url: '/education/update',
      method: 'put',
      data: education
    })
  },
  // 添加方法
  add(education) {
    return request({
      url: '/education/add',
      method: 'post',
      data: education
    })
  },
  // 查询所有学历不分页
  list() {
    return request({
      // 两种写法（1）'/classify/api/list/'+pageNum+'/'+pageSize,
      // 第二种写法
      // url: `/classify/api/list/${pageNum}/${pageSize}`,
      url: '/education/list',
      method: 'get'
    })
  },
  // 查询所有学历信息分页
  listData(pageIndex, pageSize, education) {
    return request({
      url: `/education/listWhere/${pageIndex}/${pageSize}`,
      method: 'post',
      data: education
    })
  }
}
