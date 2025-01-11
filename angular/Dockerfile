FROM nginx:1.27.3

LABEL org.opencontainers.image.title="aui-angular"
LABEL org.opencontainers.image.version="1.0.0-SNAPSHOT"
LABEL org.opencontainers.image.licenses="MIT"

ENV VERSION="1.0.0-SNAPSHOT"

ENV API_URL http://localhost:8080/

EXPOSE 80

COPY ./docker/nginx.conf /etc/nginx/nginx.conf
COPY ./dist/angular/browser /usr/share/nginx/html
