<template>
    <div>
          <v-parallax
            dark
            src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"
            class = "pt-0 bt-0"
            >
            <v-row
            align="center"
            justify="center"
            >
            <v-col class="text-center" cols="12">
                <h1 class="display-1 font-weight-thin mb-4">Vuetify.js</h1>
                <h4 class="subheading">Build your application today!</h4>
            </v-col>
            </v-row>
        </v-parallax>

        <!---->

        <v-row>
            <v-navigation-drawer
            permanent
            width = "25%"
            >
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title class="title">
                            Sort by
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-divider></v-divider>

                <v-list
                    dense
                    nav
                >
                    <v-list-item
                    v-for="item in items"
                    :key="item.title"
                    link
                    >
                        <v-list-item-content>
                            <v-btn>
                                {{ item.title }}
                            </v-btn>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>

            <!---->
            <v-card
            outlined
            elevation
            class="elevation-12 card"
            >   
                <TourList :tour_data="tours"/>                    
            </v-card>
        </v-row>
    </div>
</template>

<script>
import TourList from './TourList.vue'

export default {
    name : "Search",
    components : {
        TourList
    },
    data: () => ({
        items: [
          { title: 'Dashboard', icon: 'mdi-view-dashboard' },
          { title: 'Photos', icon: 'mdi-image' },
          { title: 'About', icon: 'mdi-help-box' },
        ],
        tours : [
            { text : "Language", value : "English" },
            { text : "Location", value : "Berlin, Germany" },
            { text : "Time and Date", value : "09:00 05/05/2020" },
            { text : "Rating", value : 4.5 },
            { text : "Capacity", value : 50 },
        ],
    })
}
</script>

<style>

.container {
    width : 100%;

}

</style>