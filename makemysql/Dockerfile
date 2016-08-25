FROM mysql:5.7
MAINTAINER Sadik Bakiu

#Add the schema file to docker-entrypoint-initdb.d, content of which is run on mysqld startup
ADD files/ /docker-entrypoint-initdb.d/
 
CMD while true; do sleep 1000; done

EXPOSE 3306