<template>
    <div>
        <v-footer
        class = "footer"
        dense
        dark
        height = 150px
        max-height = 150px
        >
            <v-card-text
                    class = "text-center white--text"
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

            <v-card-text class="text-center white--text">
                <strong>© FreeTours</strong> {{ new Date().getFullYear() }}. Made with ❤️ by <strong>EAMasters</strong>.
            </v-card-text>
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
