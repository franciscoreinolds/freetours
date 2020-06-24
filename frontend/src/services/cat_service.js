import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class CatService {
    get() {
        console.log(API_URL + '/categories');
        return axios
            .get(
                API_URL + '/categories'
            )
            .then(
                response => {
                    if (response.data) {
                        return response;
                    }
                }
            )
            .catch(
                error => {
                    return error;
                }
            )
    }
}

export default new CatService();
