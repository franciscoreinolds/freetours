<template>
    <div>
        <v-main>
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
                            max-width="600px"
                            height="550px"
                    >
                        <h1
                                class = "pl-12 pt-6"
                        >
                            Review
                        </h1>

                        <div class="te textarea">
                            <v-card-actions class="pa-4">
                                Rate your overall experience:
                                <v-rating
                                        v-model="rating"
                                        color="yellow darken-3"
                                        background-color="grey darken-1"
                                        empty-icon="$ratingFull"
                                        half-increments
                                        hover
                                        @input="restore_variables()"
                                ></v-rating>
                            </v-card-actions>
                            <v-card-text class="pa-4">
                                Add a comment for being displayed in the tour (Optional):
                            </v-card-text>
                            <v-textarea
                                    v-model = "comment"
                                    outlined
                                    name="description"
                                    label="Add a Tour or Guide Comment (Optional)"
                                    @input="restore_variables()"
                            ></v-textarea>
                            <v-alert
                                    dismissible
                                    v-if = 'success'
                                    type = 'success'
                            >
                                {{ this.message }}
                            </v-alert>
                            <v-alert
                                    dismissible
                                    v-if = 'error'
                                    type = 'error'
                            >
                                {{ this.message }}
                            </v-alert>
                            <v-layout justify-center>
                                <v-btn
                                        large
                                        primary
                                        :disabled = "this.rating <= 0.0 || this.rating > 5.0"
                                        v-on:click = "addReview()"
                                >
                                    Add Review
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card>
                </v-row>
            </v-container>
        </v-main>
    </div>
</template>

<script>
    import ReviewService from "../services/review_service";

    export default {
        name: "Review",
        data: () => ({
            success : false,
            error : false,
            message: "",
            rating: 0.0,
            comment: "",
        }),
        methods: {
            addReview: async function () {
                this.restore_variables();
                let response = await ReviewService.addReview(this.$route.params.token, this.rating, this.comment)
                console.log(response)
                this.message = response.data
                switch (response.status) {
                    case 200:
                        this.success = true;
                        break;
                    case 400:
                    case 404:
                    case 406:
                        this.error = true;
                        break;
                }
            },
            restore_variables: function () {
                this.success = this.error = false
                this.message = ""
            }
        }
    }
</script>

<style scoped>

    .textarea {
        width : 85%;
        margin : auto;
    }

</style>