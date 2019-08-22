package com.deepexi.promotion;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CopyObjectRequest;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.PutObjectResult;
import com.deepexi.promotion.config.OSSConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;

/**
 * @author zhoust
 * @date 2019/5/30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class)
@ActiveProfiles("dev")
public class OssClientTest {
    @Autowired
    private OSSConfig ossConfig;

    @Test
    public void test(){
        OSSClient ossClient = new OSSClient(ossConfig.getEndpoint(),ossConfig.getAccessKeyId(),ossConfig.getAccessKeySecret());
        String content = "Hello OSS";
        String fileName = "测试oss_Api_0530.txt";
        String copyFileName = "测试oss_Api_0530_1.txt";
        //上传
        PutObjectResult result= ossClient.putObject(ossConfig.getBucketName(), fileName, new ByteArrayInputStream(content.getBytes()));
        System.out.println(result.getETag());
        //是否存在
        boolean found = ossClient.doesObjectExist(ossConfig.getBucketName(),fileName);
        Assert.assertTrue(found);

        // 拷贝
        CopyObjectRequest copyObjectRequest = new CopyObjectRequest(ossConfig.getBucketName(), fileName, ossConfig.getBucketName(), copyFileName);
        CopyObjectResult copyObjectResult = ossClient.copyObject(copyObjectRequest);
        System.out.println(copyObjectResult.getETag());

        // 删除
        ossClient.deleteObject(ossConfig.getBucketName(),copyFileName);
        boolean found1 = ossClient.doesObjectExist(ossConfig.getBucketName(),copyFileName);
        Assert.assertFalse(found1);
        ossClient.deleteObject(ossConfig.getBucketName(),fileName);
        ossClient.shutdown();
    }
}
