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
        <v-card-text class="red--text" v-if="message">{{message}}</v-card-text>
    </v-card>
</template>

<script>

import schedule_signin from '../services/schedule_signin'
import schedule_service from '../services/schedule_service'

export default {
    name: "TourSignIn",
    props: {
        id: Number,
        signeesSize: Number,
        maxCapacity: Number
    },
    data () {
      return {
         participants: 0,
         message: '',
      }
    },

    computed: {

    },

    methods: {
        tourSignIn: async function () {
            if(this.$props.signeesSize + this.participants > this.$props.maxCapacity){
                var possibleParticipants = this.$props.maxCapacity - this.$props.signeesSize;
                if(possibleParticipants > 0 && this.participants < this.$props.maxCapacity)
                    this.message = "*Tour capacity exceeded, your " + this.participants + " participants are waiting on queue." + " For now, you can only subscribe " + possibleParticipants + ".";
                else if (this.participants > this.$props.maxCapacity && possibleParticipants > 0)
                    this.message = "*Impossible. Tour capacity exceeded."
                else if(possibleParticipants == 0) 
                    this.message = "*Tour capacity exceeded, you can not subscribe any participant."
            }else this.message = '';
            
            const resp = await schedule_signin.scheduleSignIn(this.$props.id, this.participants)
            console.log(resp)
            if (resp.data === 1) this.$router.go(0)
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
