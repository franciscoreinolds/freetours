import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue'
import SignUp from '../components/SignUp.vue'
import Profile from '../components/Profile.vue'
import Search from '../components/Search.vue'
import CreateTour from '../components/CreateTour.vue'
import Tour from '../components/Tour.vue'

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
  },
  {
    name : "Sign Up",
    path : "/signup",
    component : SignUp
  },
  {
    name : "Profile",
    path : "/profile",
    component : Profile
  },
  {
    name : "Search",
    path : "/search",
    component : Search
  },
  {
    name : "CreateTour",
    path : "/createtour",
    component : CreateTour
  },
  {
    name : "Tour",
    path : "/tour",
    component : Tour
  }
]

const router = new VueRouter({
  routes
})

export default router
