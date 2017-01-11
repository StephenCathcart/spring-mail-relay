##Spring Mail Relay

###Outline
Spring Mail Relay is a RESTful API starter service for sending mail. The idea is that the service will be public facing and can forward requests to an internal SMTP server; this would normally be locked down behind your corporate firewall and would otherwise be unavailable from your cloud application. This way only the mail relay service needs to granted access rather then every specific cloud application.

####cURL
You can test the service with the below cURL command. Requires a request JSON file similar to below:
```javascript
	curl -u username:password -v -H "Content-Type: application/json" --data "@request.json" http://localhost:8080/mail-relay/send
```

####REST Request
The mail relay application expects a POST request containing the following JSON payload, note that the *'to'*, *'cc'* and *'bcc'* fields are arrays:
```javascript
	{
		"from": "stephen@gmail.com",
		"to": ["alice@gmail.com", "gregg@gmail.com"],
		"cc": ["craig@gmail.com", "kirsty@gmail.com"],
		"bcc": ["steph@gmail.com", "gary@gmail.com"],
		"subject": "Awesome subject",
		"message": "Even more awesome message",
		"replyTo": "name@email.com"
	}
```
The parameters *'from'*, *'to'*, *'subject'* and *'message'* are required. To not use the other fields simply don't include them in the request, for example:
```javascript
	{
		"from": "stephen@gmail.com",
		"to": ["alice@gmail.com", "gregg@gmail.com"],
		"subject": "Awesome subject",
		"message": "Even more awesome message"
	}
```

####REST Responses
```javascript
	{"code":200,"status":"success","messages":["The email was sent successfully"]}
		
	{"code":400,"status":"error","messages":["from: not a well-formed email address"]}

	{"code":400,"status":"error","messages":["The request cannot be fulfilled due to bad syntax."]}

	{"code":503,"status":"error","messages":["The mail server is currently unavailable."]}
```
####Security
The application is setup with HTTP Basic Authentication. The application will create a user entry called *'user'* when the application first runs with an encrypted password. These credentials will need to be included with the REST Request (see cURL above).
	
####Hibernate
Hibernate has been configured to automatically create the required database tables when the application first starts up if required. A default user account called 'user' is also inserted at this point.
