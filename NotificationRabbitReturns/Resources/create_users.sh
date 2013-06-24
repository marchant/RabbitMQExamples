#!/bin/zsh

host="http://localhost:11111/cgi-bin/WebObjects/NotificationRabbitReturns.woa"

curl -X POST --data "{'user':'batman', 'platform':'ANDROID'}" "$host/ra/device.json" > /dev/null 2>&1
curl -X POST --data "{'user':'superman', 'platform':'IOS'}" "$host/ra/device.json" > /dev/null 2>&1
curl -X POST --data "{'user':'okenobi', 'platform':'WINDOWS_PHONE'}" "$host/ra/device.json" > /dev/null 2>&1
