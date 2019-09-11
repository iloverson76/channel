package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deepexi.channel.domain.SuperEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreGradeRelationDTO extends SuperEntity {
    @TableField(value = "`store_id`")
    private Long storeId;
    @TableField(value = "`store_grade_Id`")
    private Long storeGradeId;
}
