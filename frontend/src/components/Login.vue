<template>
    <div>
        <h1
        class = "pl-12 pt-6"
        >
            Login
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
                        <v-card-text>
                            <v-form>
                            <v-text-field
                                outlined
                                label="Login"
                                name="login"
                                type="text"
                                class = "pt-12"
                            />

                            <v-text-field
                                outlined
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
                                >
                                    I Forgot my Password
                                </v-btn>
                            </v-layout>
                        </v-card-actions>
                    </v-card>
                </v-row>
            </v-container>
            </v-content>
    </div>
</template>

<script>
export default {
    name : "Login",
    props: {
      source: String,
    },
}
</script>

<style scoped>

.card {
    width : 75%;
    height : 450px;
}

</style>