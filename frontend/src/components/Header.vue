<template>
    <div>
        <v-app-bar
        class = "navbar"
        dense
        dark
        height = 70px
        max-height = 70px
        >
        <router-link
        :to = "{path : '/'}"
        class = "router-link"
        >
            <v-toolbar-title
            class = "toolbar-title"
            >       
                FreeTours  
            </v-toolbar-title>  
        </router-link> 
        <v-spacer></v-spacer>
        <v-text-field
            class = "text-field"
            label="Destination"
            outlined
            prop
            :append-icon="'mdi-map-marker'"
        >
        </v-text-field>
        <v-spacer></v-spacer>
        <div
        v-if="this.user==''"
        >
        <router-link
        :to = "{path : '/signup'}"
        class = "router-link"
        >
            <v-btn
            text
            >
                Sign Up
            </v-btn>
        </router-link>
        <Login
            class = "login"
        />
        </div>
        <div
        v-else
        >
        <router-link
        :to = "{path : '/createtour'}"
        class = "router-link"
        >
            <v-btn
            text
            >
                Add New Tour
            </v-btn>
        </router-link>
        <router-link
        :to = "{path : '/profile'}"
        class = "router-link"
        >
            <v-btn
            text
            >
                {{ user }}
            </v-btn>
        </router-link>
        <v-btn
        text
        v-on:click="logout()"
        >
            Logout
        </v-btn>
        </div>
        </v-app-bar>
    </div>
</template>

<script>
import AuthService from '../services/auth_service';
import Login from "./Login";

export default {
    name : "Header",
    components: {Login},
    computed : {
        user : function() {
            return this.$store.state.username;
        }
    },
    async created () {
    },
    data: () => ({
        links : [
            {
                text : "FreeTours",
                link : "/"
            }
        ]
    }),
    methods : { 
        logout: async function() {
            AuthService.logout();
            this.$router.push('/');
        }
    }
}
</script>

<style scoped>

.text-field {
  height : 60px;
  width : 50px
}

.router-link {
    text-decoration : none;
}

.toolbar-title {
    color : #ffffff;
}

</style>
