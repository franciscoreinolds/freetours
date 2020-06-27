<template>
    <div>
        <v-footer
        class = "footer"
        color = "black"
        height = 150px
        max-height = 150px
        >
            <v-card
            class = "my_card"
            height = "120px"
            min-width = "200px"
            color = "black"
            >
                <v-card-text
                class = "my_card_text"
                >
                    <v-btn
                    v-for="icon in icons"
                    :key = "icon.index"
                    class = "mx-4"
                    dark
                    icon
                    >
                        <v-icon
                        dark
                        size = "24px"
                        >
                            {{icon}}
                        </v-icon>
                    </v-btn>
                </v-card-text>
                <v-divider></v-divider>
                <v-card-text
                class = "text-center white--text"
                >
                    {{ new Date().getFullYear() }} - <strong>FreeTours</strong>
                </v-card-text>
            </v-card>
        </v-footer>
    </div>
</template>

<script>
export default {
    name : "Footer",
    data: () => ({
        icons : [
            'mdi-facebook',
            'mdi-twitter',
            'mdi-email'
        ]
    }),
}



</script>

<style>

<link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">

.footer {
    justify-content : center;
}

.my_card {
    margin : 0 auto;
    justify-text : center;
}

</style>
