import axios from 'axios';



const API_URL = process.env.VUE_APP_API_URL

class TourService {
    async getTour(id) {
        const auth = localStorage.getItem('user')
        let config = {};

        console.log(auth)
        if (auth) {
            config = {
                headers: {
                  Authorization: auth,
                }
            }
        }

        try {
            const response = await axios
                .get(API_URL + '/tour/' + id, config);
            return response;
        }
        catch (error) {
            return error.response.status;
        }
    }

    async searchTours(filters) {
        const auth = localStorage.getItem('user')
        let config = {};

        console.log(auth)
        if (auth) {
            config = {
                headers: {
                  Authorization: auth,
                }
            }
        }
        
        if(filters.category){
            config.params = {
                'categoryIds': filters.category.join(',')
            }
        }else{
            config.params = {}
        }

        if(filters.languages){
            config.params.languages = filters.languages.join(',')
        }
        
        console.log(filters.ratings)
        if(filters.ratings){
            config.params.ratings = filters.ratings.join(',')
        }

        console.log("Config")
        console.log(config)
        if(filters.date_range){
            if(filters.date_range[0])
            config.params.fromDate = filters.date_range[0]
            if(filters.date_range[1])
                config.params.untilDate = filters.date_range[1]
        }
        
        try {
            const response = await axios
                .get(API_URL + '/search/' + filters.destination, config);
            return response;
        }
        catch (error) {
            return error.response.status;
        }
    }

}

export default new TourService();
