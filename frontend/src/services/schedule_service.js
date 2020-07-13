import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class ScheduleService {
    createSchedules(dates) {
        const auth = localStorage.getItem('user')
        let config = {};

        if (auth) {
            config = {
                headers: {
                  Authorization: auth,
                }
            }
            
            const data = []
            for(const date of dates){
                if(date != '')
                    data.push({'date': date})
            }

            return axios
            .post(API_URL + '/createScheduling/1', data, config)
            .then(response => {
                return response;
            })
            .catch(error => {
                return error.response.status;
            });
        }

        
    }
}

export default new ScheduleService();
