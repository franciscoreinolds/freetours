import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class ProfileService {
    get(user) {
        console.log(API_URL + '/profile/' + user);
        return axios
            .get(
                API_URL + '/profile/' + user
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

export default new ProfileService();