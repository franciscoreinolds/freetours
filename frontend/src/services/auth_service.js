import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class AuthService {
    login(user) {
        return axios
            .post(API_URL + '/sign_in', {
                username: user.username,
                password: user.password
            })
            .then(response => {
                if (response.data) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.status;
            })
            .catch(error => {
                return error.response.status;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return axios.post(API_URL + '/sign_up', {
            username: user.username,
            email: user.email,
            password: user.password
        });
    }
}

export default new AuthService();
