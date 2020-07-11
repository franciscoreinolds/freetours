<template>
  <v-app>
    <Header
    class = "header"
    />
    <router-view
    class = "router"
    >
    </router-view>
    <Footer
    class = "footer"
    />
  </v-app>
</template>

<script>
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';

export default {
  name: 'App',

  components: {
    Footer,
    Header,
  },

  data: () => ({
    //
  }),
  async created(){
    const jwt = localStorage.getItem('user', jwt);
    if(jwt != ''){
      var parsedJwt =  JSON.parse(atob(jwt.split('.')[1]));
      store.commit('setUsername', parsedJwt.sub);
    }   
  }
};
</script>

<style scoped>

.header {
  height : 70px;
}

.router { 
  height : 100%;
  width : 85%;
  margin : auto;
  padding : 25px 0px;
}

.footer {
  height : 70px;
}

</style>