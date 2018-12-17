FROM ubuntu:artful

MAINTAINER panweihao

ADD jdk-8u171-linux-x64.gz /usr/local/src/jdk8


ENV JAVA_HOME=/usr/local/src/jdk8/jdk1.8.0_171
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

EXPOSE 8080