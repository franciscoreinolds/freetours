<template>
    <div>
          

        <!---->

        <v-row>
            <v-navigation-drawer
            permanent
            width = "25%"
            >
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title class="title">
                            Filter by:
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-divider></v-divider>

                <v-list
                    dense
                    nav
                >
                <v-list-item>
                    Date Range:
                </v-list-item>
                <!-- Date Range -->
                <v-date-picker
                    class = "mb-5"
                    v-model="date_range"
                    height = "200px"
                    width = "100%"
                    range
                ></v-date-picker>
                <v-list-item>
                        <v-btn
                        class = "mb-5"
                        title = "Date Range"
                        width = "100%"
                        >
                        From {{dateRangeText}}
                    <!-- Text Field that shows Range -->
                        </v-btn>
                    </v-list-item>
                    <v-list-item>
                        <v-select
                        v-model=category
                        :multiple="multiple"
                        label = "Categories"
                        :items = "categories"
                        item-text = "name"
                        item-value = "id"
                        width = "150px"
                        solo
                        >
                        </v-select>
                    </v-list-item>

                    <!-- Languages Select -->
                    <v-list-item>
                        <v-select
                        v-model="language"
                        :multiple="multiple"
                        label = "Languages"
                        :items = "languages"
                        item-text = "name"
                        width = "150px"
                        solo
                        >
                        </v-select>
                    </v-list-item>
                    
                    <!-- Ratings Range -->
                    <v-list-item>
                        <v-card
                            width = "100%"
                        >
                            <v-card-text>
                                <h3>
                                    Rating
                                </h3>
                                <h4>
                                    From {{rate_range[0]}} until {{rate_range[1]}}
                                </h4>
                            </v-card-text>
                            <v-card-content>
                                <div
                                class = "my_slider"
                                >
                                <v-range-slider
                                    v-model="rate_range"
                                    ticks="always"
                                    step = "0.1"
                                    min = "1"
                                    max = "5"
                               ></v-range-slider> 
                               </div>
                            </v-card-content>
                      </v-card>
                    </v-list-item>
                    <br>
                    <!-- Search Button -->
                    <v-list-item
                    >
                        <v-btn
                            v-on:click="search"
                            class = "search_button"
                        >
                            Search
                        </v-btn>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
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
import CatService from '../services/cat_service';
import LangService from '../services/lang_service';
import TourList from './TourList.vue';
import tour_service from '../services/tour_service';

export default {
    name : "Search",
    components : {
        TourList
    },
    computed: {
        dateRangeText() {
            return this.date_range.join(' until ')
        }
    },
    data: () => ({
        multiple: true,
        date_range : [],
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
        categories : [],
        rate_ticks : [1,2,3,4,5],
        rate_range : [1,5],
        languages : [],
        language:'',
        category: ''
    }),
    async created() {
        var cat_resp = await CatService.get();
        if (cat_resp.status == 200) {
            this.categories = cat_resp.data;
        }
        else console.log('Cat_Response Status not 200')

        var lang_resp = await LangService.get();
        if (lang_resp.status == 200) {
            this.languages = lang_resp.data;
        }
        else console.log('Lang_Response Status not 200')
        
        let filters = {
            'destination': this.$route.params.destination 
        }

        if(this.$route.params.fromDate){
            this.date_range = [this.$route.params.fromDate,this.$route.params.fromDate]
            filters.date_range = this.date_range
        }
        if(this.$route.params.category){
            this.category = [this.categories[this.$route.params.category-1]]
            filters.category = [this.$route.params.category]
        }


        const resp = await tour_service.searchTours(filters)
        this.tours = resp.data
        console.log(this.languages)
        console.log(resp.data)
    },
    methods: {
        async search() {

            let filters = {
                'destination': this.$route.params.destination 
            }

            if(this.date_range){
                filters.date_range = this.date_range
            }
            if(this.category){
                filters.category = this.category
            }

            if(this.language){
                filters.languages = this.language   
            }

            if(this.rate_ticks){
                filters.ratings = this.rate_range
            }

            const resp = await tour_service.searchTours(filters)
            console.log(resp.data)
            this.tours = resp.data
        }
    }
}
</script>

<style>

.container {
    width : 100%;

}

.my_slider {
    width : 85%;
    margin : auto;
}

.search_button {
    margin : auto;
}

</style>
