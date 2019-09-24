package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.BankAccountDO;
import com.deepexi.channel.domain.BankAccountQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
@Mapper
public interface BankAccountMapper extends BaseMapper<BankAccountDO> {

    List<BankAccountDO> findList(BankAccountQuery query);

    int deleteByIds(@Param("ids") List<Integer > ids);

    List<BankAccountDO> getBankAccountByIds(@Param("ids") List<Long> ids);
}