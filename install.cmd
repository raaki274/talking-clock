call mvn clean
call mvn package
export TARGET=./target
java -jar %TARGET/talkingclock-0.0.1-SNAPSHOT.jar
