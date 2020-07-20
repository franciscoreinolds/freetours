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
                <v-form
                    ref="form"
                    v-model="isFormValid"
                >
                    <v-card-text>
                        <v-text-field
                                outlined
                                v-model="user.username"
                                label="Username"
                                name="username"
                                type="text"
                                class = "pt-12"
                                @input="restore_variables()"
                                required
                                :rules = "[rules.required]"
                        />

                        <v-text-field
                                outlined
                                v-model="user.password"
                                id="password"
                                label="Password"
                                name="password"
                                type="password"
                                class = "pt-6"
                                @input="restore_variables()"
                                required
                                :rules = "[rules.required]"
                            />
                        </v-card-text>
                    <v-alert
                            dismissible
                            v-if = 'success'
                            type = 'success'
                    >
                        {{ this.message }}
                    </v-alert>
                    <v-alert
                            dismissible
                            v-if = 'error'
                            type = 'error'
                    >
                        {{ this.message }}
                    </v-alert>
                    <v-card-actions>
                        <v-layout justify-center>
                            <v-btn
                                    large
                                    primary
                                    v-on:click="login()"
                                    :disabled = "!isFormValid"
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
                                    v-on:click="forgot_password()"
                            >
                                I Forgot my Password
                            </v-btn>
                        </v-layout>
                    </v-card-actions>
                </v-form>
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
        success : false,
        error : false,
        message: "",
        isFormValid : true,
        rules : { 
            required: value => !!value || 'Required field.',
        },
    }),
    methods: {
        // Login when called
        login: async function () {
            this.success = this.error = false
            this.status = await AuthService.login(this.user)
            switch (this.status) {
                case 200:
                    this.success = true;
                    this.dialog = false;
                    this.$router.push('/');
                    console.log('Login: 200!');
                    break;
                case 401:
                    this.error = true;
                    this.message = "Invalid Credentials";
                    break;

                case 403:
                    this.error = true;
                    this.message = "Username doesn't exist";
                    break;
            }
        },
        // Go to Forgot Password page
        forgot_password: function () {
            this.dialog = false
            this.$router.push('/forgotpassword')
        },
        restore_variables: function () {
            this.success = this.error = false
            this.message = ""
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
