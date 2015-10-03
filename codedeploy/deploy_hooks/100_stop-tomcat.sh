#!/bin/bash

/opt/bitnami/ctlscript.sh stop tomcat

if [ $? != 0 ]; then
    echo "Unable to stop tomcat via ctlscript"
    exit 1
fi

exit 0

