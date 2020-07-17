import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class ScheduleSignInService {
    async scheduleSignIn(id, nrPeople){
        const auth = localStorage.getItem('user')
        let config = {};

        if (auth) {
            config = {
                headers: {
                  Authorization: auth,
                }
            }
            
            const data = {'scheduleId': id, 'nrPeople': nrPeople}
            
            
            console.log(data)
            try {
                const response = await axios
                    .post(API_URL + '/schedule_signin/', data, config);
                return response;
            }
            catch (error) {
                return error.response.status;
            }
        }
    }
    
}

export default new ScheduleSignInService();
