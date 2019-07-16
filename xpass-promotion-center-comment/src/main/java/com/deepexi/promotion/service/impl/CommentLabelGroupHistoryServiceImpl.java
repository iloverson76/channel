package com.deepexi.promotion.service;

import com.deepexi.promotion.dao.CommentLabelGroupHistoryDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryDO;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryQuery;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryDTO;
import com.deepexi.promotion.service.CommentLabelGroupHistoryService;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;


/**
 * @author zhangyanping
 */
@Service
@Slf4j
public class CommentLabelGroupHistoryServiceImpl implements CommentLabelGroupHistoryService {


    @Autowired
    private CommentLabelGroupHistoryDAO commentLabelGroupHistoryDAO;

    @Override
    public List<CommentLabelGroupHistoryDTO> listPageCommentLabelGroupHistorys(CommentLabelGroupHistoryQuery query) {
        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        return ObjectCloneUtils.convertList(
                commentLabelGroupHistoryDAO.listPageCommentLabelGroupHistorys(query),
                CommentLabelGroupHistoryDTO.class,
                CloneDirection.OPPOSITE
        );
    }

    @Override
    public List<CommentLabelGroupHistoryDTO> listCommentLabelGroupHistorys(CommentLabelGroupHistoryQuery dto) {
        return ObjectCloneUtils.convertList(
                commentLabelGroupHistoryDAO.listPageCommentLabelGroupHistorys(dto),
                CommentLabelGroupHistoryDTO.class,
                CloneDirection.OPPOSITE
        );
    }

    @Override
    public CommentLabelGroupHistoryDTO getCommentLabelGroupHistory(Long id) {
        CommentLabelGroupHistoryDO result = commentLabelGroupHistoryDAO.getCommentLabelGroupHistory(id);
        if (result == null) {
            return null;
        }
        return result.clone(CommentLabelGroupHistoryDTO.class);
    }

    @Override
    public Boolean updateCommentLabelGroupHistory(Long id, CommentLabelGroupHistoryDTO dto) {
        dto.setId(id);
        return commentLabelGroupHistoryDAO.updateCommentLabelGroupHistory(dto.clone(CommentLabelGroupHistoryDO.class));
    }

    @Override
    public Boolean insertCommentLabelGroupHistory(CommentLabelGroupHistoryDTO dto) {
        return commentLabelGroupHistoryDAO.insertCommentLabelGroupHistory(dto.clone(CommentLabelGroupHistoryDO.class));
    }


    @Override
    public Boolean deleteCommentLabelGroupHistorys(Long... ids) {
        return commentLabelGroupHistoryDAO.deleteCommentLabelGroupHistorys(ids);
    }
}