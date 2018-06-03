FROM payara/micro:4.181
ADD mysql-connector-java-5.1.23.jar /home
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar", "--addJars", "/home/mysql-connector-java-5.1.23.jar","--deploymentDir","/opt/payara/deployments"]
