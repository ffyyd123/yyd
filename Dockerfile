#下载maven与java的镜像
FROM openjdk:8-jdk-alpine
VOLUME /tmp
#指定对外端口号
EXPOSE 8066
#EXPOSE 9205
COPY ./yyd/target/yyd.jar yyd.jar

#ADD ./yyd/src/main/resources/fonts/simhei.ttf /usr/share/fonts/simhei.ttf
#ADD ./yyd/src/main/resources/fonts/msyh.ttf /usr/share/fonts/msyh.ttf

ENV SW_AGENT_NAME=商品购物

#定义时区参数
ENV TZ=Asia/Shanghai
#设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone

#启动java程序
#--spring.profiles.active=dev 多环境下指定环境 。 -c为清除以前启动的数据
#ENTRYPOINT java -Xms512m -Xmx1024m -javaagent:/data/agent/skywalking-agent.jar -jar hms.jar --spring.profiles.active=$ENV --spring.profiles.include=$LOG
ENTRYPOINT java -Xms1024m -Xmx1024m -jar hms.jar --spring.profiles.active=$ENV --spring.profiles.include=$LOG
