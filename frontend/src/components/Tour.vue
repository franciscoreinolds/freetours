<template>
    <div>
        <v-main>
            <v-container
                class="fill-height"
                fluid
            >
                <v-row
                align="center"
                justify="center"
                >
                    <v-card
                    shaped
                    elevation
                    min-width = "400px"
                    class="elevation-12 card"
                    >
                        <h1
                        class = "pa-5"
                        >
                            {{tour.name}}
                        </h1>
                        <v-carousel
                        cycle
                        show-arrows-on-hover
                        height = "300px"
                        class = "carousel"
                        >
                            <v-carousel-item
                            v-for="(slide, i) in tour.images"
                            :key="i"  
                            :src="require(`@/assets/${slide.image}`)" 
                            >
                            </v-carousel-item>
                        </v-carousel>
                        <v-row>
                            <v-col
                            :cols = 8
                            >
                                <v-card-text>
                                    <h2>
                                        Location: {{tour.city.name}}
                                    </h2>
                                    <h2>
                                        Duration: {{tour.duration}}
                                    </h2>
                                    <h2>
                                        Capacity: 50 people
                                    </h2>
                                    <h2>
                                        Languages: {{tour.languages.map( l => l.abbreviation).join()}}
                                    </h2>
                                    <h2>
                                        Description:
                                        {{tour.description}}                                    
                                    </h2>
                                    <h2>
                                        Route:
                                    </h2>
                                </v-card-text>
                            </v-col>
                            <v-col
                            :cols = 4
                            >
                                <v-list
                                    style="max-height: 500px;"
                                    class="overflow-y-auto"
                                >
                                    <v-list-item
                                    v-for="(schedulling, index) in tour.active"
                                    :key="index"
                                    class = "pb-1 ps-1 pe-1 pt-0"
                                    >
                                        <v-list-item-content
                                        class = "pa-0"
                                        >
                                            <v-card
                                            color="#fafafa"
                                            width = "100%"
                                            >
                                                <v-card-text>
                                                    <h3>
                                                        Date: {{schedulling.date.slice(0,10)}}
                                                    </h3>
                                                    <h4>
                                                        Starting Time: {{schedulling.date.slice(11  ,16)}}
                                                    </h4>
                                                    <h4>
                                                        Number of people going: {{schedulling.signees.length}}
                                                    </h4>
                                                </v-card-text>
                                            </v-card>
                                        </v-list-item-content>
                                   </v-list-item>
                                </v-list>
                            </v-col>
                        </v-row>
                        <v-layout
                        wrap
                        justify-center>
                            <v-flex
                            xs8
                            >
                                <!--
                                    Main (Map) Card
                                -->
                                <v-card
                                height = "500px"
                                >
                                    <div
                                    class = "mapHolder"
                                    >
                                        <l-map
                                            ref="myMap"
                                            :zoom="15"
                                            :center="[tour.route[0].latitude, tour.route[0].longitude]"
                                            :options="{
                                                doubleClickZoom:false,
                                                zoomControl : false
                                            }"
                                            >
                                            <l-tile-layer :url="url">
                                            </l-tile-layer>
                                            <!--
                                            <v-geosearch
                                            :options="geosearchOptions"
                                            >
                                            </v-geosearch>
                                            -->
                                            <l-marker
                                                v-for="(place,index) in tour.route"
                                                :key="index"
                                                :lat-lng="[place.latitude, place.longitude]"
                                            >
                                                <l-icon
                                                :icon-size="[37,37] "
                                                :icon-anchor="[16, 37]"
                                                :iconUrl="require('leaflet/dist/images/marker-icon.png')"
                                                >
                                                </l-icon>
                                            </l-marker>
                                        </l-map>
                                    </div>
                                </v-card>
                            </v-flex>
                            <v-flex
                            xs4
                            >
                                <v-list
                                style="max-height: 500px;"
                                class="overflow-y-auto"
                                >
                                    <v-list-item
                                    v-for="(place, index) in tour.route"
                                    :key="index"
                                    class = "pb-1 ps-1 pe-1 pt-0"
                                    >
                                        <v-list-item-content
                                        class = "pa-0"
                                        >
                                            <v-card
                                            color="#fafafa"
                                            width = "100%"
                                            >
                                                <v-card-text>
                                                    <h3>
                                                        Stop #{{index + 1}}
                                                    </h3>
                                                    <h4>
                                                        Marker Name: {{place.name}}
                                                    </h4>   
                                                </v-card-text>
                                            </v-card>
                                        </v-list-item-content>
                                   </v-list-item>
                                </v-list>
                            </v-flex>
                        </v-layout>
                        <br>
                        <!--
                        <v-card-actions>
                            <v-layout justify-center>
                                <v-btn
                                large
                                primary
                                >
                                    I want to go!
                                </v-btn>
                            </v-layout>
                        </v-card-actions>
                        -->
                        <v-card-text>
                            <!--
                                Comments
                            -->
                            <v-layout>
                                <v-flex
                                xs3
                                >
                                    <h2>
                                        Comments:
                                    </h2>
                                </v-flex>
                                <v-flex
                                xs9
                                >
                                    <v-list
                                    style="max-height: 500px;"
                                    class="overflow-y-auto"
                                    >
                                        <v-list-item
                                        v-for="(review, index) in tour.reviews"
                                        :key="index"
                                        >
                                            <v-list-item-content>
                                                <v-card
                                                outlined
                                                elevation
                                                color = "#fafafa"
                                                class="tour_card mb-5 elevation-12"
                                                >   
                                                    <v-card
                                                    >
                                                        <v-card-title>
                                                        <span class="title font-weight-light">Comment</span>
                                                        </v-card-title>

                                                        <v-card-text class="headline font-weight-bold">
                                                            {{review.comment}}
                                                        </v-card-text>

                                                        <v-card-actions>
                                                        <v-list-item class="grow">
                                                            <v-list-item-avatar color="grey darken-3">
                                                            <v-img
                                                                class="elevation-6"
                                                                src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                                                            ></v-img>
                                                            </v-list-item-avatar>

                                                            <v-list-item-content>
                                                            <v-list-item-title v-on:click="$router.push('/profile/' + review.user.username)">{{review.user.username}}</v-list-item-title>
                                                            </v-list-item-content>

                                    
                                                        </v-list-item>
                                                        </v-card-actions>
                                                    </v-card>                 
                                                </v-card>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-list>
                                </v-flex>
                            </v-layout>
                            <br>
                            <v-layout>
                                <v-flex
                                xs3
                                >
                                    <h2>
                                        More Tours By:
                                    </h2>
                                    <br>
                                </v-flex>
                                <v-flex
                                xs9
                                >
                                    <v-card
                                    outlined
                                    elevation
                                    class="elevation-12"
                                    >   
                                        <TourList :tour_data="moreTours" />                    
                                    </v-card>
                                </v-flex>
                            </v-layout>
                        </v-card-text>
                        <br>
                    </v-card>
                </v-row>
            </v-container>
        </v-main>
    </div>
</template>

<script>
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import TourList from './TourList.vue';
import TourService from './../services/tour_service'

export default {
    name : "Tour",
    components : {
        TourList
    },
    data() {
        return {
            tour: null,
            moreTours: null,
            url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
            bounds: null,
            geosearchOptions: { // Important part Here
                provider: new OpenStreetMapProvider(),
                style : 'bar',
                autoComplete: true,
                autoCompleteDelay : 250,
                maxMarkers : 0,
                autoClose : true
            }
        }
    },
    methods: {
        async doStuff() {
        const resp = await TourService.getTour(this.$route.params.id)
        this.tour = resp.data.tour
        this.moreTours = resp.data.moreToursBy
        console.log(resp.data.moreToursBy)
        console.log(this.$route.params.id)
         
        }
    },
    watch: {
        '$route.params.id': async function (id) {
            await this.doStuff()
        }
    },
    async created(){
        await this.doStuff()
        
    }
}
</script>

<style>

h2 {
    padding : 15px;
}

</style>
