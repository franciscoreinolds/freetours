import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class ReviewService {
    addReview(token, rating, comment) {

        return axios.post(API_URL + '/review?token=' + token, {
                rating: rating,
                comment: comment
            })
            .then(
                response => {
                    if (response.data) {
                        return response;
                    }
                }
            )
            .catch(
                error => {
                    return error.response;
                }
            )

    }
}

export default new ReviewService();