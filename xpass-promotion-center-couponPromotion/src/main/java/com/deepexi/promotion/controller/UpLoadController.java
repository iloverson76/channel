package com.deepexi.promotion.controller;

import com.deepexi.promotion.config.OSSConfig;
import com.deepexi.promotion.enums.BusinessTopicEnum;
import com.deepexi.promotion.enums.FileTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.utils.OSSClientUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

/**
 * @author zhoust
 * @date 2019/5/30
 **/
@RestController
@RequestMapping("/api/v1/comment/common/upload")
@Slf4j
public class UpLoadController {

    @Autowired
    private OSSClientUtil ossClientUtil;
    @Autowired
    private OSSConfig ossConfig;

    @Resource
    private ExecutorService executorService;

    @SuppressWarnings("unchecked")
    @PostMapping
    public Payload upload(@RequestParam("files") final MultipartFile[] files,
                          @RequestParam(value = "topic") final String topic,
                          @RequestParam(value = "type") final String type) {

        BusinessTopicEnum topicEnum = BusinessTopicEnum.getByCode(topic);
        FileTypeEnum fileTypeEnum = FileTypeEnum.getBycode(type);

        if (topicEnum == null || fileTypeEnum == null) {
            throw new ApplicationException(ResultEnum.PARAM_ERR);
        }
        List<String> resultUrls = new ArrayList<>();
        CompletionService completionService = new ExecutorCompletionService(executorService);
        //因oss不支持批量上传，这里采用多线程并行处理
        for (int i = 0; i < files.length; i++) {
            final MultipartFile file = files[i];
            completionService.submit(() -> {
                String url = ossClientUtil.uploadFile2OSS(file, ossConfig, topicEnum.getCode() + "/" + fileTypeEnum.getCode());
                return url;
            });
        }
        try {
            for (int i = 0; i < files.length; i++) {
                String result = (String) completionService.take().get();
                resultUrls.add(result);
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("线程池上传文件任务失败", e);
        } finally {

        }
        return new Payload(resultUrls);
    }
}
