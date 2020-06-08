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
                        <v-card-text>
                            <v-form>
                            <v-text-field
                                outlined
                                label="Username"
                                name="username"
                                type="text"
                                class = "pt-12"
                            />

                            <v-text-field
                                outlined
                                id="password"
                                label="Password"
                                name="password"
                                type="password"
                            />
                            <v-text-field
                                outlined
                                label="Email"
                                name="password"
                                type="text"
                            />
                            <v-row>
                                <v-col
                                :cols = 3>
                                    <v-select
                                        v-model="value"
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
                                        :return-value.sync="date"
                                        transition="scale-transition"
                                        offset-y
                                        min-width="290px"
                                    >
                                        <template v-slot:activator="{ on }">
                                        <v-text-field
                                            outlined
                                            v-model="date"
                                            label="Tour Date"
                                            readonly
                                            v-on="on"
                                        ></v-text-field>
                                        </template>
                                        <v-date-picker v-model="date" no-title scrollable>
                                        <v-spacer></v-spacer>
                                        <v-btn text color="primary" @click="menu_date = false">Cancel</v-btn>
                                        <v-btn text color="primary" @click="$refs.menu_date.save(date)">OK</v-btn>
                                        </v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                            <v-select
                                v-model="value"
                                :items="languages"
                                item-text="language"
                                item-value="value"
                                chips
                                label="What languages do you speak"
                                outlined
                                multiple
                                height = 50px
                            ></v-select>
                            <v-textarea
                            outlined
                            name="description"
                            label="Description about yourself"
                            ></v-textarea>
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
                        class = "pt-4 pb-6"
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
    data: () => ({
        languages : [
            {
                language : "Portuguese",
                value : 1
            },
            {
                language : "Spanish",
                value : 2
            },
            {
                language : "French",
                value : 3
            },
            {
                language : "German",
                value : 4
            },
            {
                language : "English",
                value : 5
            },
            {
                language : "Dutch",
                value : 6
            },
        ],
        country_codes : [
            {
                country_code : "PT: +351",
                value : 1
            },
            {
                country_code : "ES: +34",
                value : 2
            },
            {
                country_code : "FR: +33",
                value : 3
            },
            {
                country_code : "DE: +49",
                value : 4
            },
            {
                country_code : "UK: +44",
                value : 5
            },
            {
                country_code : "NL: +31",
                value : 6
            },
            {
                country_code : "BE: +32",
                value : 7
            },
        ],
        menu_date : false
    }),
}
</script>

<style scoped>

.card {
    width : 75%;
}

</style>