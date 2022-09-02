FROM alpine:latest

# User ID 
ARG UID=1000
# create directory for gatling install
RUN mkdir -p gatling

RUN apk add --update openjdk17 tzdata curl unzip bash busybox-extras linux-headers &&  rm -rf /var/cache/apk/*
# install gatling
#Installing Gatling 3.8.3
ENV GATLING_VERSION=3.8.3
RUN mkdir -p /opt/gatling \
    && wget -O /tmp/gatling.zip https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/$GATLING_VERSION/gatling-charts-highcharts-bundle-$GATLING_VERSION-bundle.zip \
    && unzip /tmp/gatling.zip -d /tmp/ && mv /tmp/gatling-charts-highcharts-bundle-$GATLING_VERSION/* /opt/gatling/ \
    && rm -rf /tmp/gatling-charts-highcharts-bundle-$GATLING_VERSION /tmp/gatling.zip 

COPY . /opt/gatling/
COPY gatling.conf /opt/gatling/conf/gatling.conf

# change context to gatling directory
WORKDIR  /opt/gatling

# set directories below to be mountable from host
VOLUME ["/opt/gatling/results","/opt/gatling/user-files"]

# set environment variables
ENV PATH /opt/gatling/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
ENV GATLING_HOME /opt/gatling

RUN addgroup admin && adduser -u $UID -S gatling -G admin \
    && chown -R gatling:admin /opt/gatling/

USER gatling