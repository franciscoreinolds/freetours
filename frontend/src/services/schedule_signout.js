import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class ScheduleSignOutService {
    async scheduleSignOut(id, nrPeople){
        const auth = localStorage.getItem('user')
        let config = {};

        if (auth) {
            config = {
                headers: {
                  Authorization: auth,
                }
            }

            console.log("SignOut - Id: " + id);
            console.log("SignOut - NrPeople: " + nrPeople);
            
            const data = {'scheduleId': id}
            
            console.log(data)
            try {
                const response = await axios
                    .post(API_URL + '/schedule/unsubscribe', data, config);
                return response;
            }
            catch (error) {
                return error.response.status;
            }
        }
    }
    
}

export default new ScheduleSignOutService();
