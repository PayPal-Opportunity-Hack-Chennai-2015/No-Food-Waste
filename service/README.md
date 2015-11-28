# Backend Service Repo


# Testing using CURL
## create user
curl -v -X POST 'http://localhost:8080/user/create' --data '@user.json' -H "Content-Type: application/json" -H "Accept: application/json"

## create donate-food
curl -v -X POST 'http://localhost:8080/donate/create' --data '@donate.json' -H "Content-Type: application/json" -H "Accept: application/json"