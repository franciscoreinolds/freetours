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
                            :src="`${slide.image}`"
                            >
                            </v-carousel-item>
                        </v-carousel>
                        <v-row>
                            <v-col
                            :cols = 8
                            >
                                <v-card-text>
                                    <v-row>
                                        <v-col>
                                            <h2>
                                                Location: {{tour.city.name}}
                                            </h2>
                                            <h2>
                                                Duration: {{tour.duration}}
                                            </h2>
                                            <h2>
                                                Guide: 
                                                <router-link :to="{ name: 'Profile', params: { username: tour.guide.username }}">
                                                {{tour.guide.username}}
                                                </router-link>
                                            </h2>
                                            <h2>
                                                Category: {{tour.category.name}}
                                            </h2>
                                            <h2>
                                                Capacity: {{tour.minCapacity}} - {{tour.maxCapacity}} people
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
                                        </v-col>
                                        <v-col :cols = 4>
                                            <v-btn
                                                    small
                                                    primary
                                                    v-on:click="qrdisplay()"
                                            >
                                                <span v-if="!this.displayed"> Display QR Code </span>
                                                <span v-else> Hide QR Code </span>
                                            </v-btn>
                                            <div
                                                v-if="this.displayed"
                                            >
                                                <v-img
                                                        v-bind:src="tour.qrCode"
                                                        aspect-ratio = 1
                                                        max-height = "150px"
                                                        max-width = "150px"
                                                >
                                                </v-img>
                                                <span>
                                                    Use this QR Code to advertise this tour.
                                                </span>
                                            </div>
                                        </v-col>
                                    </v-row>
                                </v-card-text>
                            </v-col>
                            <v-col
                            :cols = 4
                            >
                                <div style="margin-bottom: 5px;">
                                <v-layout justify-center>
                                    <v-dialog v-model="dialog" max-width="600px" style="display: inline;">
                                        <template v-slot:activator="{ on, attrs }">
                                            <div
                                            v-bind="attrs"
                                            v-on="on"
                                            v-if="isGuide()"
                                            >
                                            <div class="text-center my-2">
                                                <v-btn 
                                                x-large
                                                >
                                                Add Schedule
                                                </v-btn>
                                            </div>
                                            </div>
                                        </template>
                                        <div
                                        v-if="isGuide()"
                                        >
                                        <CreateSchedule :id="tour.id"/>
                                        </div>
                                    </v-dialog>
                                </v-layout>
                                </div>
                                <v-list
                                    style="max-height: 500px;"
                                    class="overflow-y-auto"
                                >
                                    <div 
                                    v-if="tour.active.length != 0">
                                    <v-list-item
                                    v-for="(schedulling, index) in tour.active"
                                    :key="index"
                                    class = "pb-1 ps-1 pe-1 pt-0"
                                    >
                                        <v-list-item-content
                                        class = "pa-0"
                                        >
                                            
                                            <v-layout justify-center>
                                                <v-dialog v-model="dialog" max-width="600px">
                                                    <template v-slot:activator="{ on, attrs }">
                                                        <v-card
                                                        color="#fafafa"
                                                        width = "100%"
                                                        v-bind="attrs"
                                                        v-on="on"
                                                        >
                                                            <v-card-text>
                                                                <h3>
                                                                    Date: {{schedulling.date.slice(0,10)}}
                                                                </h3>
                                                                <h4>
                                                                    Starting Time: {{parseInt(schedulling.date.slice(11 ,13))+1}}:{{schedulling.date.slice(14 ,16)}}
                                                                </h4>
                                                                <h4>
                                                                    Number of people going: {{schedulling.signees.length}}
                                                                </h4>
                                                                <div
                                                                v-if="!isGuide() && schedulling.signees.length == tour.maxCapacity"
                                                                >
                                                                <h2 class="text-center orange font-weight-bold">WAITING QUEUE</h2>
                                                                </div>
                                                                <div
                                                                v-else-if="!isGuide() && include(schedulling.signees) == true"
                                                                >
                                                                <h2 class="text-center green font-weight-bold">SUBSCRIBED</h2>
                                                                </div>
                                                                <div
                                                                v-else-if="!isGuide() && include(schedulling.signees) == false"
                                                                >
                                                                <h2 class="text-center yellow font-weight-bold">SUBSCRIBE</h2>
                                                                </div>
                                                            </v-card-text>
                                                        </v-card>
                                                    </template>
                                                    <div
                                                    v-if="!isGuide()"
                                                    >
                                                    <TourSignIn :id="schedulling.id" :signeesSize="schedulling.signees.length" :maxCapacity="tour.maxCapacity"/>
                                                    </div>
                                                </v-dialog>
                                            </v-layout>
                                            <v-card-text>
                                                <div
                                                v-if="!isGuide() && include(schedulling.signees) == true"
                                                >
                                                    <div class="text-center my-2">
                                                        <v-btn 
                                                        small
                                                        v-on:click="tourSignOut(schedulling.id)"
                                                        >
                                                        Unsubscribe
                                                        </v-btn>
                                                    </div>            
                                                </div>
                                                <div
                                                v-else-if="isGuide()"
                                                >
                                                    <div class="text-center my-2">
                                                        <v-btn 
                                                        small
                                                        v-on:click="tourSignOut(schedulling.id)"
                                                        >
                                                        Cancel Schedule
                                                        </v-btn>
                                                    </div>
                                                </div>
                                            </v-card-text>
                                        </v-list-item-content>
                                    </v-list-item>
                                    </div>
                                    <div v-if="!isGuide() && tour.active.length == 0">
                                        <h2 class="text-center red">No Schedulings</h2>
                                    </div>
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
                                            style="z-index: 1;"
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
import TourSignIn from './TourSignIn'
import store from '../store'
import CreateSchedule from './CreateSchedule'
import ScheduleSignOut from '../services/schedule_signout'

const FRONTEND_URL = process.env.VUE_APP_FRONTEND_URL

export default {
    name : "Tour",
    components : {
        TourList,
        TourSignIn,
        CreateSchedule
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
            },
            displayed: false,
        }
    },
    methods: {
        async doStuff() {
            const resp = await TourService.getTour(this.$route.params.id)
            this.tour = resp.data.tour

            var img
            for(img of this.tour.images){
                img.image = FRONTEND_URL + '/images/' + img.image
            }

            this.moreTours = resp.data.moreToursBy
            //console.log(resp.data.moreToursBy)
            //console.log(this.$route.params.id)
        },
        qrdisplay: function () {
            this.displayed = !this.displayed;
        },
        include: function(signees){
            for(var i in signees){
                //console.log("Signee: " + signees[i].username)
                //console.log("Username: " + store.state.username)
                if(signees[i].username == store.state.username) return true;
            }
            return false;
        },
        isGuide: function(){
            //console.log("Tour username: " + this.tour.guide.username)
            //console.log("State username: " + store.state.username)
            if(this.tour.guide.username == store.state.username) return true;
            else return false;
        },
        tourSignOut: async function (id) {
          const resp = await ScheduleSignOut.scheduleSignOut(id)
          //console.log(resp)
          this.$router.go(0)
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
