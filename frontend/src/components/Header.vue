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
        <v-autocomplete
            v-model = "sel_destination"
            :items = "all_destinations"
            item-text="name"
            item-value="name"
            label = "Destination"
            outlined
            class = "my_autocomplete"
            :append-icon="'mdi-map-marker'"
            @change="destination()"
        >
        </v-autocomplete>
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
        :to = "{path : '/profile/' + this.user}"
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
import store from "../store";

export default {
    name : "Header",
    components: {Login},
    computed : {
        user : function() {
            return this.$store.state.username;
        }
    },
    data: () => ({
        all_destinations : store.state.cities,
        /*all_destinations : [
            'Amsterdam, Netherlands',
            'Paris, France',
            'Lisbon, Portugal',
            'Porto, Portugal',
            'Faro, Portugal',
            'Braga, Portugal',
            'Guimarães, Portugal',
            'Famalicão, Portugal',
            'Funchal, Portugal'
        ],*/
        sel_destination : "",
        rules : {
            required: value => !!value || 'Required field.',
        },
        links : [
            {
                text : "FreeTours",
                link : "/"
            }
        ]
    }),
    methods : { 
        destination: async function() {
            if(this.sel_destination != ""){
                // Search
                this.$router.push('/search/' + this.sel_destination);
            }
        },
        logout: async function() {
            AuthService.logout();
            this.$router.push('/');
        }
    }
}
</script>

<style scoped>

.my_autocomplete {
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
