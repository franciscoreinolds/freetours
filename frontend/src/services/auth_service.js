import axios from 'axios';
import store from '../store';

const API_URL = process.env.VUE_APP_API_URL

class AuthService {
    login(user) {
        return axios
            .post(API_URL + '/sign_in', {
                username: user.username,
                password: user.password
            })
            .then(response => {
                console.log(response)
                if (response.data) {
                    var jwt = response.data;
                    localStorage.setItem('user', jwt);
                    var parsedJwt =  JSON.parse(atob(jwt.split('.')[1]));
                    store.commit('setUsername', parsedJwt.sub);
                }

                return response.status;
            })
            .catch(error => {
                return error.response.status;
            });
    }

    logout() {
        localStorage.removeItem('user');
        store.commit('setUsername','');
    }

    register(user) {
        return axios
            .post(API_URL + '/sign_up', {
                username: user.username,
                email: user.email,
                password: user.password,
                phoneNumber : user.country_code + user.phone_nr,
                dateOfBirth : user.dob,
                languages : user.languages,
                aboutMe : user.description
            })
            .then(response => {
                if (response.data) {
                    console.log(response.data)
                    /*
                    var jwt = JSON.stringify(response.data);
                    localStorage.setItem('user', jwt);
                    var parsedJwt =  JSON.parse(atob(jwt.split('.')[1]));
                    store.commit('setUsername', parsedJwt.sub);
                    */
                }

                return response.status;
            })
            .catch(error => {
                return error.response.status;
            });
    }

    forgot_password(email) {
        return axios
            .post(API_URL + '/reset_password', {
                email : email
            })
            .then(response => {
                if (response.data) {
                    console.log(response.data)
                }
                return response
            })
            .catch(error => {
                return error.response;
            });
    }

    change_password(token, pass, pass_conf) {

        if (pass !== pass_conf)
            return false

        return axios
            .post(API_URL + '/change_password?token=' + token, {
                password : pass,
                password_confirmation : pass_conf,
            })
            .then(response => {
                if (response.data) {
                    console.log(response.data)
                }
                return response
            })
            .catch(error => {
                return error.response;
            });
    }
}

export default new AuthService();
