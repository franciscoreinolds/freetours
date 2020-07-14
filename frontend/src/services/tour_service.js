import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class TourService {
    getTour(id) {
        const auth = localStorage.getItem('user')
        let config = {};

        console.log(auth)
        if (auth) {
            config = {
                headers: {
                  Authorization: auth,
                }
            }
        }

        return axios
            .get(API_URL + '/tour/'+id, config)
            .then(response => {
                return response;
            })
            .catch(error => {
                return error.response.status;
            });
    }
}

export default new TourService();
