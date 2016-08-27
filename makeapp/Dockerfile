FROM tomcat:8-jre8
MAINTAINER Sadik Bakiu

ARG PORT=3306
ARG HOST=mysql
ARG DATABASE=allianz
ARG USERNAME=allianz
ARG PASSWORD=allianz
ARG CONFIG_FILE_PATH=/usr/local/tomcat/webapps/simpleproject/WEB-INF/classes/db.properties

ADD config/simpleproject.war /tmp/

RUN unzip /tmp/simpleproject.war -d /usr/local/tomcat/webapps/simpleproject/

ADD config/*.properties /usr/local/tomcat/webapps/simpleproject/WEB-INF/classes/

RUN sed -i "s#PORT#${PORT}#g" ${CONFIG_FILE_PATH} && \
	sed -i "s#HOST#${HOST}#g" ${CONFIG_FILE_PATH} && \	
	sed -i "s#DATABASE#${DATABASE}#g" ${CONFIG_FILE_PATH} && \
	sed -i "s#USERNAME#${USERNAME}#g" ${CONFIG_FILE_PATH} && \
	sed -i "s#PASSWORD#${PASSWORD}#g" ${CONFIG_FILE_PATH}

CMD ["catalina.sh", "run"]
