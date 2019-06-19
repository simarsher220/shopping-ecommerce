FROM openjdk:10-jre-slim

LABEL Name="Ecommerce Service" \
            Product="Ratings"

EXPOSE 8080

RUN mkdir /container
COPY build/libs/product-0.0.1-SNAPSHOT.jar /container/ratings-api.jar

WORKDIR /container

ENTRYPOINT exec java $JAVA_OPTS -jar ratings-api.jar