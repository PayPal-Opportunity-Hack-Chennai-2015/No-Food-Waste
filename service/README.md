# Backend Service Repo


# Testing (create user)
curl -v -X POST 'http://localhost:8080/user/create' --data '@user.json' -H "Content-Type: application/json" -H "Accept: application/json"