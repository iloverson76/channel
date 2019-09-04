package com.deepexi.channel.controller;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.distributor.DistributorDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeVO;
import com.deepexi.channel.domain.distributor.DistributorVO;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "经销商管理")
@RestController
@RequestMapping("/api/v1/distributor")
public class DistributorController {

    @Autowired
    private DistributorService distributorService;

    @PostMapping
    @ApiOperation(value = "创建经销商")
    public Payload<Boolean> create(@RequestBody DistributorVO vo) {

        DistributorDTO dto=vo.clone(DistributorDTO.class, CloneDirection.FORWARD);

        return new Payload(distributorService.create(dto));
    }

}