FROM        centos:7
MAINTAINER  bing <2770078682@qq.com>
RUN         yum install -y wget
ENV         BING_HOME /home/bing
RUN         mkdir -p $BING_HOME/java
WORKDIR     $BING_HOME/java
RUN         wget --no-check-certificate --no-cookie --header 'Cookie:oraclelicense=accept-securebackup-cookie;' http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz
RUN         tar -zxvf jdk-8u181-linux-x64.tar.gz && rm -f jdk-8u181-linux-x64.tar.gz
ENV         JAVA_HOME   $BING_HOME/java/jdk1.8.0_181
ENV         PATH        $JAVA_HOME/bin:$PATH
ENV         CLASSPATH   .:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV         APP_PATH /home/bing/app
RUN         mkdir -p $APP_PATH
COPY        docker-test-spring-boot-web.jar $APP_PATH
WORKDIR     $APP_PATH
ENV         APP_NAME docker-test-spring-boot-web.jar
ENTRYPOINT  java -jar $APP_NAME