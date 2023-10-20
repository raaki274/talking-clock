if ! mvn clean; then
    echo "Build failed in clean stage!"
    echo "Check the console logs for more details."
elif ! mvn package; then
    echo "Build failed in install stage!"
    echo "Check the console logs for more details."
else
    export TARGET="./target"
    if ! java -jar $TARGET/talkingclock-0.0.1-SNAPSHOT.jar; then
        echo "Run application failed!"
        echo "Check the console logs for more details"
    else
        echo "Started the application successfully!"
    fi
fi
