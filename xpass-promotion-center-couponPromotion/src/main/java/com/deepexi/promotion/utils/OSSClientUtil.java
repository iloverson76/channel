package com.deepexi.promotion.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.deepexi.promotion.common.RandomGenerator;
import com.deepexi.promotion.config.OSSConfig;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhoust
 * @date 2019/5/30
 **/
@Component
@Slf4j
public class OSSClientUtil {

    @Autowired
    private AppRuntimeEnv env;

    private volatile static OSSClient instance;

    public OSSClientUtil() {
    }

    public static OSSClient getOSSClient(OSSConfig ossConfig) {
        if (instance == null) {
            synchronized (OSSClientUtil.class) {
                if (instance == null) {
                    instance = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
                }
            }
        }
        return instance;
    }

    public String uploadFile2OSS(MultipartFile file, OSSConfig ossConfig, String customKey) {
        long size = file.getSize();
        if (size > (ossConfig.getFileMaxSize().longValue() * 1024 * 1024)) {
            log.error("上传文件大小不能超过" + ossConfig.getFileMaxSize() + "M！");
            throw new ApplicationException(ResultEnum.UPLOAD_FILE_TOO_BIG);
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        if (!ossConfig.getFileExt().contains(suffix)) {
            throw new ApplicationException(ResultEnum.FILE_EXT_NON_SUPPORT);
        }
        String tenantId = env.getTenantId();
        String name = RandomGenerator.getNonce_str(6) + System.currentTimeMillis() + (tenantId == null ? "" : tenantId) + "." + suffix;
        try {
            InputStream inputStream = file.getInputStream();
            return uploadFile2OSS(new ByteArrayInputStream(IOUtils.toByteArray(inputStream)), name, ossConfig, customKey);
        } catch (IOException e) {
            log.error("文件上传失败", e);
        }
        return name;
    }

    /**
     * @param inputStream 输入流
     * @param fileName    文件名
     * @param ossConfig   oss配置
     * @param customKey   定制key，根据不同业务，用于层级文件夹
     * @return
     */
    public String uploadFile2OSS(ByteArrayInputStream inputStream, String fileName, OSSConfig ossConfig, String customKey) {
        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf(".") + 1)));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件
            String key = switchDir(ossConfig, customKey) + fileName;
            getOSSClient(ossConfig).putObject(switchBucket(ossConfig), key, inputStream, objectMetadata);
            ret = ossConfig.getViewUrl() + key;
        } catch (OSSException | ClientException e) {
            log.error("上传阿里云异常", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ret;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     * 文件后缀
     *
     * @return String
     */
    public static String getContentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("mp4")) {
            return "video/mp4";
        }
        if (filenameExtension.equalsIgnoreCase("avi")) {
            return "video/avi";
        }
        if (filenameExtension.equalsIgnoreCase("wmv")) {
            return "video/x-ms-wmv";
        }
        if (filenameExtension.equalsIgnoreCase("rmvb")) {
            return "application/vnd.rn-realmedia-vbr";
        }
        if (filenameExtension.equalsIgnoreCase("png")) {
            return "image/png";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpg") || filenameExtension.equalsIgnoreCase("jpeg")) {
            return "image/jpeg";
        }
        return "image/jpeg";
    }

    private String switchBucket(OSSConfig ossConfig) {
        return ossConfig.getBucketName();
    }

    private String switchDir(OSSConfig ossConfig, String customKey) {
        if (StringUtils.isNotEmpty(customKey)) {
            return ossConfig.getOssProfile() + "/" + customKey + "/";
        } else {
            return ossConfig.getOssProfile() + "/";
        }
    }
}
