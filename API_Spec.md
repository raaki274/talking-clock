# talking-clock REST API documentation
This is a REST based API will return a human friendly text either for given input time or for the current time, refer the below for example,

Numeric Time     Human Friendly Text\
01:00            One o'clock\
02:00            Two o'clock\
13:00            One o'clock\
13:05            Five past one\
13:10            Ten past one\
13:25            Twenty five past one\
13:30            Half past one\
13:35            Twenty five to two\
13:55            Five to two

## Pre-requisities for compiling and running the API
* Java 17 installed
* Maven installed with the access configured for repo to download required dependencies

## Compiling and running the API
* Clone the repo/download the code from repo
* Chage the current working directory to root folder of the repo where pom.xml exist
* Run the script `./install` from Mac/Linux/Unix machine and run `install.cmd` from Windows machine
* After successful compilation and packaging, the API will be up and running on port number `8080` 
* If install scripts doesn't work, try running the command `mvn spring-boot:run` from the project root folder

## URIs & Usage Scenarios
### URL can either be `http://localhost:8080` or the respective URL if the API is hosted
* Example request URLs `http://localhost:8080/talking-cloack` and `http://localhost:8080/talking-cloack/23:45`
* `GET` `URL/talking-cloack`
  * Returns the humal friendly text for current time
* `GET` `URL/talking-cloack/23:45`
  * Returns the humal friendly text for input time `23:45`
* `GET` `URL/talking-cloack/26:45`
  * Returns the `Invalid input time`
* `GET` `URL/talking-cloack/22:75`
  * Returns the `Invalid input time`
* `GET` `URL/talking-cloack/abc`
  * Returns the `Invalid input time`


## Tools & Tech Stack
* Java 17.x.x
* Spring Boot 3.1.3
* Embedded Tomcat Web Server
* Maven 4
* Junit
* GitHub
