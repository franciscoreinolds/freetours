import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL

class TourServiceCreate {

    createTour(tour) {
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
                }
            }
        }

        console.log("Duration " + tour.duration)
        console.log("Loc: " + tour.location);

        return axios
            .post(API_URL + '/createTour', {
                name: tour.name,
                description: tour.description,
                duration: tour.duration,
                location: tour.location
            }, config)
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
