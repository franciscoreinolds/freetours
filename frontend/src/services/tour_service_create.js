import axios from 'axios';
import store from '../store';

const API_URL = process.env.VUE_APP_API_URL

class TourServiceCreate {

    async createTour(tour, images) {
        // Duration
        if(tour.duration_hours.length==1) tour.duration_hours = "0".concat(tour.duration_hours);
        if(tour.duration_minutes.length==1) tour.duration_minutes = "0".concat(tour.duration_minutes);
        if(tour.duration_hours == '') tour.duration_hours = "00"
        if(tour.duration_minutes == '') tour.duration_minutes = "00"
        tour.duration = tour.duration_hours.concat(":",tour.duration_minutes).concat(':00');

        const auth = localStorage.getItem('user')
        let config = {};

        if (auth) {
            config = {
                headers: {
                    Authorization: auth,
                    'Content-Type': 'multipart/form-data'
                }
            }
        }

        console.log(store.state.username);

        const tourObj = {
            name: tour.name,
            description: tour.description,
            duration: tour.duration,
            languages: tour.languages,
            city: tour.location,
            minCapacity: tour.min,
            maxCapacity: tour.max,
            category: tour.category,
            guide: {"username": store.state.username}
        };
        const tourJson = JSON.stringify(tourObj);
        const blob = new Blob([tourJson], {
            type: 'application/json'
        });

        let formData = new FormData();
        formData.append("tour", blob);
        for(let i = 0; i < images.length; i++ ){
            let file = images[i];
            formData.append('images', file);
        }

        // Request
        return axios
            .post(API_URL + '/createTour', formData, config)
            .then(response => {
                console.log("Pedido efetuado com sucesso");
                return response;
            })
            .catch(error => {
                console.log("Erro");
                return error.response;
            });
    }

}

export default new TourServiceCreate();
