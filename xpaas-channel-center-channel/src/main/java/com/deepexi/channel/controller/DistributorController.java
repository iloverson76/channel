package com.deepexi.channel.controller;

import com.deepexi.channel.businness.DistributorBusinessService;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountVO;
import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeVO;
import com.deepexi.channel.domain.distributor.DistributorVO;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Api(description = "经销商管理")
@RestController
@RequestMapping("/api/v1/distributor")
public class DistributorController {

    @Autowired
    private DistributorBusinessService distributorBusinessService;

    @PostMapping
    @ApiOperation(value = "创建经销商")
    public Payload<Boolean> create(@RequestBody DistributorVO vo) {

        List<BankAccountDTO> bankAccounts=
                ObjectCloneUtils.convertList(vo.getBankAccounts(), BankAccountDTO.class,CloneDirection.FORWARD);

        DistributorDTO dto= new DistributorDTO();

        BeanUtils.copyProperties(vo,dto);

        dto.setBankAccounts(bankAccounts);

        return new Payload(distributorBusinessService.create(dto));
    }

    @DeleteMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id批量删除经销商")
    public Payload<Boolean> delete(@PathVariable(value = "id") String ids) {

        List<Long> idList= Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        return new Payload<>(distributorBusinessService.delete(idList));
    }

}