# $1 - Geoname ID; $2 - Name; $3 - ASCII Name; $4 - Alternate Names; $5 - Latitude;
# $6 - Longitude; $7 - Feature Class; $8 - Feature Code; $9 - Country Code; $10 - Country Code 2;
# $11 - Admin1 Code; $12 - Admin2 Code; $13 - Admin3 Code; $14 - Admin4 Code; $15 - Population;
# $16 - Elevation; $17 - DIgital Elevation Model; $18 - Timezone; $19 - Modification date; $20 - Country; $21 - Coordinates
BEGIN{FS=";"}
# every city with 40k or more population and country name is not empty
$15 >= 40000 && $20 ~ /.+/ && $20 !~ /Country/        {country_name=gensub(/'/, "''", "g", $20); city_name=gensub(/'/, "''", "g", $2);
                                                      countries[country_name]; cities[city_name][0]=$5; cities[city_name][1]=$6; cities[city_name][2]=country_name}
END{insertcCountries(); insertCities()}

function insertcCountries(){
  for(country in countries)
    print "INSERT INTO country(name) VALUES ('"country"');"
}

function insertCities(){
  for(city in cities)
    print "INSERT INTO city(country_id, latitude, longitude, name) VALUES ((SELECT id FROM country WHERE name='"cities[city][2]"'), "cities[city][0]", "cities[city][1]", '"city"');"
}
