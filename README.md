###Simple Spring BOOT based REST API

This app has a single module called Profile on which GET, PUT, DELETE, POST operations have been demonstrated.

By default, the data is handled in app itself. However there is also a facility to move to MySQL database.

####Tools Used
- Java 1.8
- Spring Tool Suite 3.7.3
- Spring Boot
- Maven
- MySQL Database (Optional, In-app implementation be default)

####How to run the App
#####Implementation tested on - Mac OS High Sierra v10.13

- Open Terminal and log in to Super User
- Clone repo to desired directory
	+ `git clone https://github.com/gourabc/springboot.git .`
- cd into local project directory. Run `mvn clean install`
- Run `java -jar target/api-0.0.1.jar`
- [OPTIONAL]In case jar is not running, ensure the name of the jar file matches the one in target folder.
- App is ready for use. You can use POSTMAN to query the APIs.

######Reference Link
- [Maven Install](https://www.youtube.com/watch?v=vHGdjKuXKAs)

Please refer the [API Document](API_DOCUMENT.doc) for API Links and expected Response.