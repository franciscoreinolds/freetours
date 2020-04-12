<template>
    <div>
        <v-content>
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
                            Tour name
                        </h1>
                        <v-carousel
                        cycle
                        show-arrows-on-hover
                        height = "300px"
                        class = "carousel"
                        >
                            <v-carousel-item
                            v-for="(slide, i) in slides"
                            :key="i"
                            >
                                <v-sheet
                                    :color="colors[i]"
                                    height="100%"
                                >
                                    <v-row
                                    class="fill-height"
                                    align="center"
                                    justify="center"
                                    >
                                    <div class="display-3">{{ slide }} Slide</div>
                                    </v-row>
                                </v-sheet>
                            </v-carousel-item>
                        </v-carousel>
                        <v-card-text>
                            <h2>
                                Location: Berlin, Germany
                            </h2>
                            <h2>
                                Starting Time: 09:30 05/05/2020
                            </h2>
                            <h2>
                                Duration: 03h30m
                            </h2>
                            <h2>
                                Capacity: 50 people
                            </h2>
                            <h2>
                                Languages: FR EN
                            </h2>
                            <h2>
                                Description: 
                            </h2>
                            <h2>
                                Route:
                            </h2>
                        </v-card-text>
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
                                            :zoom="zoom"
                                            :center="center"
                                            :zoomControl = "disabled"
                                            :options="{
                                                doubleClickZoom:false,
                                                zoomControl : false
                                            }"
                                            >
                                            <l-tile-layer :url="url">
                                            </l-tile-layer>
                                            <v-geosearch
                                            :options="geosearchOptions"
                                            >
                                            </v-geosearch>
                                            <l-marker
                                                v-for="(marker,index) in markers"
                                                :key="index"
                                                :lat-lng="[marker.position.lattitude, marker.position.longitude]"
                                            >
                                                <l-icon
                                                :icon-size="marker.icon_size"
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
                                <v-card
                                height = "500px"
                                width = "100%"
                                >
                                    <v-list
                                    style="max-height: 500px;"
                                    class="overflow-y-auto"
                                    >
                                        <v-list-item
                                        v-for="(marker, index) in markers"
                                        :key="index"
                                        class = "pb-1 ps-1 pe-1 pt-0"
                                        >
                                            <v-list-item-content
                                            class = "pa-0"
                                            >
                                                <v-card
                                                color="#ffeeee"
                                                width = "100%"
                                                @mouseover="mouseOverMarker(index)"
                                                @mouseleave="mouseLeavesMarker(index)"
                                                >
                                                    <v-card-text>
                                                        <h3>
                                                            Stop #{{index + 1}}
                                                        </h3>
                                                        <h4>
                                                            Marker Name: {{marker.name}}
                                                        </h4>
                                                        <h4>
                                                            Marker Description: {{marker.description}}
                                                        </h4>
                                                    </v-card-text>
                                                </v-card>
                                            </v-list-item-content>
                                       </v-list-item>
                                    </v-list>
                                </v-card>
                            </v-flex>
                        </v-layout>
                        <br>
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
                                        v-for="(comment, index) in comments"
                                        :key="index"
                                        >
                                            <v-list-item-content>
                                                <v-card
                                                height = "100px"
                                                >
                                                    Comment
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
                                        <TourList :tour_data="tours"/>                    
                                    </v-card>
                                </v-flex>
                            </v-layout>
                        </v-card-text>
                        <br>
                        <v-card-actions>
                            <v-layout justify-center>
                                <v-btn
                                large
                                primary
                                >
                                    More Tours by:
                                </v-btn>
                            </v-layout>
                        </v-card-actions>
                        <br>
                    </v-card>
                </v-row>
            </v-container>
        </v-content>
    </div>
</template>

<script>
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import TourList from './TourList.vue';

export default {
    name : "Tour",
    components : {
        TourList
    },
    data() {
        return {
            colors: [
            'indigo',
            'warning',
            'pink darken-2',
            'red lighten-1',
            'deep-purple accent-4',
            ],
            slides : [
            'First',
            'Second',
            'Third',
            'Fourth',
            'Fifth',
            ],
            markers : [
                {
                    name : "Aeroporto Lisboa",
                    description : "Aeroporto Desc",
                    position : {
                        lattitude : 38.77008,
                        longitude: -9.12612
                    },
                    icon_size : [37,37]
                },
                {
                    name : "Areeiro",
                    description : "Areeiro Desc",
                    position : {
                        lattitude : 38.7424,
                        longitude: -9.1336
                    },
                    icon_size : [37,37]
                },
                {
                    name : "Alameda",
                    description : "Alameda Desc",
                    position : {
                        lattitude : 38.7373,
                        longitude: -9.1337
                    },
                    icon_size : [37,37]
                },
                {
                    name : "Saldanha",
                    description : "Saldanha Desc",
                    position : {
                        lattitude : 38.7352,
                        longitude: -9.1440
                    },
                    icon_size : [37,37]
                },
                {
                    name : "Campo Pequeno",
                    description : "Campo Pequeno Desc",
                    position : {
                        lattitude : 38.7411,
                        longitude: -9.1470
                    },
                    icon_size : [37,37]
                },
            ],
            url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
            zoom: 7,
            center: [38.7411, -9.1470],
            bounds: null,
            geosearchOptions: { // Important part Here
                provider: new OpenStreetMapProvider(),
                style : 'bar',
                autoComplete: true,
                autoCompleteDelay : 250,
                maxMarkers : 0,
                autoClose : true
            },
            comments : [
                {
                    comment : "asdasdas"
                },
                {
                    comment : "asdasdas"
                },
                {
                    comment : "asdasdas"
                },
                {
                    comment : "asdasdas"
                },
                {
                    comment : "asdasdas"
                },
                {
                    comment : "asdasdas"
                },
            ],
            tours : [
                {
                    text : "Language",
                    value : "English"
                },
                {
                    text : "Location",
                    value : "Berlin, Germany"
                },
                {
                    text : "Time and Date",
                    value : "09:00 05/05/2020"
                },
                {
                    text : "Rating",
                    value : 4.5
                },
                {
                    text : "Capacity",
                    value : 50
                },
            ],
        }
    },
    mounted: {
    },
    methods: {
        mouseOverMarker(index) {
            this.markers[index].icon_size = [50,50];
        },
        mouseLeavesMarker(index) {
            this.markers[index].icon_size = [37,37];
        }
    }
}
</script>

<style>

h2 {
    padding : 15px;
}

</style>