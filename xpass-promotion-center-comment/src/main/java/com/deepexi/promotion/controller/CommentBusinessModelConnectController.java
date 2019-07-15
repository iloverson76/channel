package com.deepexi.promotion.controller;

import com.deepexi.util.config.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.deepexi.promotion.service.CommentBusinessModelConnectService;
import com.deepexi.promotion.domain.CommentBusinessModelConnectDTO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 业务和对象关联关系
 * @author zhangyanping
 * @date 2019/5/15 13:48
 */

@RestController
@RequestMapping("api/v1/comment/businesses/models/relations")
@Slf4j
public class  CommentBusinessModelConnectController {

    @Autowired
    private CommentBusinessModelConnectService commentBusinessModelConnectService;

    private static final String ERROR = "error";
    /**
     * 添加评价和业务关联关系
     * @param vo 实体
     * @return Payload
     */
    @PostMapping
    public Payload insertCommentBusinessModelConnect(@RequestBody @Valid CommentBusinessModelConnectVO vo) {
        try {
            //创建关联对象时要将修改时间设置默认现在值 ，否则前端修改时间显示bug
            vo.setUpdatedTime(new Date());
            return new Payload<>(commentBusinessModelConnectService.insertCommentBusinessModelConnect(vo.clone(CommentBusinessModelConnectDTO.class)));
        } catch (Exception e) {
            log.error(ERROR + e);
            throw e;
        }
    }

    /**
     * 删除业务对象关联关系
     * @param id 关联主键
     * @return Payload
     */
    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    public Payload deleteCommentBusinessModelConnects(@PathVariable(value = "id", required = true) String id) {
        try {
            return new Payload<>(commentBusinessModelConnectService.deleteCommentBusinessModelConnects(Long.parseLong(id)));
        } catch (Exception e) {
            log.error(ERROR + e);
            return new Payload<>(false);
        }
    }

}