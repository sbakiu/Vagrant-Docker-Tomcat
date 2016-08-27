FROM mysql:5.7
MAINTAINER Sadik Bakiu

#Add the schema file to docker-entrypoint-initdb.d, content of which is run on mysqld startup
ADD data/ /docker-entrypoint-initdb.d/

EXPOSE 3306

CMD ["mysqld"]