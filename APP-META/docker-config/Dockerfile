FROM openjdk:8

# Install Maven
ARG MAVEN_VERSION=3.6.3
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

RUN mkdir -p /usr/asteroids-api
WORKDIR /usr/asteroids-api

COPY src /usr/asteroids-api/src
COPY pom.xml /usr/asteroids-api
RUN cd /usr/asteroids-api

RUN mvn clean install -Dmaven.test.skip=true

CMD mvn test