package com.deepexi.promotion.service;

import com.deepexi.promotion.dao.CommentLabelGroupConnectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deepexi.promotion.domain.CommentLabelGroupConnectDO;
import com.deepexi.promotion.domain.CommentLabelGroupConnectQuery;
import com.deepexi.promotion.domain.CommentLabelGroupConnectDTO;
import com.deepexi.promotion.service.CommentLabelGroupConnectService;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;


/**
 * @author
 */
@Service
public class CommentLabelGroupConnectServiceImpl implements CommentLabelGroupConnectService {


    @Autowired
    private CommentLabelGroupConnectDAO commentLabelGroupConnectDAO;

    @Override
    public List<CommentLabelGroupConnectDTO> listPageCommentLabelGroupConnects(CommentLabelGroupConnectQuery query) {
        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        return ObjectCloneUtils.convertList(
                commentLabelGroupConnectDAO.listPageCommentLabelGroupConnects(query),
                CommentLabelGroupConnectDTO.class,
                CloneDirection.OPPOSITE
        );
    }

    @Override
    public List<CommentLabelGroupConnectDTO> listCommentLabelGroupConnects(CommentLabelGroupConnectQuery dto) {
        return ObjectCloneUtils.convertList(
                commentLabelGroupConnectDAO.listPageCommentLabelGroupConnects(dto),
                CommentLabelGroupConnectDTO.class,
                CloneDirection.OPPOSITE
        );
    }

    @Override
    public CommentLabelGroupConnectDTO getCommentLabelGroupConnect(Long id) {
        CommentLabelGroupConnectDO result = commentLabelGroupConnectDAO.getCommentLabelGroupConnect(id);
        if (result == null) {
            return null;
        }
        return result.clone(CommentLabelGroupConnectDTO.class);
    }

    @Override
    public Boolean updateCommentLabelGroupConnect(Long id, CommentLabelGroupConnectDTO dto) {
        dto.setId(id);
        return commentLabelGroupConnectDAO.updateCommentLabelGroupConnect(dto.clone(CommentLabelGroupConnectDO.class));
    }

    @Override
    public Boolean insertCommentLabelGroupConnect(CommentLabelGroupConnectDTO dto) {
        return commentLabelGroupConnectDAO.insertCommentLabelGroupConnect(dto.clone(CommentLabelGroupConnectDO.class));
    }


    @Override
    public Boolean deleteCommentLabelGroupConnects(Long... ids) {
        return commentLabelGroupConnectDAO.deleteCommentLabelGroupConnects(ids);
    }
}