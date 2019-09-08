package com.deepexi.channel.controller;

import com.deepexi.channel.businness.StoreBusinessService;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainVO;
import com.deepexi.channel.domain.store.*;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.StoreService;
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


@Api(value = "/门店信息表", description = "查询门店列表")
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreBusinessService storeBusinessService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求")
    public Payload<PageBean<StoreVO>> findPage(@ApiParam(name = "query", required = true) StoreQuery query) {
        List<StoreDTO> storeDTOs = storeService.findPage(query);
        if (CollectionUtil.isEmpty(storeDTOs)) {
            return null;
        }
        return new Payload(new PageBean<>(ObjectCloneUtils.convertList(storeDTOs, StoreVO.class)));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取门店详情")
    public Payload<StoreDetailVO> detail(@PathVariable(value = "id", required = true) Long pk) {
        StoreDetailDTO storeDetailDTO = storeBusinessService.detail(pk);
        if (storeDetailDTO == null) {
            return new Payload<>();
        }
        StoreDetailVO storeDetailVO = storeDetailDTO.clone(StoreDetailVO.class, CloneDirection.OPPOSITE);
        //设置门店等级详情
        if (storeDetailDTO.getStoreGradeDTO() != null) {
            storeDetailVO.setStoreGradeVO(storeDetailDTO.getStoreGradeDTO().clone(StoreGradeVO.class));
        }
        //设置门店类型详情
        if (storeDetailDTO.getStoreTypeDTO() != null) {
            storeDetailVO.setStoreTypeVO(storeDetailDTO.getStoreTypeDTO().clone(StoreTypeVO.class));
        }
        //设置连锁
        if (storeDetailDTO.getChainDTO() != null) {
            storeDetailVO.setChainVO(storeDetailDTO.getChainDTO().clone(ChainVO.class));
        }
        //设置区域
        if (storeDetailDTO.getAreaDTO() != null) {
            storeDetailVO.setAreaVO(storeDetailDTO.getAreaDTO().clone(AreaVO.class));
        }
        //设置修改历史
        if(CollectionUtil.isNotEmpty(storeDetailDTO.getStoreHistoryDTOS())){
            storeDetailVO.setStoreHistoryVOS(ObjectCloneUtils.convertList(storeDetailDTO.getStoreHistoryDTOS(),StoreHistoryVO.class,CloneDirection.OPPOSITE ));
        }

        return new Payload(storeDetailVO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改", notes = "根据id修改CcStore")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long pk, @RequestBody StoreDetailVO vo) {
        vo.setId(pk);
        StoreDetailDTO storeDetailDTO = vo.clone(StoreDetailDTO.class);
        if (vo.getStoreGradeVO() != null) {
            StoreGradeDTO storeGradeDTO = vo.getStoreGradeVO().clone(StoreGradeDTO.class);
            storeDetailDTO.setStoreGradeDTO(storeGradeDTO);
        }
        if (vo.getStoreTypeVO() != null) {
            StoreTypeDTO storeTypeDTO = vo.getStoreTypeVO().clone(StoreTypeDTO.class);
            storeDetailDTO.setStoreTypeDTO(storeTypeDTO);
        }
        if (vo.getChainVO() != null) {
            ChainDTO chainDTO = vo.getChainVO().clone(ChainDTO.class);
            storeDetailDTO.setChainDTO(chainDTO);
        }
        if (vo.getAreaVO() != null) {
            AreaDTO areaDTO = vo.getAreaVO().clone(AreaDTO.class);
            storeDetailDTO.setAreaDTO(areaDTO);
        }
        //编码是否重复
        if (!storeService.isCodeUnique(storeDetailDTO)) {
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        return new Payload(storeBusinessService.update(storeDetailDTO));
    }

    @PostMapping
    @ApiOperation(value = "创建门店", notes = "创建门店")
    public Payload<Long> create(@RequestBody StoreDetailVO vo) {
        StoreDetailDTO storeDetailDTO = vo.clone(StoreDetailDTO.class, CloneDirection.OPPOSITE);
        if (vo.getStoreGradeVO() != null) {
            StoreGradeDTO storeGradeDTO = vo.getStoreGradeVO().clone(StoreGradeDTO.class);
            storeDetailDTO.setStoreGradeDTO(storeGradeDTO);
        }
        if (vo.getStoreTypeVO() != null) {
            StoreTypeDTO storeTypeDTO = vo.getStoreTypeVO().clone(StoreTypeDTO.class);
            storeDetailDTO.setStoreTypeDTO(storeTypeDTO);
        }
        if (vo.getChainVO() != null) {
            ChainDTO chainDTO = vo.getChainVO().clone(ChainDTO.class);
            storeDetailDTO.setChainDTO(chainDTO);
        }
        if (vo.getAreaVO() != null) {
            AreaDTO areaDTO = vo.getAreaVO().clone(AreaDTO.class);
            storeDetailDTO.setAreaDTO(areaDTO);
        }

        //编码是否重复
        if (!storeService.isCodeUnique(storeDetailDTO)) {
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        return new Payload(storeBusinessService.create(storeDetailDTO));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据id批量删除门店", notes = "根据id批量删除store")
    public Payload<Boolean> delete(@PathVariable(value = "id", required = true) String id) {
        List<Long> ids = Arrays.stream(id.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return new Payload(storeService.delete(ids));
    }

}