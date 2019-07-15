package com.deepexi.promotion.service;

import java.util.List;

import com.deepexi.promotion.dao.CommentModelHistoryDAO;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.service.CommentModelHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;

/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@Service
@Slf4j
public class CommentModelHistoryServiceImpl implements CommentModelHistoryService {

    @Autowired
    private CommentModelHistoryDAO commentModelHistoryDAO;


    @Override
    public List<CommentModelHistoryDTO> findCommentModelHistoryPage(CommentModelHistoryQuery query) {
        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<CommentModelHistoryDO> pageList = commentModelHistoryDAO.findCommentModelHistoryPage(query);
        return ObjectCloneUtils.convertList(pageList, CommentModelHistoryDTO.class, CloneDirection.OPPOSITE);
    }
    @Override
    public Boolean createCommentModelHistory(CommentModelHistoryDTO dto) {
        return commentModelHistoryDAO.createCommentModelHistory(dto.clone(CommentModelHistoryDO.class));
    }
    @Override
    public Boolean deleteBatchByModelIds(Long... ids) {
        return commentModelHistoryDAO.deleteBatchByModelIds(ids);
    }

}