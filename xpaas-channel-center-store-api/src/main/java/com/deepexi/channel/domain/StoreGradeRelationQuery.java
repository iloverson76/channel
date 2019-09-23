package com.deepexi.channel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreGradeRelationQuery extends SuperEntity {

    private Long storeId;

    private Long storeGradeId;

    private List<Long> storeGradeIds;
}
