[![Build Status](https://travis-ci.org/sbakiu/Allianz.svg?branch=development)](https://travis-ci.org/sbakiu/Allianz)
# Allianz - Docker orchestration, service discovery, Vagrant

Create a small web application which on request retrieves several rows of data from the database of your choice.
 
Dockerize the web app and  the database. The application should resolve database configuration using the service discovery tool of your choice.
 
Web application and database should run on different virtual nodes, configured by Vagrant.

## Architecture
![Architecture of the tool](architecture/Architecture.jpg?raw=true "Architecture of the developed tool")

Initially, a virtual machine is create on VirtualBox, dockerHostVM. On top of this VM, three docker containers are built:
* __dataContainer__ used to store the data for the database and logs for the application
* __mysqlContainer__ used for the MySQL installation
* __applicationContainer__ used for hosting the application

Service discovery is performed with simple docker links.

## Run
```vagrant up --no-parallel```

## GET Request
```cURL http://localhost:8888/simpleproject/rest/StudentService/students```

## Tools used
* [Vagrant 1.8.5](https://www.vagrantup.com/) 
* [Docker 1.12](https://www.docker.com/)
* [Tomcat 8](https://tomcat.apache.org/)
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [MySQL 5.7](http://dev.mysql.com/downloads/)



