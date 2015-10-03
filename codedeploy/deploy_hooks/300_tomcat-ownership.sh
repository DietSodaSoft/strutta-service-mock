#!/bin/bash

mv /opt/bitnami/apache-tomcat/webapps/schultzco-webservices.war /opt/bitnami/apache-tomcat/webapps/ROOT.war
chown tomcat:tomcat /opt/bitnami/apache-tomcat/webapps/ROOT.war


if [ $? != 0 ]; then
    echo "Unable to change war ownership"
    exit 1
fi

exit 0