import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username : '',
    cities: []
  },
  mutations: {
      setUsername(state, u) {
        state.username = u;
      },
      setCities(state, cities){
        state.cities = cities;
      }
  },
  actions: {
  },
  modules: {
  }
})
