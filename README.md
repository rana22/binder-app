# binder-app
spring-boot, gradle, rabbit mq, microservices

### List of services
#### 1. SMTP Email Services

# Project Set Up

## 1 Setting up SMTP mail service 

Set UP:
1. Set up local smtp server using your email id - run following command on terminal 
```
postfix stop
postfix start
telnet localhost 25
```
-smtp server rum on port 25
2. make post call to check if the application is running 
```
url: localhost:8080/binder/email
method: post

request payload:

{
"emailTo" : ["receivers email"],
"subject" : "test-subject,
"content" : "test-content",
"sendFrom" : "sender address"
}
```
3. Add authorization as basic auth with 
```
username - user
password - password
```
