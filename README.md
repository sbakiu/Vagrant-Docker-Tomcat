[![Build Status](https://travis-ci.org/sbakiu/Allianz.svg?branch=development)](https://travis-ci.org/sbakiu/Allianz)
# Allianz - Docker orchestration, service discovery, Vagrant

Create a small web application which on request retrieves several rows of data from the database of your choice.
 
Dockerize the web app and  the database. The application should resolve database configuration using the service discovery tool of your choice.
 
Web application and database should run on different virtual nodes, configured by Vagrant.

## Run
```vagrant up --no-parallel```

## GET Request
```cURL http://localhost:8888/simpleproject/rest/StudentService/students```
