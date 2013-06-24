#!/bin/zsh

host="http://localhost:22222/cgi-bin/WebObjects/NotificationRabbitBegins.woa"

if [ $# -ne 1 ]
then
  echo "Usage: `basename $0` num"
  exit 1
fi

for i in `seq 1 3 $1`
do
    echo -n -e "\r$i"

    curl --write-out %{http_code} -X POST --data "{'message':'hi Superman, won\'t you put your underwear under your pants sometimes?'}" "$host/ra/device/superman/notification.json" > /dev/null 2>&1
    curl --write-out %{http_code} -X POST --data "{'message':'Hey Batman, nice batphone, but mine\'s better!'}" "$host/ra/device/batman/notification.json" > /dev/null 2>&1
    curl --write-out %{http_code} -X POST --data "{'message':'Help me Obi-Wan Kenobi, you\'re my only hope!'}" "$host/ra/device/okenobi/notification.json" > /dev/null 2>&1

done
echo ""
