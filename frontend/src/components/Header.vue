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
                Username
            </v-btn>
        </router-link>
        <v-btn
        text
        >
            Logout
        </v-btn>
        </v-app-bar>
    </div>
</template>

<script>
export default {
    name : "Header",
    data: () => ({
        links : [
            {
                text : "FreeTours",
                link : "/"
            }
        ]
    })
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