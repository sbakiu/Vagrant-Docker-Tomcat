FROM tomcat:8-jre7
MAINTAINER Sadik Bakiu

ARG PORT=3306
ARG HOST=mysql
ARG DATABASE=allianz
ARG USERNAME=allianz
ARG PASSWORD=allianz
ARG VERSION=0.0.1-SNAPSHOT
ARG CONFIG_FILE_PATH=/usr/local/tomcat/webapps/simpleproject/WEB-INF/db.properties

ADD jars/*.jar /usr/local/tomcat/lib/
ADD config/simpleproject-${VERSION}.war /tmp/

RUN unzip /tmp/simpleproject-0.0.1-SNAPSHOT.war -d /usr/local/tomcat/webapps/simpleproject/

ADD config/*.properties /usr/local/tomcat/webapps/simpleproject/WEB-INF/

RUN sed -i "s#PORT#${PORT}#g" ${CONFIG_FILE_PATH} && \
	sed -i "s#HOST#${HOST}#g" ${CONFIG_FILE_PATH} && \	
	sed -i "s#DATABASE#${DATABASE}#g" ${CONFIG_FILE_PATH} && \
	sed -i "s#USERNAME#${USERNAME}#g" ${CONFIG_FILE_PATH} && \
	sed -i "s#PASSWORD#${PASSWORD}#g" ${CONFIG_FILE_PATH} 


CMD ["catalina.sh", "run"]
