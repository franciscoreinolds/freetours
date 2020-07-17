import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class CityService {
    get() {
        console.log(API_URL + '/cities');
        return axios
            .get(
                API_URL + '/cities'
            )
            .then(
                response => {
                    if (response.data) {
                        //console.log(response)
                        return response;
                    }
                }
            )
            .catch(
                error => {
                    console.log(error)
                    return error;
                }
            )
    }
}

export default new CityService();
