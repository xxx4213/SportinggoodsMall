import request from '@/utils/request'


//订单表-查询单条数据
export function ordersone(params) {
  return request({
    url: 'orders/getone',
    method: 'post',
    params: params
  })
}

//订单表-查询列表数据
export function orderslist(params) {
  return request({
    url: 'orders/getlist',
    method: 'post',
    data: params
  })
}

//订单表-删除
export function ordersdel(params) {
  return request({
    url: 'orders/del',
    method: 'post',
    params: params
  })
}

//订单表-保存
export function orderssave(params) {
  return request({
    url: 'orders/save',
    method: 'post',
    data: params
  })
}

//商品分类表-查询单条数据
export function typeone(params) {
  return request({
    url: 'type/getone',
    method: 'post',
    params: params
  })
}

//商品分类表-查询列表数据
export function typelist(params) {
  return request({
    url: 'type/getlist',
    method: 'post',
    data: params
  })
}

//商品分类表-删除
export function typedel(params) {
  return request({
    url: 'type/del',
    method: 'post',
    params: params
  })
}

//商品分类表-保存
export function typesave(params) {
  return request({
    url: 'type/save',
    method: 'post',
    data: params
  })
}
//用户表-查询单条数据
export function userone(params) {
  return request({
    url: 'user/getone',
    method: 'post',
    params: params
  })
}

//用户表-查询列表数据
export function userlist(params) {
  return request({
    url: 'user/getlist',
    method: 'post',
    data: params
  })
}

//用户表-删除
export function userdel(params) {
  return request({
    url: 'user/del',
    method: 'post',
    params: params
  })
}

//用户表-保存
export function usersave(params) {
  return request({
    url: 'user/save',
    method: 'post',
    data: params
  })
}
//收货地址表-查询单条数据
export function addrsone(params) {
  return request({
    url: 'addrs/getone',
    method: 'post',
    params: params
  })
}

//收货地址表-查询列表数据
export function addrslist(params) {
  return request({
    url: 'addrs/getlist',
    method: 'post',
    data: params
  })
}

//收货地址表-删除
export function addrsdel(params) {
  return request({
    url: 'addrs/del',
    method: 'post',
    params: params
  })
}

//收货地址表-保存
export function addrssave(params) {
  return request({
    url: 'addrs/save',
    method: 'post',
    data: params
  })
}
//评论表-查询单条数据
export function commitone(params) {
  return request({
    url: 'commit/getone',
    method: 'post',
    params: params
  })
}

//评论表-查询列表数据
export function commitlist(params) {
  return request({
    url: 'commit/getlist',
    method: 'post',
    data: params
  })
}

//评论表-删除
export function commitdel(params) {
  return request({
    url: 'commit/del',
    method: 'post',
    params: params
  })
}

//评论表-保存
export function commitsave(params) {
  return request({
    url: 'commit/save',
    method: 'post',
    data: params
  })
}
//商品表-查询单条数据
export function goodsone(params) {
  return request({
    url: 'goods/getone',
    method: 'post',
    params: params
  })
}

//商品表-查询列表数据
export function goodslist(params) {
  return request({
    url: 'goods/getlist',
    method: 'post',
    data: params
  })
}

//商品表-删除
export function goodsdel(params) {
  return request({
    url: 'goods/del',
    method: 'post',
    params: params
  })
}

//商品表-保存
export function goodssave(params) {
  return request({
    url: 'goods/save',
    method: 'post',
    data: params
  })
}

//设置所有收货地址为非默认
export function allnodefault(params) {
  return request({
    url: 'addrs/allnodefault',
    method: 'post',
    params: params
  })
}


//商品表-设置发货状态
export function updatestatus(params) {
  return request({
    url: 'orders/updatestatus',
    method: 'post',
    data: params
  })
}

