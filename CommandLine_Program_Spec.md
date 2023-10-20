# talking-clock documentation
This is a Java program will return a human friendly text either for given input time or for the current time, refer the below for example,

## Numeric Time     Human Friendly Text 
01:00            One o'clock 
02:00            Two o'clock 
13:00            One o'clock 
13:05            Five past one 
13:10            Ten past one 
13:25            Twenty five past one
13:30            Half past one
13:35            Twenty five to two 
13:55            Five to two

## Pre-requisities for compiling and running teh API
* Java 17 installed

## Compiling and running the program
* Clone the repo/download the code from repo
* Chage the current working directory to root/standalone and the root folder is where pom.xml exist
* Run `javac TalkingClock.java`
* After successful compilation; run `java TalkingClock` for current time and run `java TalkingClock 21:36` to supply payload for time

