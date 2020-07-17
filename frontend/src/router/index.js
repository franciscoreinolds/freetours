import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import SignUp from '../components/SignUp.vue'
import Profile from '../components/Profile.vue'
import Search from '../components/Search.vue'
import CreateTour from '../components/CreateTour.vue'
import Tour from '../components/Tour.vue'
import User from '../models/user'
import ForgotPassword from '../components/ForgotPassword.vue'
import ChangePassword from '../components/ChangePassword.vue'
import Review from '../components/Review'

Vue.use(VueRouter)

const routes = [
  {
    name : "Home",
    path : "/",
    component : Home
  },
  {
    name : "Sign Up",
    path : "/signup",
    component : SignUp
  },
  {
    name : "Profile",
    path : "/profile/:username",
    component : Profile
  },
  {
    name : "Search",
    path : "/search/:destination?/:fromDate?/:category?",
    component : Search
  },
  {
    name : "CreateTour",
    path : "/createtour",
    component : CreateTour
  },
  {
    name : "Tour",
    path : "/tour/:id",
    component : Tour
  },
  {
    name : "ForgotPassword",
    path : "/forgotpassword",
    component : ForgotPassword
  },
  {
    name : "ChangePassword",
    path : "/changepassword/:token",
    component : ChangePassword
  },
  {
    name : "Review",
    path : "/review/:token",
    component : Review
  }
]

const router = new VueRouter({
  routes
})

export default router
