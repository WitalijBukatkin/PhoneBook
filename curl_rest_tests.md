##### Get users list
`curl -s http://localhost:8080/rest/users`

##### Create user
`curl -s -X POST -d '{"name":"User1","email":"test.email@mail.com","password":"Password","contacts":[]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/users`

##### Get user by id
`curl -s http://localhost:8080/rest/users/100`

##### Update user
`curl -s -X PUT -d '{"id":100,"name":"U","email":"test.em","password":"Passwo","contacts":[]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/users/100`

##### Delete user
`curl -s -X DELETE http://localhost:8080/rest/users/100`

##### Find user by name
`curl -s http://localhost:8080/rest/users/by?name=est`

# Get contacts list for userId=0
`curl -s http://localhost:8080/rest/contacts?userId=0`

##### Create contact for userId=0
`curl -s -X POST -d '{"userId":0,"firstName":"Contact New","secondName":"Test contact","email":"test.contact3@mail.com","phoneNumbers":["12","13"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/contacts?userId=0`

##### Get contact for userId=0 by id
`curl -s http://localhost:8080/rest/contacts/100?userId=0`

##### Update contact for userId=0
`curl -s -X PUT -d '{"id":1,"userId":0,"firstName":"Contact","secondName":"Test contact","email":"test.contact3@mail.com","phoneNumbers":["12"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/contacts/100?userId=0`

##### Delete contact for userId=0
`curl -s -X DELETE http://localhost:8080/rest/contacts/100?userId=0`

##### Find contact for userId=0 by phone number
`curl -s http://localhost:8080/rest/contacts/by?phoneNumber=12&userId=0`