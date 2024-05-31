import request from '@/utils/request'
// export default {
//   list(){
//     return request({
//       url: '/family/list',
//       method: 'get'
//     })
//   }
// }
export default {

  // 删除方法
  del(id) {
    return request({
      url: '/family/del/' + id,
      method: 'delete'
    })
  },
  // 修改方法
  update(family) {
    return request({
      url: '/family/update',
      method: 'put',
      data: family
    })
  },
  // 添加方法
  add(family) {
    return request({
      url: '/family/add',
      method: 'post',
      data: family
    })
  },
  // 查询所有家庭不分页
  list() {
    return request({
      // 两种写法（1）'/classify/api/list/'+pageNum+'/'+pageSize,
      // 第二种写法
      // url: `/classify/api/list/${pageNum}/${pageSize}`,
      url: '/family/list',
      method: 'get'
    })
  },
  // 查询所有家庭不分页
  listData(pageIndex, pageSize, family) {
    return request({
      // 两种写法（1）'/classify/api/list/'+pageNum+'/'+pageSize,
      // 第二种写法
      // url: `/classify/api/list/${pageNum}/${pageSize}`,
      // url: '/family/list',
      url: `/family/listWhere/${pageIndex}/${pageSize}`,
      method: 'post',
      data: family
    })
  }
}
