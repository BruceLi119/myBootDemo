#!/bin/sh

tpid=$(cat ./tpids)

if [ ${tpid} ]; then
	echo 'kill Process'
	kill -9 $tpid
fi
