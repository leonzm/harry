cd /home/java/voldemort_restful/
nohup java -Xms1024m -Xmx2048m -Dfile.encoding=UTF-8 -jar -XX:+UseG1GC -XX:+UseStringDeduplication -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=18080 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false harry.jar > /dev/null 2>&1 &

