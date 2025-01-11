FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="aui-company"
LABEL org.opencontainers.image.version="1.0.0-SNAPSHOT"
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8081

ENV EMPLOYEE_URL=http://localhost:8082/employee

EXPOSE 8081

COPY target/company-${VERSION}.jar /opt/company/company.jar

CMD ["java", "-jar", "/opt/company/company.jar"]
