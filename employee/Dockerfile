FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="aui-employee"
LABEL org.opencontainers.image.version="1.0.0-SNAPSHOT"
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8082

EXPOSE 8082

COPY target/employee-${VERSION}.jar /opt/employee/employee.jar

CMD ["java", "-jar", "/opt/employee/employee.jar"]
