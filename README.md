catalog
=======

For managing personal digital (and other) content 

##Usage
###Client
####To install and launch a light http-server on localhost:8080
* cd client
* npm install -g bower gulp http-server
* npm install && bower install
* gulp
* cd dist && http-server .

###Server
####To install and start an embedded tomcat on port 8081
* cd server
* ./gradlew build && java -jar build/libs/catalog-server-0.1.jar

###Database
* Setup a MongoDB and create a database "catalogdb" with "items"-collection

###Technology stack
####Client-side
* AngularJS
* Jade
* SASS
* gulp.js
* bower
* bootstrap

####Server-side
* Gradle
* Java
* Spring
* Lombok
* JUnit
* Mockito
* REST & JSON
* MongoDB
