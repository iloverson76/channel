# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
FROM iron/java:1.8

USER root

ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8

COPY xpaas-channel-wepapp/target/demo.jar /home/