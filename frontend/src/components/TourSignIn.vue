<template>
   <v-card>
        <v-card-title>
            <span class="headline">Tour Sign in</span>
        </v-card-title>
        <v-card-text>
            <form>
                <v-text-field
                v-model="participants"
                type="number"
                label="Number of participants"
                required
                ></v-text-field>
                <v-btn class="mr-4" @click="tourSignIn">submit</v-btn>
            </form>
        </v-card-text>
    </v-card>
</template>

<script>

import schedule_signin from '../services/schedule_signin'
import schedule_service from '../services/schedule_service'

export default {
    name: "TourSignIn",
    props: {
        id: Number
    },
    data () {
      return {
         participants: 0
      }
    },

    computed: {

    },

    methods: {
        tourSignIn: async function () {
          const resp = await schedule_signin.scheduleSignIn(this.$props.id, this.participants)
          console.log(resp)
          this.$router.go(0)
        }
    }
}
</script>

<style scoped>

.card {
    width : 75%;
    height : 450px;
}

</style>
