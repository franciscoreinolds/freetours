<template>
   <v-card>
        <v-card-title>
            <span class="headline">Create Schedule</span>
        </v-card-title>
        <v-card-text>
            <v-form
                ref="form"
                v-model="isFormValid"
            >   
                <v-row v-for="i in 5"
                :key="i" justify="center" style="padding: 2%">
                        <label style="padding: 8px 10px;padding-right: 2%; color: black;" for="scheduleModel">Schedule {{i}}:  </label>
                        <Datetime
                         v-model="dates[i-1]"   
                         :input-id="'schedule' + i"
                         placeholder="Select date" 
                         style="border: solid 1px #ddd;padding: 8px 10px" 
                         type="datetime"
                         class="theme-black"/>
                 </v-row>
                <v-card-actions>
                    <v-layout justify-center>
                        <v-btn
                                large
                                primary
                                v-on:click="createSchedule()"
                                :disabled = "!isFormValid"
                        >
                            Create
                        </v-btn>
                    </v-layout>
                </v-card-actions>
            </v-form>
        </v-card-text>
    </v-card>
</template>

<script>

import { Datetime } from 'vue-datetime';
import 'vue-datetime/dist/vue-datetime.css'
import createScheduleStyle from './../style/createScheduleStyle.css'
import ScheduleService from './../services/schedule_service'

export default {
    name: "CreateSchedule",
    props: {
        id: Number // String
    },
    data () {
      return {
        dates: [null,null,null,null,null],
        isFormValid: true,
        picker: new Date().toISOString().substr(0, 10),
        landscape: false,
        reactive: false,
        fullWidth: false,
        flat: false,
        showCurrent: true,
        month: false,
        multiple: true,
        readonly: false,
        disabled: false,
        enableEvents: false,
      }
    },

    computed: {

    },

    methods: {
        createSchedule: async function () {
          //console.log(this.dates)
          const response = await ScheduleService.createSchedules(this.$props.id, this.dates)
          //console.log("Resp:" + response)
          if(this.$route.name == 'CreateTour'){
              this.$router.push('/tour/' + this.$props.id);
          }else {
              this.$router.go(0)
          }
          
        }
    },
    components: {
        Datetime: Datetime
    }
}
</script>

<style scoped>

.card {
    width : 75%;
    height : 450px;
}

</style>
