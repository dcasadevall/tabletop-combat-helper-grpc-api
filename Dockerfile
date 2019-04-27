# https://github.com/GoogleCloudPlatform/openjdk-runtime
FROM gcr.io/google_appengine/openjdk8

RUN sed -i '/jessie-updates/d' /etc/apt/sources.list  # Now archived
RUN echo 'deb [check-valid-until=no] http://archive.debian.org/debian jessie-backports main' > /etc/apt/sources.list.d/jessie-backports.list \
    && apt-get -o Acquire::Check-Valid-Until=false update \
    && apt-get install --no-install-recommends -y -q ca-certificates \
    && apt-get -y -q upgrade \
    && apt-get install --no-install-recommends -y -t jessie-backports openjdk-8-jre-headless \
    && rm -rf /var/lib/apt/lists/*

ADD ./server/build/libs/server.jar /tch-api/server.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/tch-api/server.jar"]
