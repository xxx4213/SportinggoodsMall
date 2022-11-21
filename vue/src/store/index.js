import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import user from './modules/user'
import car from './modules/car'
import app from './modules/app'
import settings from './modules/settings'


Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    car,
    user,
    app,
    settings
  },
  getters
})

export default store
