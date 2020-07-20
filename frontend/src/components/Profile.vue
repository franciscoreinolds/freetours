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
                        <v-row style="margin: 1%;justify-content: space-between;">
                            <h1
                            class = "pa-5"
                            >
                                My Profile
                            </h1>
                            <v-btn v-if="isOwnProfile()" v-on:click="deleteAccount" style="margin: 2%" dark justify="right"> Delete account</v-btn>
                        </v-row>
                        
                        <v-card-text>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        disabled
                                        v-model="username"
                                        label="Username"
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                        :disabled="!editMode"
                                        v-model="email"
                                        label="Email"
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                        :disabled="!editMode"
                                        v-model="dateOfBirth"
                                        label="Date of birth"
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        :disabled="!editMode"
                                        v-model="phoneNumber"
                                        label="Phone number"
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                        disabled
                                        v-model="rating"
                                        label="Rating"
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col
                                :cols = 8
                                >   
                                    <v-select
                                        :items="languages"
                                        item-text="name"
                                        item-value="id"
                                        multiple
                                        :disabled="!editMode"
                                        v-model="spoken_languages"
                                        label="Languages"
                                    ></v-select>
                                </v-col>
                                <v-col v-if="!editMode"
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
                                <v-col v-else
                                :cols = 4
                                >
                                    <v-file-input
                                        outlined
                                        small-chips
                                        accept = ".png"
                                        label="Edit your profile picture"
                                        v-model="newImage"
                                        required
                                    >
                                    </v-file-input>
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
                                    :disabled="!editMode"
                                    outlined
                                    v-model="aboutMe"
                                    >
                                    </v-textarea>
                                </v-col>
                            </v-row>
                        </v-card-text>
                        <v-card-actions
                        class = "pt-1 pb-6"
                        >
                            <v-layout justify-center>
                                <v-btn v-if="!editMode"
                                large
                                primary
                                :disabled="!isOwnProfile()"
                                v-on:click="changeToEditMode"
                                >
                                    Change information
                                </v-btn>
                                <v-btn v-else
                                large
                                primary
                                v-on:click="changeToViewMode"
                                >
                                    Save Changes
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
import LangService from '../services/lang_service'
import profile_service from '../services/profile_service'

const FRONTEND_URL = process.env.VUE_APP_FRONTEND_URL

export default {
    name : "Profile",
    components : {
        TourList
    },
    data: () => {
        return {
            newImage: null,
            editMode: false,
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
        var lang_array = await LangService.get();
        for (var i = 0; i < lang_array.data.length; i++) {
            this.languages.push(lang_array.data[i]);
        }
        await this.doStuff();
    },
    watch: {
        '$route.params.username': async function (id) {
            await this.doStuff()
        }
    },
    methods:  {
        async doStuff(x) {
            this.image = FRONTEND_URL + '/images/' + this.$route.params.username + '.png'
            let profile = await ProfileService.get(this.$route.params.username)
            this.username = profile.data.username
            this.email = profile.data.email
            this.rating = profile.data.rating
            this.dateOfBirth = profile.data.dateOfBirth
            this.phoneNumber = profile.data.phoneNumber
            this.spoken_languages = profile.data.languages
            this.aboutMe = profile.data.aboutMe
            console.log(profile.data)
            const aux = profile.data.schedules ? profile.data.schedules.filter( s => new Date(s.date) > new Date() ).map(s => s.tour) : []
            const aux2 = profile.data.schedules ? profile.data.schedules.filter( s => new Date(s.date) <= new Date() ).map(s => s.tour) : []
            const aux3 = profile.data.schedules ? profile.data.schedules.map(s => s.tour) : []
            this.tours = [
                            aux3.concat(profile.data.tours),
                            aux,
                            profile.data.tours,
                            aux2
            ]
        },

        changeToEditMode(){
            this.editMode = true
        },

        async changeToViewMode(){
            this.editMode = false
            
            const user = {
                'username': this.username,
                'email': this.email,
                'rating': this.rating,
                'dateOfBirth': this.dateOfBirth,
                'phoneNumber': this.phoneNumber,
                'languages': [],
                'aboutMe': this.aboutMe
            }

            for(const languageId of this.spoken_languages){
                user.languages.push(this.languages[languageId])
            }
             
            
            await profile_service.saveChanges(user, this.newImage)
            await this.doStuff();
        },

        isOwnProfile(){
            console.log(this.$store.state.username + " " + this.$route.params.username)
            console.log(this.$store.state.username === this.$route.params.username )
            return this.$store.state.username === this.$route.params.username 
        },

        async deleteAccount(){
            await profile_service.deleteAccount(this.username);

            localStorage.removeItem('user');
            this.$store.commit('setUsername','');
            this.$router.push('/')
        }
    }
}
</script>