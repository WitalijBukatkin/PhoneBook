### The test project for DINO Systems
#### Implementing personal phonebook for each users
#### It use REST API for access

### Capabilities
- get list all users
- create/delete/get(by id)/update users
- create/delete/get(by id)/update contact for user
- get list all contacts for user
- find users by name
- find contact by phoneNumber

### REST API
| URL | HTTP Method | Body | Description |
| --- | ---- | ----| --- |
| /rest/users | GET | | Get users list |
| /rest/users/{ID} | GET  | | Get user by id |
| /rest/users |  POST | {"name":"User1","email":"test.email@mail.com","password":"Password","contacts":[]} | Create user |
| /rest/users | PUT | {"id":100,"name":"U","email":"test.em","password":"Passwo","contacts":[]} | Update user |
| /rest/users/{ID} | DELETE | | Delete user |
| /rest/users/by?name=test| GET |  | Find user by name |

| URL | HTTP Method | Body | Description |
| ---  | ---- | ----| --- |
| /rest/contacts?userId={USER_ID} | GET | | Get contacts list for userId|
| /rest/contacts/{ID}?userId={USER_ID}  | GET  | | Get contact for userId by id |
| /rest/contacts?userId={USER_ID} |  POST | {"userId":0,"firstName":"Contact New","secondName":"Test contact","email":"test.contact3@mail.com","phoneNumbers":["12","13"]} | Create contact for userId |
| /rest/contacts?userId={USER_ID}  | PUT | {"id":1,"userId":0,"firstName":"Contact","secondName":"Test contact","email":"test.contact3@mail.com","phoneNumbers":["12"]} | Update contact for userId |
| /rest/contacts/{ID}?userId={USER_ID}  | DELETE | | Delete contact for userId |
| /rest/contacts/by?phoneNumber={PHONE_NUMBER}?userId={USER_ID} | GET |  |Find contact for userId=0 by phone number |

### Curl example REST API located in curl_rest_tests.md file on root directory

### Uses
- Spring Boot 2
- Maven
- Java 8
- Junit 5

## For starting server exec: mvnw/mvnw.cmd with argument 'spring-boot:run'
### Example
`./mvnw spring-boot:run`

## For starting unit test exec: mvnw/mvnw.cmd with argument 'tests'
### Example
`./mvnw test`