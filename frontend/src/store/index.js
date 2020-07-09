import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username : ''
  },
  mutations: {
      setUsername(state, u) {
        state.username = u;
      }
  },
  actions: {
  },
  modules: {
  }
})
