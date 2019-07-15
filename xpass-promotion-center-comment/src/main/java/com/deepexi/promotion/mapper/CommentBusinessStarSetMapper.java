package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentBusinessStarSetDO;
import com.deepexi.promotion.domain.CommentBusinessStarSetDeleteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author zhangyanping
 * @date 2019/5/24 15:37
 */
@Mapper
public interface  CommentBusinessStarSetMapper extends BaseMapper<CommentBusinessStarSetDO> {

    List<CommentBusinessStarSetDO> findList(@Param("eo") CommentBusinessStarSetDO eo);

    int deleteByIds(@Param("ids") List<Integer> ids);

    int deleteByCondition(CommentBusinessStarSetDeleteVO vo);

}