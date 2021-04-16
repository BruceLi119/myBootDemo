#!/bin/sh
java -jar -Dspring.config.location=../config/application.properties ../lib/distinctionLog-1.0-SNAPSHOT.jar >/dev/null 2>&1 &

echo $! > tpids

echo start success!
