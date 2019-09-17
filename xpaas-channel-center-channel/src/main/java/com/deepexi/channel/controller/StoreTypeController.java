package com.deepexi.channel.controller;

import com.deepexi.channel.businness.StoreTypeBusinessService;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.domain.store.StoreTypeVO;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.StoreTypeService;
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

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "/门店类型管理", description = "门店类型管理")
@RestController
@RequestMapping("/api/v1/storeType")
public class StoreTypeController {

    @Autowired
    private StoreTypeService storeTypeService;
    @Autowired
    private StoreTypeBusinessService storeTypeBusinessService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求,page传-1时获取所有门店类型")
    public  Payload<PageBean<StoreTypeVO>> findPage(@ApiParam(name = "query", required = true) StoreTypeQuery query) {
        List<StoreTypeDTO> storeTypeDTOS = storeTypeService.findPage(query);
        if(CollectionUtil.isEmpty(storeTypeDTOS)){
            return new Payload(null);
        }
        List<StoreTypeVO> storeTypeVOS = ObjectCloneUtils.convertList(storeTypeDTOS, StoreTypeVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(new PageBean<>(storeTypeVOS));
    }

    @GetMapping("/{id}")
    public Payload<StoreTypeVO> detail(@PathVariable(value = "id", required = true) Long  id) {
        StoreTypeDTO storeTypeDTO = storeTypeService.detail(id);
        if(storeTypeDTO == null){
            return new Payload();
        }
        return new Payload(storeTypeDTO.clone(StoreTypeVO.class, CloneDirection.OPPOSITE));
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改", notes = "根据id修改门店类型")
    public Payload update(@Valid @PathVariable(value = "id", required = true) Long id, @RequestBody StoreTypeVO vo) {
         vo.setId(id);
        StoreTypeDTO dto =vo.clone(StoreTypeDTO.class);
        // 判断编码是否重复
        if(!storeTypeService.isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        if(!storeTypeService.isNameUnique(dto)){
            throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
        }
         return new Payload(storeTypeService.update(dto));
    }

    @PostMapping
    @ApiOperation(value = "创建门店类型", notes = "创建门店类型")
    public Payload<Long> create(@Valid @RequestBody StoreTypeVO vo) {
        StoreTypeDTO dto =vo.clone(StoreTypeDTO.class);
        // 判断编码是否重复
        if(!storeTypeService.isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        if(!storeTypeService.isNameUnique(dto)){
            throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
        }
        return new Payload<>(storeTypeService.create(dto));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据id批量删除门店类型", notes = "根据id批量删除门店类型")
    public Payload delete(@PathVariable(value = "id", required = true) String id) {
        List<Long> ids = Arrays.stream(id.split(",")).map(Long::parseLong).collect(Collectors.toList());
        // 校验是否有门店关联该门店类型
        if(storeTypeBusinessService.haveStoreRelation(ids)) {
            throw new ApplicationException(ResultEnum.HAVE_RELATION);
        }
        return new Payload(storeTypeBusinessService.deleteStoreType(ids));
    }

}