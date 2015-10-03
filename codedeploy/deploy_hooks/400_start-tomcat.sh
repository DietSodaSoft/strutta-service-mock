#!/bin/bash

/opt/bitnami/ctlscript.sh start tomcat

if [ $? != 0 ]; then
    echo "Unable to start tomcat via ctlscript"
    exit 1
fi

exit 0

