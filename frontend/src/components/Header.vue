<template>
    <div>
        <v-app-bar
        class = "navbar"
        dense
        dark
        height = 70px
        max-height = 70px
        >
        <v-toolbar-title>
            FreeTours
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-text-field
            class = "text-field"
            label="Destination"
            outlined
            prop
            :append-icon="'mdi-map-marker'"
        >
        </v-text-field>
        <v-spacer></v-spacer>
        <v-btn
        text
        >
            Add New Tour
        </v-btn>
        <v-btn
        text
        >
            Username
        </v-btn>
        <v-btn
        text
        >
            Logout
        </v-btn>
        </v-app-bar>
    </div>
</template>

<script>
export default {
    name : "Header"
}
</script>

<style scoped>

.text-field {
  height : 60px;
  width : 50px
}
</style>