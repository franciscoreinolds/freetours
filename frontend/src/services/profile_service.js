import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class ProfileService {
    get(user) {
        let jwt = localStorage.getItem('user');

        let promise;

        if(jwt != null) {

            jwt = jwt.substring(1, jwt.length - 1);

            promise = axios.get(API_URL + '/profile/' + user, { headers: {"Authorization": jwt}});

        } else {

            promise = axios.get(API_URL + '/profile/' + user);

        }

        return promise
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