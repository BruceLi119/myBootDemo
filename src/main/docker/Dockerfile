#使用 JDK 8 环境为基础镜像，如果镜像不是本地的将会从 DockerHub 进行下载
FROM java:8
#作者
MAINTAINER li.shuGuang<li.shuguang@asiainfo-sec.com>
#该命令效果：在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到tomcat容器的工作目录/tmp目录
VOLUME /tmp
#复制文件并更名为app.jar
ADD springbootdemo-1.0-SNAPSHOT.jar app.jar
 #为了缩短 Tomcat 的启动时间，添加 java.security.egd 的系统属性指向 /dev/urandom 作为 ENTRYPOINT。
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
