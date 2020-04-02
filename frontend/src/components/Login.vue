<template>
    <div>
        <h1>
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
                    class="elevation-12"
                    width = "1000px"
                    height = "600px"
                    >
                        <v-card-text>
                            <v-form>
                            <v-text-field
                                label="Login"
                                name="login"
                                type="text"
                            />

                            <v-text-field
                                id="password"
                                label="Password"
                                name="password"
                                type="password"
                            />
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer />
                            <v-btn color="primary">Login</v-btn>
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