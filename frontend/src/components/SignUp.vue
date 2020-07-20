<template>
    <div>
        <h1
        class = "pl-12 pt-6"
        >
            SignUp
        </h1>
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
                        <v-form
                           v-model="isFormValid" 
                        >
                            <v-card-text>
                                <v-text-field
                                    v-model = "user.username"
                                    outlined
                                    label="Username"
                                    name="username"
                                    type="text"
                                    class = "pt-12"
                                    :rules="[rules.required]" 
                                    required
                                />

                                <v-text-field
                                    outlined
                                    v-model = "user.password"
                                    id="password"
                                    label="Password"
                                    name="password"
                                    type="password"
                                    :rules="[rules.required]" 
                                    required
                                />
                                <v-text-field
                                    v-model = "user.email"
                                    outlined
                                    label="Email"
                                    name="email"
                                    type="text"
                                    :rules="[rules.required]" 
                                    required
                                />
                                <v-row>
                                    <v-col
                                    :cols = 3>
                                        <v-select
                                            v-model="user.country_code"
                                            :items="country_codes"
                                            item-text="country_code"
                                            item-value="value"
                                            chips
                                            label="Country Code"
                                            outlined
                                            height = 50px
                                        ></v-select>
                                    </v-col>
                                    <v-col
                                    :cols = 9
                                    >
                                        <v-text-field
                                            outlined
                                            v-model = "user.phone_nr"
                                            label="Phone Number"
                                            name="phone_number"
                                            type="text"
                                        />
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col
                                    :cols = 3
                                    >
                                        <v-file-input
                                        outlined
                                        small-chips
                                        accept = ".png"
                                        label="Profile Photo"
                                        prepend-icon="mdi-camera"
                                        v-model="profile_image"
                                        >
                                        </v-file-input>
                                    </v-col>
                                    <v-col
                                    :cols = 9
                                    >
                                        <v-menu
                                            ref="menu_date"
                                            v-model="menu_date"
                                            :close-on-content-click="false"
                                            :return-value.sync="user.dob"
                                            transition="scale-transition"
                                            offset-y
                                            min-width="290px"
                                        >
                                            <template v-slot:activator="{ on }">
                                                <v-text-field
                                                    outlined
                                                    v-model="user.dob"
                                                    label="Date of Birth"
                                                    readonly
                                                    v-on="on"
                                                ></v-text-field>
                                            </template>
                                            <v-date-picker v-model="user.dob" no-title scrollable>
                                                <v-spacer></v-spacer>
                                                <v-btn text color="primary" @click="menu_date = false">Cancel</v-btn>
                                                <v-btn text color="primary" @click="$refs.menu_date.save(user.dob)">OK</v-btn>
                                            </v-date-picker>
                                        </v-menu>
                                    </v-col>
                                </v-row>
                                <v-select
                                    v-model="sel_languages"
                                    :items="languages"
                                    item-text="name"
                                    item-value="name"
                                    chips
                                    label="What languages do you speak"
                                    outlined
                                    multiple
                                    height = 50px
                                ></v-select>
                                <v-textarea
                                    v-model = "user.description"
                                    outlined
                                    name="description"
                                    label="Description about yourself"
                                ></v-textarea>
                            </v-card-text>
                            <v-alert
                                    dismissible
                                    v-if = 'success'
                                    type = 'success'
                            >
                                Successful sign up. You can now login.
                            </v-alert>
                            <v-alert
                                    dismissible
                                    v-if = 'error'
                                    type = 'error'
                            >
                                A user already exists with that username / email.
                            </v-alert>
                            <v-card-actions>
                                <v-layout justify-center>
                                    <v-btn
                                    large
                                    primary
                                    :disabled = "!isFormValid"
                                    v-on:click="signup()"
                                    >
                                        Sign Up
                                    </v-btn>
                                </v-layout>
                            </v-card-actions>
                            <br>
                        </v-form>
                    </v-card>
                </v-row>
            </v-container>
        </v-content>
    </div>
</template>

<script>
import AuthService from '../services/auth_service';
import LangService from '../services/lang_service';

export default {
    name : "Login",
    props: {
      source: String,
    },
    data: () => ({
        success : false,
        error : false,
        isFormValid : true,
        rules : {
            required: value => !!value || 'Required field.',
        },
        languages : [],
        sel_languages : [],
        country_codes : [],
        sel_country_code : "",
        menu_date : false,
        profile_image : null,
        user : {
            username : '',
            email : '',
            password : '',
            country_code : '',
            phone_nr : '',
            dob: new Date().toISOString().substr(0, 10),
            languages : [],
            description : ''
        }
    }),
    async created() {
        var lang_array = await LangService.get();
        console.log(lang_array);
        for (var i = 0; i < lang_array.data.length; i++) {
            //var toi_lang = {};
            var toi_code = {};

            /*
            toi_lang.name = lang_array.data[i].name;
            toi_lang.id = lang_array.data[i].id;
            this.languages.push(toi_lang);
            */
            this.languages.push(lang_array.data[i]);
            toi_code.country_code =  lang_array.data[i].abbreviation + " : " + lang_array.data[i].country_code;
            toi_code.value = lang_array.data[i].id;
            this.country_codes.push(toi_code);
        }
    },
    methods: {
        //signup
        signup: async function() {
            this.success = this.error = false
            for (var i = 0 ; i < this.sel_languages.length ; i++) {
                var language = this.languages.find(obj => {
                    return obj.name == this.sel_languages[i]
                })
                this.user.languages.push(language);
            }
            this.status = await AuthService.register(this.user, this.profile_image);
            switch(this.status) {
                case 201:
                    this.success = true;
                    break;
                case 409:
                    this.error = true;
                    console.log('409');
                    break;
            }
        }
    }
}
</script>

<style scoped>

.card {
    width : 75%;
}

</style>
