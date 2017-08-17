#!/bin/sh

# DIRECTORY WHERE .JAR IS IN
PATH_JAR=dist/artifacts/WebCrawler_jar

LINK_SEED=""

for a
do
    LINK_SEED+="$a "
done

echo 'Runing...'

eval java -cp $PATH_JAR/WebCrawlerTV.jar \
    net.mateusgabi.webcrawler.Main \
    $LINK_SEED >> logs/log.log 2>&1 &


echo $! > "logs/oss.pid"
