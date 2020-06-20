# Deployment

## Packaging Backend
For packaging backend application in a WAR file, you just need to go to the backend folder and run:
```
./mvnw install
```

## Deploy Components

In this folder, you just need to run:
```
docker-compose up --build
```

This command builds the docker images for frontend and backend, and then initializes a container for each service (frontend, backend, database).
