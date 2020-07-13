import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class ScheduleService {
    createSchedules(id, dates) {
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
            
            console.log(data)
            return axios
            .post(API_URL + '/createScheduling/' + id, data, config)
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
