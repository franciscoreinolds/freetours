# $1 - Geoname ID; $2 - Name; $3 - ASCII Name; $4 - Alternate Names; $5 - Latitude;
# $6 - Longitude; $7 - Feature Class; $8 - Feature Code; $9 - Country Code; $10 - Country Code 2;
# $11 - Admin1 Code; $12 - Admin2 Code; $13 - Admin3 Code; $14 - Admin4 Code; $15 - Population;
# $16 - Elevation; $17 - DIgital Elevation Model; $18 - Timezone; $19 - Modification date; $20 - Country; $21 - Coordinates
BEGIN{FS=";"}
$15 >= 40000 && $20 ~ /.+/        {countries[$20]; cities[$2][0]=$5; cities[$2][1]=$6; cities[$2][2]=$20}
END{insertcCountries(); insertCities()}

function insertcCountries(){
  for(country in countries)
    print "INSERT INTO country (name) VALUES (\""country"\");"
}

function insertCities(){
  for(city in cities)
    print "INSERT INTO city (name, latitude, longitude, country_id) VALUES (\""city"\", \""cities[city][0]"\", \""cities[city][1]"\", SELECT id FROM country WHERE name=\""cities[city][2]"\")"
}
