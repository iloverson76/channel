package com.deepexi.promotion.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoust
 * @date 2019/5/30
 **/
@Configuration
@Data
public class OSSConfig {
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId ;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.fileMaxSize}")
    private Long fileMaxSize;
    @Value("${oss.env}")
    private String env;
    @Value("${oss.viewurl}")
    private String viewUrl;
    @Value("${oss.ext}")
    private String fileExt;
    @Value("${oss.profile}")
    private String ossProfile;
}
