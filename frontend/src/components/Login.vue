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
                                @click="dialog = false"
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
export default {
    name : "Login",
    props: {
      source: String,
    },
    data: () => ({
        dialog: false
    })
}
</script>

<style scoped>

.card {
    width : 75%;
    height : 450px;
}

</style>