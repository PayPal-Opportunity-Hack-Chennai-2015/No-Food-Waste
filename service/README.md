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

## Sample CURL Request for setup
`curl -v -X POST 'http://localhost:8080/user/create' --data '@user.json' -H "Content-Type: application/json" -H "Accept: application/json"`
