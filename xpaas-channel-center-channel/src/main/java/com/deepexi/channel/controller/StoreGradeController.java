package com.deepexi.channel.controller;

import com.deepexi.channel.businness.StoreGradeBusinessService;
import com.deepexi.channel.domain.store.StoreGradeDTO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.channel.domain.store.StoreGradeVO;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "/门店等级管理", description = "门店等级管理")
@RestController
@RequestMapping("/api/v1/storeGrade")
public class StoreGradeController {

    @Autowired
    private StoreGradeService storeGradeService;
    @Autowired
    private StoreGradeBusinessService storeGradeBusinessService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "查询门店等级列表,传-1时获取整个列表")
    public Payload<PageBean<StoreGradeVO>> findPage(@ApiParam(name = "query", required = true) StoreGradeQuery query) {
        List<StoreGradeDTO> storeGradeDTOS = storeGradeService.findPage(query);
        if (CollectionUtil.isEmpty(storeGradeDTOS)) {
            return new Payload(null);
        }
        List<StoreGradeVO> storeGradeVOS = ObjectCloneUtils.convertList(storeGradeDTOS, StoreGradeVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(new PageBean<>(storeGradeVOS));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询门店等级详情", notes = "查询门店等级详情")
    public Payload<StoreGradeVO> detail(@PathVariable(value = "id", required = true) Long pk) {
        return new Payload(storeGradeService.detail(pk));

    }


    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改", notes = "根据id修改门店等级")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long pk, @RequestBody StoreGradeVO vo) {
        vo.setId(pk);
        StoreGradeDTO storeGradeDTO = vo.clone(StoreGradeDTO.class);
        //判断编码是否重复
        if(!storeGradeService.isCodeUnique(storeGradeDTO)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        if(!storeGradeService.isNameUnique(storeGradeDTO)){
            throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
        }
        return new Payload(storeGradeService.update(storeGradeDTO));
    }

    @PostMapping
    @ApiOperation(value = "创建门店等级", notes = "创建门店等级")
    public Payload<Long> create(@RequestBody StoreGradeVO vo) {
        StoreGradeDTO storeGradeDTO = vo.clone(StoreGradeDTO.class);
        //判断编码是否重复
        if(!storeGradeService.isCodeUnique(storeGradeDTO)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        if(!storeGradeService.isNameUnique(storeGradeDTO)){
            throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
        }
        return new Payload(storeGradeService.create(storeGradeDTO));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据id批量删除门店等级", notes = "根据id批量删除门店等级")
    public Payload delete(@PathVariable(value = "id", required = true) String id) {
        List<Long> ids = Arrays.stream(id.split(",")).map(Long::parseLong).collect(Collectors.toList());
        //校验是否有门店关联该门店等级
        if(storeGradeBusinessService.haveStoreRelation(ids)){
            throw new ApplicationException(ResultEnum.HAVE_RELATION);
        }
        return new Payload(storeGradeBusinessService.deleteGradeType(ids));
    }

}