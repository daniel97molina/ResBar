FROM mariadb:10.1
ENV MYSQL_ROOT_PASSWORD 1234
ENV MYSQL_USER resbar
ENV MYSQL_PASSWORD Restaurante2018
ENV MYSQL_DATABASE resbar
ADD ResBar.sql /docker-entrypoint-initdb.d

