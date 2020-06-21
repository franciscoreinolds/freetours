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
                                v-model="user.username"
                                label="Username"
                                name="username"
                                type="text"
                                class = "pt-12"
                                @input="message = ''"
                        />

                        <v-text-field
                                outlined
                                v-model="user.password"
                                id="password"
                                label="Password"
                                name="password"
                                type="password"
                                class = "pt-6"
                                @input="message = ''"
                        />
                    </v-form>
                </v-card-text>
                <v-card-text class="red--text" v-if="message">{{message}}</v-card-text>
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
import AuthService from '../services/auth_service'
import User from '../models/user';

export default {
    name: "Login",
    props: {
        source: String,
    },
    data: () => ({
        dialog: false,
        user: new User('', '', ''),
        message: ''
    }),
    methods: {
        // Login when called
        login: async function () {
            this.status = await AuthService.login(this.user)
            switch (this.status) {

                case 401:
                    this.message = "*Invalid Credentials";
                    break;

                case 403:
                    this.message = "*Username doesn't exist";
                    break;
            }
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