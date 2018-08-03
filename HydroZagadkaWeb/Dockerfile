FROM jboss/wildfly

ADD target/HydroZagadka.war /opt/jboss/wildfly/standalone/deployments/

RUN mkdir -p /opt/jboss/patataje-upload
RUN chmod 777 /opt/jboss/patataje-upload

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
