import { axios } from '@/utils/request'
export default {
  get(params) {
    params.method = 'get'
    return axios(params)
  },
  post(params) {
    params.method = 'post'
    return axios(params)
  },
  delete(params) {
    params.method = 'delete'
    return axios(params)
  },
  put(params) {
    params.method = 'put'
    return axios(params)
  },
}
