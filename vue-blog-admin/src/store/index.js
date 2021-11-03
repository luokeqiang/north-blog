import { createStore } from 'vuex'
import app from './modules/app'
import tagsView from './modules/tagsView'
import user from './modules/user'
import getters from './getters'
import networkDisk from './modules/networkDisk'

export default createStore({
  state: {
    test: 'Hello world'
  },
  getters,
  mutations: {
  },
  actions: {
  },
  modules: {
    app,
    tagsView,
    user,
    networkDisk
  }
})
