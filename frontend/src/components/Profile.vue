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
                    class="elevation-12 card"
                    >
                        <h1
                        class = "pa-5"
                        >
                            My Profile
                        </h1>
                        <v-card-text>
                            <v-row>
                                <v-col>
                                    Username: {{ this.username }}
                                </v-col>
                                <v-col>
                                    Email: {{ this.email }}
                                </v-col>
                                <v-col>
                                    Date of Birth: {{ this.dateOfBirth }}
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    Phone Number: {{ this.phoneNumber }}
                                </v-col>
                                <v-col>
                                    Rating: {{ this.rating }}
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col
                                :cols = 8
                                >
                                    Languages:
                                    <span v-for="language in this.spoken_languages">
                                        {{ language.abbreviation }} ||
                                    </span>
                                </v-col>
                                <v-col
                                :cols = 4
                                >
                                    <v-img
                                    v-bind:src="this.image"
                                    aspect-ratio = 1.8
                                    max-height = "300px"
                                    max-width = "300px"
                                    >
                                    </v-img>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col
                                :cols = 12
                                >
                                    <span
                                    class = "pb-2"
                                    >
                                        About me:
                                    </span>
                                    <v-textarea
                                    outlined
                                    :value = "this.aboutMe"
                                    >
                                        {{ this.aboutMe }}
                                    </v-textarea>
                                </v-col>
                            </v-row>
                        </v-card-text>
                        <v-card-actions
                        class = "pt-1 pb-6"
                        >
                            <v-layout justify-center>
                                <v-btn
                                large
                                primary
                                >
                                    Change edited information
                                </v-btn>
                            </v-layout>
                        </v-card-actions>
                        <h1
                        class = "pa-5"
                        >
                            Tours
                        </h1>
                        <v-radio-group
                        v-model="radioselected"
                        row
                        class = "pa-5"
                        >
                            <v-radio
                            v-for = "val in radio_values"
                            :key="val.index"
                            :label="val.text"
                            :value="val.value"
                            >
                            </v-radio>
                        </v-radio-group>
                        <TourList :tour_data="tours[radioselected]"/> 
                    </v-card>
                </v-row>
            </v-container>
        </v-content>
    </div>
</template>

<script>
import TourList from './TourList.vue'
import ProfileService from '../services/profile_service'

const FRONTEND_URL = process.env.VUE_APP_FRONTEND_URL

export default {
    name : "Profile",
    components : {
        TourList
    },
    data: () => {
        return {
            radioselected : 2,
            radio_values : [
                {
                    text : "All",
                    value : 0
                },
                {
                    text : "My Upcoming Tours",
                    value : 1
                },
                {
                    text : "My Organized Tours",
                    value : 2
                },
                {
                    text : "History",
                    value : 3
                },
            ],
            languages : [
                {
                    language : "Portuguese",
                    value : 1
                },
                {
                    language : "Spanish",
                    value : 2
                },
                {
                    language : "French",
                    value : 3
                },
                {
                    language : "German",
                    value : 4
                },
                {
                    language : "English",
                    value : 5
                },
                {
                    language : "Dutch",
                    value : 6
                },
            ],
            country_codes : [
                {
                    country_code : "PT: +351",
                    value : 1
                },
                {
                    country_code : "ES: +34",
                    value : 2
                },
                {
                    country_code : "FR: +33",
                    value : 3
                },
                {
                    country_code : "DE: +49",
                    value : 4
                },
                {
                    country_code : "UK: +44",
                    value : 5
                },
                {
                    country_code : "NL: +31",
                    value : 6
                },
                {
                    country_code : "BE: +32",
                    value : 7
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
            cards: [
                {
                title: "Indigo Card",
                color: "indigo",
                flex: 4
                },
                {
                title: "Red Card",
                color: "red",
                flex: 4
                },
                {
                title: "Purple Card",
                color: "purple",
                flex: 4
                },
                {
                title: "Green Card",
                color: "green",
                flex: 7
                },
                {
                title: "Black Card",
                color: "black",
                flex: 5
                },
                {
                title: "Brown Card",
                color: "brown",
                flex: 10
                }
            ],
            username : "",
            email: "",
            dateOfBirth: "",
            phoneNumber: "",
            spoken_languages: "",
            aboutMe: "",
            image: "",
            rating: "",
        }
    },
    created: async function () {
        await this.doStuff();
    },
    watch: {
        '$route.params.username': async function (id) {
            await this.doStuff()
        }
    },
    methods:  {
        log(x) {
            console.log(x)
        },
        doStuff: async function () {
            this.image = FRONTEND_URL + '/images/' + this.$route.params.username + '.png'
            let profile = await ProfileService.get(this.$route.params.username)
            this.username = profile.data.username
            this.email = profile.data.email
            this.dateOfBirth = profile.data.dateOfBirth
            this.phoneNumber = profile.data.phoneNumber
            this.rating = profile.data.rating
            this.spoken_languages = profile.data.languages
            this.aboutMe = profile.data.aboutMe
            this.tours = [
                        profile.data.schedules.map(s => s.tour).concat(profile.data.tours),
                        profile.data.schedules.filter( s => new Date(s.date) > new Date() ).map(s => s.tour),
                        profile.data.tours,
                        profile.data.schedules.filter( s => new Date(s.date) <= new Date() ).map(s => s.tour)]
            console.log(profile.data)
        }
    },
}
</script>