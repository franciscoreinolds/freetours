import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    name : "Home",
    path : "/",
    component : Home
  },
  {
    name : "Login",
    path : "/login",
    component : Login
  }
]

const router = new VueRouter({
  routes
})

export default router
