RabbitMQExamples
================

Here are the examples for the WOWODC 2013 presentation on RabbitMQ

 
Download [RabbitMQ Server](http://www.rabbitmq.com/download.html)

To launch the server, you can execute the script 
`$ sbin/rabbitmq-server`
 which is in the rabbitmq server directory.  
To enable the management plugin run the following script : 
`$ sbin/rabbitmq-plugins enable rabbitmq_management`

##RabbitBasicExample :

This is the first demo project, it's just Java.

Just one queue used as hub.

this program :
*	Pushes a new message in the queue every second
*	A thread consume messages from this queue and print it


##Model

All following applications have the same model.

The model defines two entities :
*	Device : username, platform of his phone. Usernames are uniques.
*	Log : a relationship with the device, the message, and the duration of the sending.

##Protobuf

The protobuf (use for serialization) has two messages :
*	Log
*	Device


##NotificationFullWO

Defines the REST routes : 
*	POST http://host/cgi-bin/WebObjects/NotificationFullWO.woa/ra/device.json  
		{  
			"user":"my username",  
			"platform":"IOS | ANDROID | WINDOWS_PHONE"  
		}  		
*	POST http://host/cgi-bin/WebObjects/NotificationFullWO.woa/ra/device/username/notification.json  
		{  
			"message":"your message"  
		}  	

When there is a notification request the right sender is called and then the notification is logged.


##NotificationRabbitBegins

A direct exchange is set and there is a queue for each platform.
When there is a notification request the application just publishes a messages on the notification exchange.
the senders on the three queues consume messages, send them and log the result.


##NotificationRabbitReturns

Same architecture than **NotificationRabbitBegins** except that when a sender have send the message it publishes all informations for the log in the logger exchange. There is a logger application which consume the messages in the logger queue and write into database.

