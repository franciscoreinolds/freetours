<template>
    <div>
        <v-content>
            <v-container
                class="fill-height"
                fluid
            >
                <v-row
                align="center"
                justify="center"
                >
                    <v-card
                    shaped
                    elevation
                    class="elevation-12 card"
                    >
                        <h1
                        class = "pa-5"
                        >
                            Create a Tour
                        </h1>
                        <v-card-text>
                            <v-form>
                            <v-text-field
                                outlined
                                label="Tour Name"
                                name="tourname"
                                type="text"
                                class = "pt-12"
                            />

                            <v-text-field
                                outlined
                                label="Location"
                                name="location"
                                type="text"
                            />
                              <v-row>
                                <v-col>
                                    <v-menu
                                        ref="menu_date"
                                        v-model="menu_date"
                                        :close-on-content-click="false"
                                        :return-value.sync="date"
                                        transition="scale-transition"
                                        offset-y
                                        min-width="290px"
                                    >
                                        <template v-slot:activator="{ on }">
                                        <v-text-field
                                            outlined
                                            v-model="date"
                                            label="Tour Date"
                                            readonly
                                            v-on="on"
                                        ></v-text-field>
                                        </template>
                                        <v-date-picker v-model="date" no-title scrollable>
                                        <v-spacer></v-spacer>
                                        <v-btn text color="primary" @click="menu_date = false">Cancel</v-btn>
                                        <v-btn text color="primary" @click="$refs.menu_date.save(date)">OK</v-btn>
                                        </v-date-picker>
                                    </v-menu>
                                </v-col>
                                <v-col>
                                    <v-menu
                                        ref="start_time"
                                        v-model="start_time"
                                        :close-on-content-click="false"
                                        :nudge-right="40"
                                        :return-value.sync="time"
                                        transition="scale-transition"
                                        offset-y
                                        max-width="290px"
                                        min-width="290px"
                                    >
                                        <template v-slot:activator="{ on }">
                                            <v-text-field
                                                outlined
                                                v-model="time"
                                                label="Tour Start Time"
                                                readonly
                                                v-on="on"
                                            ></v-text-field>
                                        </template>
                                        <v-time-picker
                                            v-if="start_time"
                                            v-model="time"
                                            full-width
                                            @click:minute="$refs.start_time.save(time)"
                                        ></v-time-picker>
                                    </v-menu>
                                </v-col>
                                <v-col>
                                    <v-menu
                                        ref="end_time"
                                        v-model="end_time"
                                        :close-on-content-click="false"
                                        :nudge-right="40"
                                        :return-value.sync="time"
                                        transition="scale-transition"
                                        offset-y
                                        max-width="290px"
                                        min-width="290px"
                                    >
                                        <template v-slot:activator="{ on }">
                                            <v-text-field
                                                outlined
                                                v-model="time"
                                                label="Tour End Time"
                                                readonly
                                                v-on="on"
                                            ></v-text-field>
                                        </template>
                                        <v-time-picker
                                            v-if="end_time"
                                            v-model="time"
                                            full-width
                                            @click:minute="$refs.end_time.save(time)"
                                        ></v-time-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                                <v-text-field
                                    outlined
                                    label="Capacity"
                                    name="capacity"
                                    type="number"
                                />
                            <v-row>
                                <v-col
                                :cols = 12>
                                    <v-select
                                        v-model="value"
                                        :items="languages"
                                        item-text="language"
                                        item-value="value"
                                        chips
                                        label="In what language(s) will the Tour be done in?"
                                        outlined
                                        multiple
                                        height = 50px
                                    ></v-select>
                                </v-col>
                            </v-row>
                            <v-textarea
                            outlined
                            name="description"
                            label="Tour Description"
                            ></v-textarea>
                            <v-alert
                            v-if="success_alert"
                            type="success">
                                I'm a success alert.
                            </v-alert>
                            <v-layout
                            wrap
                            justify-center>
                                <v-flex
                                xs8
                                >
                                    <!--
                                        Main (Map) Card
                                    -->
                                    <v-card
                                    height = "500px"
                                    >
                                        <div
                                        class = "mapHolder"
                                        >
                                            <l-map
                                                ref="myMap"
                                                :zoom="zoom"
                                                :center="center"
                                                :zoomControl = "disabled"
                                                :options="{
                                                    doubleClickZoom:false,
                                                    zoomControl : false
                                                }"
                                                @click="clearMarkerInfo"
                                                @dblclick="loggerino"
                                                >
                                                <l-tile-layer :url="url">
                                                </l-tile-layer>
                                                <v-geosearch
                                                :options="geosearchOptions"
                                                >
                                                </v-geosearch>

                                                <!-- Provisional Marker -->
                                                
                                                <l-marker
                                                v-if="current_lattitude"
                                                :lat-lng="[current_lattitude, current_longitude]"
                                                :icon = "red_icon"
                                                >
                                                    <l-icon
                                                    :icon-size="[37,37]"
                                                    :icon-anchor="[16,37]"
                                                    :iconUrl="require('leaflet/dist/images/marker-24.png')"
                                                    >
                                                    </l-icon>
                                                </l-marker>
                                                
                                                <!-- Saved Marker(s) -->

                                                <l-marker
                                                    v-for="(marker,index) in markers"
                                                    :key="index"
                                                    :lat-lng="[marker.position.lattitude, marker.position.longitude]"
                                                    @click="markerInfo(index, marker)"
                                                    @dblclick="removeMarker(index)"
                                                >
                                                    <l-icon
                                                    :icon-size="marker.icon_size"
                                                    :icon-anchor="marker.icon_anchor"
                                                    :iconUrl="require('leaflet/dist/images/marker-icon.png')"
                                                    >
                                                    </l-icon>
                                                </l-marker>
                                            </l-map>
                                        </div>
                                    </v-card>
                                </v-flex>
                                <v-flex
                                xs4
                                >
                                    <!--
                                        Side Card - Add Marker
                                    -->

                                    <v-card
                                    height = "500px"
                                    v-if="current_lattitude || marker_index != null"
                                    >
                                        <v-card-title>
                                            Marker Information
                                        </v-card-title>
                                        <v-card-subtitle
                                            v-if="marker_index != null"
                                        >
                                            Marker Order: {{marker_index + 1}}
                                        </v-card-subtitle>
                                        <v-card-text>
                                            
                                            Marker Name:
                                            <v-text-field
                                                v-model="marker_name"
                                                outlined   
                                            >
                                            </v-text-field>
                                            
                                            Marker Description:
                                            <v-text-field
                                                v-model="marker_description"
                                                outlined
                                            >
                                            </v-text-field>
                                            
                                            <h5>
                                                Lattitude: {{marker_lattitude}}
                                            </h5>
                                            
                                            <h5>
                                                Longitude: {{marker_longitude}}
                                            </h5>

                                        </v-card-text>
                                        <div
                                        class = "text-center pb-3 mb-3 pt-0">
                                            <v-btn
                                            @click="saveMarkerInfo"
                                            >
                                                Submit Marker Info
                                            </v-btn>
                                        </div>
                                        <div
                                        class = "text-center pb-3 mb-3 pt-0">
                                            <v-btn
                                            @click="clearMarkerInfo(index)"
                                            >
                                                Clear Info
                                            </v-btn>
                                        </div>
                                    </v-card>

                                    <!--
                                        Side Card - Marker List
                                    -->

                                    <v-card
                                    height = "500px"
                                    width = "100%"
                                    v-else
                                    >
                                        <v-list
                                        style="max-height: 500px;"
                                        class="overflow-y-auto"
                                        >
                                            <v-list-item
                                            v-for="(marker, index) in markers"
                                            :key="index"
                                            class = "pb-1 ps-1 pe-1 pt-0"
                                            >
                                                <v-list-item-content
                                                class = "pa-0"
                                                >
                                                    <v-card
                                                    color="#ffeeee"
                                                    width = "100%"
                                                    @mouseover="mouseOverMarker(index)"
                                                    @mouseleave="mouseLeavesMarker(index)"
                                                    @click="editMarker(index)"
                                                    >
                                                        <v-card-text>
                                                            <h3>
                                                                Stop #{{index + 1}}
                                                            </h3>
                                                            <h4>
                                                                Marker Name: {{marker.name}}
                                                            </h4>
                                                            <h4>
                                                                Marker Description: {{marker.description}}
                                                            </h4>
                                                        </v-card-text>
                                                    </v-card>
                                                </v-list-item-content>
                                        </v-list-item>
                                        </v-list>
                                    </v-card>
                                </v-flex>
                            </v-layout>
                            <br>
                            <v-file-input
                            outlined
                            small-chips
                            multiple
                            accept = ".png"
                            label="Upload some pictures of what people will see in your tour"
                            >
                            </v-file-input>
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-layout justify-center>
                                <v-btn
                                large
                                primary
                                >
                                    Submit Tour
                                </v-btn>
                            </v-layout>
                        </v-card-actions>
                        <br>
                    </v-card>
                </v-row>
            </v-container>
        </v-content>
    </div>
</template>

<script>
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import VGeosearch from 'vue2-leaflet-geosearch';

export default {
    name : "CreateTour",
    components : {
        VGeosearch
    },
    data () {
      return {
        menu_date : false,
        start_time : false,
        end_time : false,
        date: new Date().toISOString().substr(0, 10),
        time: null,
        languages : [
            {
                language : "Portuguese",
                value : 1
            },
            {
                language : "Spanish",
                value : 2
            },
            {
                language : "French",
                value : 3
            },
            {
                language : "German",
                value : 4
            },
            {
                language : "English",
                value : 5
            },
            {
                language : "Dutch",
                value : 6
            },
        ],
        markers : [],
        url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
        zoom: 5,
        center: [38.72, -9.13],
        bounds: null,
        geosearchOptions: { // options regarding GeoSearch component
            provider: new OpenStreetMapProvider(),
            style : 'bar',
            autoComplete: true,
            autoCompleteDelay : 250,
            maxMarkers : 0,
            autoClose : true
        },
        red_icon: L.icon({ // placeholder icon
            iconUrl: require('leaflet/dist/images/marker-24.png'),
            iconSize: [37, 37],
            iconAnchor: [16, 37]
        }),
        current_lattitude : null, // placeholder lattitude
        current_longitude : null, // placeholder longitude
        marker_index : null, // index of a saved marker
        marker_name : null, // name of a saved marker
        marker_description : null, // description of a saved marker
        marker_lattitude : null, // lattitude of a saved marker
        marker_longitude : null, // longitude of a saved marker
        success_alert : false
      }
    },
    methods: {
        loggerino(e) {
            // sets sidecard info upon clicking in the map
            this.marker_index = null;
            this.marker_name = null;
            this.marker_description = null;
            this.marker_lattitude = e.latlng.lat;
            this.marker_longitude = e.latlng.lng;
            this.current_lattitude = this.marker_lattitude;
            this.current_longitude = this.marker_longitude;
        },
        removeMarker(index) {
            // removes a marker from the marker array and thus the map
            console.log("Remove Marker at index: " + index);
            this.markers.splice(index, 1);
            this.clearMarkerInfo();
        },
        saveMarkerInfo() {
            // saves / updates the information pertaining to a marker
            if (this.marker_index != null) {
                console.log("Asasdasd: " + this.marker_index);
                this.markers[this.marker_index].name = this.marker_name;
                this.markers[this.marker_index].description = this.marker_description;
                this.markers[this.marker_index].position.lattitude = this.marker_lattitude;
                this.markers[this.marker_index].position.longitude = this.marker_longitude;
                this.markers[this.marker_index].icon_size = [37,37];
                this.markers[this.marker_index].icon_anchor = [16,37];
            }
            else {
                this.markers.push(
                    {
                        position : {
                            lattitude : this.marker_lattitude,
                            longitude : this.marker_longitude
                        },
                        name : this.marker_name,
                        description : this.marker_description,
                        icon_size : [37,37],
                        icon_anchor : [16,37]
                    }
                );
            }
            //this.success_alert = true;
            this.clearMarkerInfo();
        },
        markerInfo(index, marker) {
            // sets the sidecard info upon clicking of a marker
            console.log("MarkerInfo: " + index);
            this.marker_index = index;
            this.marker_name = marker.name;
            this.marker_description = marker.description;
            this.marker_lattitude = marker.position.lattitude;
            this.marker_longitude = marker.position.longitude;
            this.current_lattitude = null;
            this.current_longitude = null;
        },
        clearMarkerInfo(index) {
            // resets placeholder marker info
            this.marker_index = null;
            this.marker_name = null;
            this.marker_description = null;
            this.marker_lattitude = null;
            this.marker_longitude = null;
            this.current_lattitude = null;
            this.current_longitude = null;
            //this.markers[index].icon_size = [37,37];
            //this.markers[index].icon_anchor = [16,37];
            //this.success_alert = false;
        },
        mouseOverMarker(index) {
            this.markers[index].icon_size = [50,50];
        },
        mouseLeavesMarker(index) {
            this.markers[index].icon_size = [37,37];
        },
        editMarker(index) {
            this.marker_index = index;
            this.marker_name = this.markers[index].name;
            this.marker_description = this.markers[index].description;
            this.marker_lattitude = this.markers[index].position.lattitude;
            this.marker_longitude = this.markers[index].position.longitude;
            this.markers[index].icon_size = [50,50];
            this.markers[index].icon_anchor = [16,37];
            //this.current_lattitude = this.markers[index].position.lattitude;
            //this.current_longitude = this.markers[index].position.longitude;
        }
    }
}
</script>

<style>

@import 'https://unpkg.com/leaflet-geosearch@2.6.0/assets/css/leaflet.css';

.mapHolder {
    height : 500px;
}

.marker_list {
    height : 500px;
}

.popup {
    max-height : 300px;
}

</style>