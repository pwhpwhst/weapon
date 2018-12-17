FROM ubuntu:artful

MAINTAINER panweihao

ADD jdk-8u171-linux-x64.gz /usr/local/src/jdk8

ADD hadoop-2.8.3.tar.gz /usr/local/src/hadoop

ENV JAVA_HOME=/usr/local/src/jdk8/jdk1.8.0_171
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

ENV HADOOP_HOME=/usr/local/src/hadoop/hadoop-2.8.3
ENV PATH=$HADOOP_HOME/bin:$PATH

EXPOSE 8080