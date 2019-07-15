package com.deepexi.promotion.controller;

import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.service.CommentLabelGroupHistoryService;
import com.deepexi.util.config.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.deepexi.promotion.service.CommentLabelGroupService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import javax.validation.Valid;

/**
 * @author zhangyanping
 * @date 2019/5/21 17:59
 */
@RestController
@RequestMapping("/api/v1/comment/apps/{appId}/labels/groups")
@Slf4j
public class CommentLabelGroupController {

    @Autowired
    private CommentLabelGroupService commentLabelGroupService;

    @Autowired
    private CommentLabelGroupHistoryService commentLabelGroupHistoryService;


    private static final String ERROR = "error";

    /**
     *  分页/列表查询标签组
     * @param appId 应用ID
     * @param query 查询参数封装
     * @return 分页列表
     */
    @GetMapping()
    public Payload findCommentLabelGroupPage(@PathVariable(value = "appId", required = true) Long appId, CommentLabelGroupQuery query) {
        query.setAppId(appId);
        List<CommentLabelGroupDTO> labelGroupVOS = commentLabelGroupService.findPageLabelGroupAndDetail(query);
        if(labelGroupVOS==null){
            return new Payload<>(new PageBean<>(new ArrayList<CommentLabelGroupVO>()));
        }
        List<CommentLabelGroupVO> list = ObjectCloneUtils.convertList(
                labelGroupVOS,
                CommentLabelGroupVO.class,
                CloneDirection.OPPOSITE
        );
        PageBean<CommentLabelGroupVO> pageBean = new PageBean<>(list);
        return new Payload<>(pageBean);
    }

    /**
     * 根据分组ID查询详细
     * @param appId 应用ID
     * @param groupId 标签组ID
     * @return 单个对象
     */
    @GetMapping("/{id:[a-zA-Z0-9]+}")
    public Payload getGroupDetail(@PathVariable(value = "appId", required = true) Long appId,
                                  @PathVariable(value = "id", required = true) Long groupId
                                 ) {
        CommentLabelGroupQuery query =new CommentLabelGroupQuery();
        query.setGroupId(groupId);
        query.setAppId(appId);
        List<CommentLabelGroupDTO> labelGroupVOS = commentLabelGroupService.findPageLabelGroupAndDetail(query);
        if(labelGroupVOS==null){
            return new Payload<>(new PageBean<>(new ArrayList<>()));
        }
        List<CommentLabelGroupVO> list = ObjectCloneUtils.convertList(
                labelGroupVOS,
                CommentLabelGroupVO.class,
                CloneDirection.OPPOSITE
        );
        return new Payload<>(list.get(0));
    }

    /**
     * 根据ID修改标签组信息
     * @param appId 应用ID
     * @param pk 主键
     * @param vo 信息体
     * @return 成功或失败
     */
    @PutMapping("/{id:[a-zA-Z0-9,]+}")
    public Payload updateCommentLabelGroup(@PathVariable(value = "appId", required = true) Long appId,
                                           @PathVariable(value = "id", required = true) Long pk,
                                           @RequestBody @Valid CommentLabelGroupInputVO vo) {

        try {
            CommentLabelGroupInputDTO dto = vo.clone(CommentLabelGroupInputDTO.class);
            dto.setLabelIdsList(vo.getLabelIdsList());
            dto.setAppId(appId);
            return new Payload<>(commentLabelGroupService.updateCommentLabelGroup(pk, dto));
        } catch (Exception e) {
            log.error(ERROR + e);
            throw e;
        }
    }

    /**
     * 添加标签组
     * @param appId 应用ID
     * @param vo 参数体
     * @return 成功或失败
     */
    @PostMapping
    public Payload insertCommentLabelGroup(@PathVariable(value = "appId", required = true) Long appId, @RequestBody @Valid CommentLabelGroupInputVO vo) {
        try {
            CommentLabelGroupInputDTO dto = vo.clone(CommentLabelGroupInputDTO.class);
            dto.setAppId(appId);
            dto.setLabelIdsList(vo.getLabelIdsList());
            return new Payload<>(commentLabelGroupService.insertCommentLabelGroup(dto));
        } catch (Exception e) {
            log.error(ERROR + e);
            throw e;
        }
    }

    /**
     * 批量删除标签组信息
     *
     * @param ids 主键字符串
     * @return 成功或失败
     */
    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    public Payload deleteCommentLabelGroups(@PathVariable(value = "id", required = true) String ids) {
        try {
            return new Payload<>(commentLabelGroupService.deleteCommentLabelGroups(Arrays.stream(ids.split(",")).map(Long::parseLong).toArray(Long[]::new)));
        } catch (Exception e) {
            log.error(ERROR + e);
            return new Payload<>(false);
        }
    }

    /**
     * 标签组历史记录查询
     * @param groupId 标签组ID
     * @param historyQuery 标签组历史参数
     * @return 列表
     */
    @GetMapping("/{groupId}/history")
    public Payload findCommentLabelGroupHistoryPage(
            @PathVariable(value = "groupId") Long groupId,@Valid CommentLabelGroupHistoryQuery historyQuery) {
        historyQuery.setLabelGroupId(groupId);
        List<CommentLabelGroupHistoryVO> vos = ObjectCloneUtils.convertList(
                commentLabelGroupHistoryService.listCommentLabelGroupHistorys(historyQuery),
                CommentLabelGroupHistoryVO.class,
                CloneDirection.OPPOSITE
        );
        PageBean<CommentLabelGroupHistoryVO> pageBean = new PageBean<>(vos);
        return new Payload<>(pageBean);
    }
}
