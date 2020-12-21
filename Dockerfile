FROM sonarqube:lts
ENV SONARQUBE_HOME /opt/sonarqube
COPY sonar-flutter-plugin/target/*.jar $SONARQUBE_HOME/extensions/plugins/
EXPOSE 9000