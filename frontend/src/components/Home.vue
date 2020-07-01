<template>
    <div>
        <div
        >
            <v-carousel
            cycle
            show-arrows-on-hover
            height = "400px"
            class = "carousel"
            >
                <v-carousel-item
                v-for="(slide, i) in slides"
                :key="i"
                >
                    <v-parallax
                        :src="require(`@/assets/${slide.image.image}`)"   
                    >
                        <v-row
                        align="center"
                        justify="center"
                        >
                       
                        <h1 class="display-1 font-weight-thin mb-4">{{slide.name}}</h1>
                        </v-row>
                    </v-parallax>
                </v-carousel-item>
            </v-carousel>
        </div>
        <v-row
        class = "filter_bar"
        width = 50%
        >
            <v-col
            :cols = 6
            >
                <v-text-field
                    class = "text-field"
                    label="Destination"
                    outlined
                    prop
                    :append-icon="'mdi-map-marker'"
                >
                </v-text-field>
            </v-col>
            <v-col
            :cols = 2
            >
                <v-menu
                    v-model="menu2"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                >
                    <template v-slot:activator="{ on }">
                    <v-text-field
                        v-model="date"
                        label="Tour Date"
                        :prepend-icon="'mdi-calendar'"
                        readonly
                        v-on="on"
                    ></v-text-field>
                    </template>
                    <v-date-picker v-model="date" @input="menu2 = false"></v-date-picker>
                </v-menu>
            </v-col>
            <v-col
            :cols = 2
            >
                <v-select
                v-model = "category"
                label = "Tour Category"
                :items = "categories"
                item-text="name"
                item-value="id"
                offset-y
                solo
                >
                </v-select>
            </v-col>
            <v-col
            :cols = 1
            >
                <router-link
                :to = "{path : '/search'}"
                class = "router-link"
                >
                    <v-btn
                    class = "btn"
                    >
                        Search Tour
                    </v-btn>
                </router-link>
            </v-col>
        </v-row>

        <h2 v-if="user.username == ''">Where will your next Tour be?</h2>
        <h2 v-else>Next tours:</h2>
        
        <!-- Cards -->
        <v-carousel
        hide-delimiters
        cycle
        show-arrows-on-hover
        height = "600px"
        class = "carousel"
        >
            <v-carousel-item 
            v-for="i in 3" 
            :key="i"
            class = "carousel_item"
            >
                <v-layout row>
                    <v-flex sm4 v-for="j in 3" :key="j" pl-2 pr-2>
                        <v-card
                        class = "card"
                        width = "100%"
                        >
                            <v-card-title
                            primary-title
                            class = "align-center"
                            >
                                <div>
                                    <h3 class="headline mb-0">{{tours[i-1].city.name}}</h3>
                                </div>
                            </v-card-title>
                        
                            <v-img
                            src="@/assets/Barcelona.jpeg"
                            ></v-img>
                            
                            <v-card-subtitle>
                            </v-card-subtitle>
                            <v-card-text>
                                {{tours[i-1].description}}
                            </v-card-text>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-carousel-item>
        </v-carousel>
        <v-layout justify-center>
            <v-card-actions>
                <v-btn primary>
                    More Tours
                </v-btn>
            </v-card-actions>
        </v-layout>
    </div>
</template>

<script>
import CatService from '../services/cat_service'
import HomeService from '../services/home_service'
import HomeModel from '../models/home_model'
import User from '../models/user'

export default {
    name : "Home",
    data() {
        return {
            colors: [
            'indigo',
            'warning',
            'pink darken-2',
            'red lighten-1',
            'deep-purple accent-4',
            ],
            slides : [],
            tours : [],
            date: new Date().toISOString().substr(0, 10),
            categories : [],
            category : '',
            menu2: false,
            user: new User('','','')
        }
    },
    async created() {
        var cat_resp = await CatService.get();
        if (cat_resp.status == 200) {
            this.categories = cat_resp.data;
        }
        else console.log('Cat_Response Status not 200')


        var home_response = await HomeService.getHome();
        this.slides = home_response.data.mostPopularCities
        this.tours = home_response.data.suggestedTours
        console.log(this.$props)
    }
}
</script

<style scoped>

h2 {
    margin-bottom: 25px;
}

.carousel {
    margin-bottom : 25px;
}

.card { 
    height : 600px;
}

.btn {
    margin-top : 5px;
}

.filter_bar {
    margin : auto;
}

.carousel_item {
    width : 90%;
    max-width : 90%;
    height : 600px;
    max-height : 600px;
    margin : auto;
}

</style>
