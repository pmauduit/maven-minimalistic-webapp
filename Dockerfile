FROM maven:slim

ADD . /usr/src
WORKDIR /usr/src
RUN mvn clean install -Dmaven.repo.local=/usr/src/.m2
RUN mvn jetty:help -Dmaven.repo.local=/usr/src/.m2
RUN chmod 777 -R /usr/src
ENV JAVA_OPTIONS ""


CMD [ "/bin/sh", "-c", "exec mvn -o ${JAVA_OPTIONS} -Dmaven.repo.local=/usr/src/.m2 jetty:run" ]

