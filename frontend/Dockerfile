# build stage
FROM node:latest as build-stage

# make the 'app' folder the current working directory
WORKDIR /app

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory --> /app
COPY ./ .

# Macs don't download this image
COPY deployment_utils/marker-24.png ./node_modules/leaflet/dist/images/marker-24.png

# Env variable
ARG VUE_APP_API_URL
ENV VUE_APP_API_URL $VUE_APP_API_URL
ARG VUE_APP_FRONTEND_URL
ENV VUE_APP_FRONTEND_URL $VUE_APP_FRONTEND_URL

# build app for production with minification
RUN npm run build


# production stage
FROM nginx as production-stage
RUN mkdir /app
COPY --from=build-stage /app/dist /app
COPY nginx.conf /etc/nginx/nginx.conf
