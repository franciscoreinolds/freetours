import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class LangService {
    get() {
        console.log(API_URL + '/languages');
        return axios
            .get(
                API_URL + '/languages'
            )
            .then(
                response => {
                    if (response.data) {
                        console.log(response)
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

export default new LangService();
