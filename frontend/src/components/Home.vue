<template>
    <div>
        <div
        >
            <v-carousel
            cycle
            show-arrows-on-hover
            height = "300px"
            class = "carousel"
            >
                <v-carousel-item
                v-for="(slide, i) in slides"
                :key="i"
                >
                    <v-sheet
                        :color="colors[i]"
                        height="100%"
                    >
                        <v-row
                        class="fill-height"
                        align="center"
                        justify="center"
                        >
                        <div class="display-3">{{ slide }} Slide</div>
                        </v-row>
                    </v-sheet>
                </v-carousel-item>
            </v-carousel>
        </div>
        <v-row
        class = "filter_bar"
        width = 50%
        >
            <v-col
            :cols = 6
            >
                <v-text-field
                    class = "text-field"
                    label="Destination"
                    outlined
                    prop
                    :append-icon="'mdi-map-marker'"
                >
                </v-text-field>
            </v-col>
            <v-col
            :cols = 2
            >
                <v-menu
                    v-model="menu2"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                >
                    <template v-slot:activator="{ on }">
                    <v-text-field
                        v-model="date"
                        label="Tour Date"
                        :prepend-icon="'mdi-calendar'"
                        readonly
                        v-on="on"
                    ></v-text-field>
                    </template>
                    <v-date-picker v-model="date" @input="menu2 = false"></v-date-picker>
                </v-menu>
            </v-col>
            <v-col
            :cols = 2
            >
                <v-select
                label = "Tour Category"
                solo
                >
                </v-select>
            </v-col>
            <v-col
            :cols = 1
            >
                <router-link
                :to = "{path : '/search'}"
                class = "router-link"
                >
                    <v-btn
                    class = "btn"
                    >
                        Search Tour
                    </v-btn>
                </router-link>
            </v-col>
        </v-row>

        <h2>Where will your next Tour be?</h2>

        <!-- Cards -->
        <v-carousel
        hide-delimiters
        cycle
        show-arrows-on-hover
        height = "600px"
        class = "carousel"
        >
            <v-carousel-item 
            v-for="i in 3" 
            :key="i"
            class = "carousel_item"
            >
                <v-layout row>
                    <v-flex sm4 v-for="j in 3" :key="j" pl-2 pr-2>
                        <v-card
                        class = "card"
                        width = "100%"
                        >
                            <v-card-title
                            primary-title
                            class = "align-center"
                            >
                                <div>
                                    <h3 class="headline mb-0">Card {{i}}-{{j}}</h3>
                                </div>
                            </v-card-title>
                        
                            <v-img
                            src="https://cdn.vuetifyjs.com/images/cards/desert.jpg"
                            ></v-img>
                            
                            <v-card-subtitle>
                                Subtitle
                            </v-card-subtitle>
                            <v-card-text>
                                Text
                            </v-card-text>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-carousel-item>
        </v-carousel>
        <v-layout justify-center>
            <v-card-actions>
                <v-btn primary>
                    More Tours
                </v-btn>
            </v-card-actions>
        </v-layout>
    </div>
</template>

<script>
export default {
    name : "Home",
    data() {
        return {
            colors: [
            'indigo',
            'warning',
            'pink darken-2',
            'red lighten-1',
            'deep-purple accent-4',
            ],
            slides : [
            'First',
            'Second',
            'Third',
            'Fourth',
            'Fifth',
            ],
            date: new Date().toISOString().substr(0, 10),
            menu2: false,
        }
    }
}
</script>

<style scoped>

h2 {
    margin-bottom: 25px;
}

.carousel {
    margin-bottom : 25px;
}

.card { 
    height : 600px;
}

.btn {
    margin-top : 5px;
}

.filter_bar {
    margin : auto;
}

.carousel_item {
    width : 90%;
    max-width : 90%;
    height : 600px;
    max-height : 600px;
    margin : auto;
}

</style>