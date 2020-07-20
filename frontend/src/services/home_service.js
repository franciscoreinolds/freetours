import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class HomeService {
    getHome() {
        const auth = localStorage.getItem('user')
        let config = {};

        if (auth) {
            config = {
                headers: {
                  Authorization: auth,
                }
            }
        }

        return axios
            .get(API_URL + '/home', config)
            .then(response => {
                console.log(response.data.mostPopularCities[0].images)
                return response;
            })
            .catch(error => {
                return error.response.status;
            });
    }
}

export default new HomeService();
