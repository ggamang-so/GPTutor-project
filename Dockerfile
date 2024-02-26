FROM openjdk:17-jdk-slim

WORKDIR /app

# COPY만  docker-compose 파일의 위치를 기반으로 작동함
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean build

ENV JAR_PATH=./build/libs
RUN mv ${JAR_PATH}/*.jar ./app.jar

ENV DB_USERNAME=gptutor
ENV DB_PASSWORD=test
ENV DB_URL=jdbc:mariadb://svc.sel5.cloudtype.app:30791/board
ENV API_KEY=""
ENV KAKAO_OAUTH_CLIENT_ID=""
ENV KAKAO_OAUTH_CLIENT_SECRET=""

LABEL authors="hyunwoo"

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "app.jar"]

CMD [ "--stacktrace", "--scan" ]