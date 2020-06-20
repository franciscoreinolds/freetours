<template>
    <v-dialog v-model="dialog" max-width="600px">
        <template v-slot:activator="{ on, attrs }">
            <v-btn
                    text
                    v-bind="attrs"
                    v-on="on"
            >
                Login
            </v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">Login</span>
            </v-card-title>
            <v-card-text>
                <v-card-text>
                    <v-form>
                        <v-text-field
                                outlined
                                v-model="username"
                                label="Username"
                                name="username"
                                type="text"
                                class = "pt-12"
                        />

                        <v-text-field
                                outlined
                                v-model="password"
                                id="password"
                                label="Password"
                                name="password"
                                type="password"
                                class = "pt-6"
                        />
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-layout justify-center>
                        <v-btn
                                large
                                primary
                                v-on:click="login()"
                        >
                            Login
                        </v-btn>
                    </v-layout>
                </v-card-actions>
                <v-card-actions
                        class = "pt-4"
                >
                    <v-layout justify-center>
                        <v-btn
                                large
                                primary
                                @click="dialog = false"
                        >
                            I Forgot my Password
                        </v-btn>
                    </v-layout>
                </v-card-actions>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

export default {
    name: "Login",
    props: {
        source: String,
    },
    data: () => ({
        dialog: false,
        username: '',
        password: ''
    }),
    methods: {
        // Login when called
        login: function () {
            axios.post(API_URL.toString() + '/sign_in', {
                username: this.username,
                password: this.password
            })
                .then(response => {
                    console.log("Response:")
                    console.log(response)
                })
                .catch(e => {
                    console.log("Errors:")
                    console.log(e)
                })
        }
    }
}
</script>

<style scoped>

.card {
    width : 75%;
    height : 450px;
}

</style>