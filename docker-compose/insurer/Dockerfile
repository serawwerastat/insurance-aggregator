FROM java:openjdk-8-jdk

MAINTAINER gatis.jezuns@sapiens.com

ENV UID=30401
ENV REST_WORK=/opt/insurer
ENV JAVA_OPTS=-Xmx128M

RUN useradd -r -u ${UID} -m -c "rest role account" -d ${REST_WORK} -s /bin/false insurer-rest-usr

COPY maven/insurer.jar ${REST_WORK}/app.jar
RUN chown insurer-rest-usr -R ${REST_WORK}

USER insurer-rest-usr

EXPOSE 8080

CMD java ${JAVA_OPTS} ${APP_ARGUMENTS} -cp ${REST_WORK}/app.jar -Dloader.main=${MAIN_CLASS} org.springframework.boot.loader.PropertiesLauncher
