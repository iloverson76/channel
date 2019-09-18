#!/bin/bash
cd `dirname $0`

img_mvn="maven:3.3.3-jdk-8"                 # docker image of maven
m2_cache=~/.m2                              # the local maven cache dir
proj_home=$PWD                              # the project root dir
img_output="deepexi/xpaas-channel-center"      # output image tag

# apollo 配置中心配置
apollo_configService="http://39.100.65.239:8080/"
app_id="xpaas-channel-center"
apollo_meta="http://39.100.65.239:8080"
apollo_bootstrap_enabled="true"
apollo_bootstrap_namespaces="application"
apollo_env="DEV"

git pull  # should use git clone https://name:pwd@xxx.git

echo "use docker maven"
docker run --rm \
   -v $m2_cache:/root/.m2 \
   -v $proj_home:/usr/src/mymaven \
   -w /usr/src/mymaven $img_mvn mvn clean package -U

sudo mv $proj_home/xpaas-channel-center-webapp/target/xpaas-channel-center-*.jar $proj_home/xpaas-channel-center-webapp/target/demo.jar # 兼容所有sh脚本
docker build -t $img_output .

mkdir -p $PWD/logs
chmod 777 $PWD/logs

# 删除容器
docker rm -f xpaas-channel-center &> /dev/null

version=`date "+%Y%m%d%H"`

# 启动镜像
docker run -d --restart=on-failure:5 --privileged=true \
    -w /home \
    -v $PWD/logs:/home/logs \
    -p 8307:8307 \
    --name xpaas-channel-center deepexi/xpaas-channel-center \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -Denv=$apollo_env \
        -Dapollo.configService=$apollo_configService \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar \
          --spring.profiles.active=prod \
          --eureka.client.serviceUrl.defaultZone=http://admin:deepexi@39.100.65.239:8080/eureka/ \
          --app.id=$app_id \
          --apollo.meta=$apollo_meta \
          --apollo.bootstrap.enabled=$apollo_bootstrap_enabled \
          --apollo.bootstrap.namespaces=$apollo_bootstrap_namespaces