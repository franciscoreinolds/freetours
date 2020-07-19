import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class ProfileService {
    get(user) {
        let jwt = localStorage.getItem('user');

        let promise;

        if(jwt != null) {

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

    saveChanges(user, profile_image) {
        
        const userObj = {
            username: user.username,
            email: user.email,
            phoneNumber : user.phoneNumber,
            dateOfBirth : user.dateOfBirth,
            languages : user.languages,
            aboutMe : user.aboutMe
        };
        const userJson = JSON.stringify(userObj);
        const blob = new Blob([userJson], {
            type: 'application/json'
        });
        
        let formData = new FormData();
        formData.append("user", blob);
        formData.append('profileImage', profile_image);


        let jwt = localStorage.getItem('user');
        
        if(jwt) {
            return axios
            .post(API_URL + '/profile/' + userObj.username, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': jwt
                }
            })
            .then(response => {
                if (response.data) {
                    console.log(response)
                }
                return response.status;
            })
            .catch(error => {
                return error.response.status;
            }); 
        }

          
    }

}

export default new ProfileService();