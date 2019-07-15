package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentDetailCheckDO;
import com.deepexi.promotion.domain.CommentDetailDTO;

import java.util.List;

/**
 * comment_detail_check
 * @author liaoxiaoxin
 */
public interface CommentDetailCheckService extends IService<CommentDetailCheckDO> {

    /**
     * 批量更新评价明细
     * @param detailDTOList CommentDetailDTO
     * @return 更新是否成功
     */
    Boolean updateBatch(List<CommentDetailDTO> detailDTOList);
}