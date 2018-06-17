FROM payara/micro:4.181
RUN wget -O /opt/payara/mysql-connector-java-5.1.23.jar http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.23/mysql-connector-java-5.1.23.jar
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar", "--addJars", "/opt/payara/mysql-connector-java-5.1.23.jar","--deploymentDir","/opt/payara/deployments"]
