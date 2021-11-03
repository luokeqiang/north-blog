import { axios } from '@/utils/request'

export function login(param) {
  return axios({
    url: '/auth/login',
    method: 'post',
    data: param
  })
}

export function getInfo(token) {
  return axios({
    url: process.env.ADMIN_API + '/auth/info',
    method: 'get',
    params: { token }
  })
}

export function getMenu() {
  return axios({
    url: process.env.ADMIN_API + '/auth/getMenu',
    method: 'get'
  })
}

export function logout() {
  return axios({
    url: process.env.ADMIN_API + '/auth/logout',
    method: 'post'
  })
}

export function getWebSiteName() {
  return axios({
    url: process.env.ADMIN_API + '/auth/getWebSiteName',
    method: 'get'
  })
}
