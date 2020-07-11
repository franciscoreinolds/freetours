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
                            class="elevation-12 card"
                            max-width="600px"
                    >
                        <h1
                                class = "pl-12 pt-6"
                        >
                            Forgot Password
                        </h1>

                        <v-card-text class = "pl-12 pt-6">
                            Insert your email and you'll receive one mail with the instructions to reset your password.
                        </v-card-text>

                        <v-form
                                v-model="isFormValid"
                        >
                            <v-card-text>
                                <v-text-field
                                        v-model = "email"
                                        outlined
                                        label="Email"
                                        name="email"
                                        type="text"
                                        :rules="[rules.required]"
                                        required
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
                                            :disabled = "!isFormValid"
                                            v-on:click="send()"
                                    >
                                        Send an email
                                    </v-btn>
                                </v-layout>
                            </v-card-actions>
                            <br>
                        </v-form>
                    </v-card>
                </v-row>
            </v-container>
        </v-main>
    </div>
</template>

<script>

    import AuthService from "../services/auth_service";

    export default {
        name: "ForgotPassword",
        data: () => ({
            success : false,
            error : false,
            message: "",
            isFormValid : true,
            rules : {
                required: value => !!value || 'Required field.',
            },
            email : "",
        }),
        methods: {
            //send mail
            send:  async function () {
                this.success = this.error = false
                let response = await AuthService.forgot_password(this.email);
                this.message = response.data
                switch (response.status) {
                    case 201:
                        this.success = true;
                        break;
                    case 400:
                        this.error = true;
                        break;
                }
            }
        }
    }

</script>

<style scoped>

</style>