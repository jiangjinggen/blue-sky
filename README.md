# blue-sky
spring cloud alibaba study 
#nacos start cmd
sh startup.sh -m standalone
startup.cmd -m standalone
#kafka start cmd
.\bin\windows\zookeeper-server-start.bat  .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties
./bin/zookeeper-server-start.sh  ./config/zookeeper.properties &  
./bin/kafka-server-start.sh ./config/server.properties &
#zipkin start cmd
java -DKAFKA_BOOTSTRAP_SERVERS=127.0.1:9092 -jar zipkin.jar --STORAGE_TYPE=mysql --MYSQL_HOST=127.0.0.1 --MYSQL_TCP_PORT=3306 --MYSQL_USER=root --MYSQL_PASS=123456 --MYSQL_DB=sky_zipkin
#redis start cmd
