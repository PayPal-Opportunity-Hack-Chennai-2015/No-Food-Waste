# Backend Service Repo

##Database setup

### Postgresql database is required
Execute the following command from location `No-Food-Waste/service/db`.

`./setup_db.sh`

The above script will create a postgresql user with name 'nfm' and password 'nfm'. The database created also has the same name.
Database can be dropped at any point using `drop_db.sh` on the same folder.

## Starting the service
Execute the following command from location `No-Food-Waste/service/`.

`./run.sh`
The above script will start the service on port 8080. It can be stopped at any point using ^C (CTRL-C) and restarted with same.

## API Requests using CURL

### create user
`curl -v -X POST 'http://localhost:8080/user/create' --data '@user.json' -H "Content-Type: application/json"`

### get user list
`curl -v -X GET 'http://localhost:8080/user'`

### get single user
`curl -v -X GET 'http://localhost:8080/user/{id}'`

### create donate-food
`curl -v -X POST 'http://localhost:8080/donate/create' --data '@donate.json' -H "Content-Type: application/json"`

### get donate-food list
`curl -v -X GET 'http://localhost:8080/donate'`

### get donate-food list with distance
`curl -v -X GET 'http://localhost:8080/donate/distance'

### update donate-food status
`curl -v -X PUT 'http://localhost:8080/donate/updateStatus' --data '@donate-update-status.json' -H "Content-Type: application/json"`

### get single donate-food
`curl -v -X GET 'http://localhost:8080/donate/{id}'`

### create food-consumer
`curl -v -X POST 'http://localhost:8080/consumer/create' --data '@consumer.json' -H "Content-Type: application/json"`

### get food-consumer list
`curl -v -X GET 'http://localhost:8080/consumer'`

### get single food-consumer
`curl -v -X GET 'http://localhost:8080/consumer/{id}'`

### get summary of all
`curl -v -X GET 'http://localhost:8080/summary'`



