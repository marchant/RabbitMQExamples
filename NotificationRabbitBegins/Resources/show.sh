#!/bin/zsh

host="http://localhost:22222/cgi-bin/WebObjects/NotificationRabbitBegins.woa"


if [ $# -ne 1 ]
then
  echo "Usage: `basename $0` logs or devices"
  exit 1
fi

curl -X GET "$host/ra/$1"
