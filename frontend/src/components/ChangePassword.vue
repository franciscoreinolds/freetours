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
                            Change Password
                        </h1>

                        <v-form
                                v-model="isFormValid"
                        >
                            <v-card-text>
                                <v-text-field
                                        outlined
                                        v-model = "password"
                                        id="password"
                                        label="Password"
                                        name="password"
                                        type="password"
                                        :rules="[rules.required]"
                                        @input="this.restore_variables"
                                        required
                                />
                                <v-text-field
                                        outlined
                                        v-model = "password_conf"
                                        id="password_conf"
                                        label="Password Confirmation"
                                        name="password_conf"
                                        type="password"
                                        :rules="[rules.required]"
                                        @input="this.restore_variables"
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
                                            v-on:click="change_password()"
                                    >
                                        Change Password
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
        name: "ChangePassword",
        data: () => ({
            success : false,
            error : false,
            message: "",
            isFormValid : true,
            rules : {
                required: value => !!value || 'Required field.',
            },
            password: "",
            password_conf: "",
        }),
        methods: {
            // change pass
            change_password: async function () {
                this.restore_variables()
                let response = await AuthService.change_password(this.$route.params.token, this.password, this.password_conf);
                console.log(response)
                if (response === false) {
                    this.message = 'Password and Password Confirmation are different'
                    this.error = true
                    return
                }
                this.message = response.data
                switch (response.status) {
                    case 200:
                        this.success = true;
                        break;
                    case 400:
                    case 404:
                    case 410:
                        this.error = true;
                        break;
                }
            },
            restore_variables: function () {
                this.success = this.error = false
                this.message = ""
            }
        }
    }
</script>

<style scoped>

</style>