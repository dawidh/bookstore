# bookstore api

- deafult port is 8888
- H2 database: http://localhost:8888/h2_console/
  - user: sa
  - driver class: org.h2.Driver
  - jdbc url: jdbc:h2:mem:bookstore
- swagger: localhost:8888/swagger-ui.html 

## endpoints
- /api/book/
  - /GET - returns all books </br>
  This method has no arguments.
  - /POST - adds a book
  Methods has requsted body argument, Book object. Book object contains Author, Title and ISBN number. Author's firstname or lastname have to starts with 'A' letter. ISBN numer is unique string
  
## examples of cURL request:
### /POST
#### request
- `curl -H "Content-Type: application/json" -X POST -d {\"author\":\""Author Name\"",\"title\":\""Some Title\"",\"isbn\":\""Sample ISBN\""} http://localhost:8888/api/book`
#### response
- `{"id":1,"author":"Author Name","title":"Some Title","isbn":"Sample ISBN"}`
### /GET
#### request
- `curl -H "Content-Type: application/json" -X GET http://localhost:8888/api/book`
#### response
- `[{"id":1,"author":"Author Name","title":"Some Title","isbn":"Sample ISBN"},{"id":2,"author":"Second Author","title":"Another Title","isbn":"Another Sample ISBN"}]`
