FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="aui-gateway"
LABEL org.opencontainers.image.version="1.0.0-SNAPSHOT"
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV COMPANY_URL=http://localhost:8081
ENV EMPLOYEE_URL=http://localhost:8082

EXPOSE 8080

COPY target/gateway-${VERSION}.jar /opt/gateway/gateway.jar

CMD ["java", "-jar", "/opt/gateway/gateway.jar"]
