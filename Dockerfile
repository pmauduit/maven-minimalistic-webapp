FROM tomcat

RUN rm -rf /usr/local/tomcat/webapps/*
ADD  target/minimalistic.war /usr/local/tomcat/webapps/

