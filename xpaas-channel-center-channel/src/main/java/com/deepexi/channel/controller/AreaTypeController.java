package com.deepexi.channel.controller;


import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.domain.area.AreaTypeVO;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 区域类型-前端控制器
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/areaType")
@Api("区域类型管理")
public class AreaTypeController {

    @Autowired
    AreaTypeService areaTypeService;

    AppRuntimeEnv appRuntimeEnv = AppRuntimeEnv.getInstance();

    @PostMapping()
    @ApiOperation(value = "新增区域类型")
    public Payload<Boolean> saveAreaType(@RequestBody AreaTypeVO vo) {

        AreaTypeDTO dto = vo.clone(AreaTypeDTO.class, CloneDirection.FORWARD);

        boolean result = areaTypeService.saveAreaType(dto);

        return new Payload<>(result);
    }

    @DeleteMapping("}")
    @ApiOperation(value = "删除区域类型")
    public Payload<Boolean> deleteAreaTypeByIds(@RequestBody Set<HashMap<Long,Long>> ids) {

        Boolean result = areaTypeService.deleteAreaTypeByIds(ids);

        return new Payload<>(result);
    }

    @PutMapping()
    @ApiOperation(value = "修改区域类型")
    public Payload<Boolean> updateAreaTypeById(@RequestBody AreaTypeVO vo) {

        AreaTypeDTO dto = vo.clone(AreaTypeDTO.class, CloneDirection.FORWARD);

        boolean result = areaTypeService.updateAreaTypeById(dto);

        return new Payload<>(result);
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取类目详情")
    public Payload<AreaTypeVO> getAreaTypeById(@PathVariable Long id) {

        AreaTypeDTO dto = areaTypeService.getAreaTypeById(id);

        AreaTypeVO vo = dto.clone(AreaTypeVO.class, CloneDirection.OPPOSITE);

        return new Payload<>(vo);
    }

    @GetMapping()
    @ApiOperation("查询区域类型列表")
    public Payload<PageBean<AreaTypeVO>> listAreaTypePage(@ApiParam(name = "query", required = true) AreaTypeQuery query) {

        query.setTenantId(appRuntimeEnv.getTenantId());

        query.setAppId(appRuntimeEnv.getAppId());

        List<AreaTypeDTO> dtoList = areaTypeService.listAreaTypePage(query);

        List<AreaTypeVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/limitedCreate")
    @ApiOperation("查询未受分类限制上级-新增用")
    public Payload<PageBean<AreaTypeVO>> listParentForCreate() {

        List<AreaTypeDTO> dtoList = areaTypeService.listParentForCreate();

        List<AreaTypeVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/limitedUpdaTe/{parentId:[0-9,]+}")
    @ApiOperation("查询未受分类限制上级-更新用")
    public Payload<PageBean<AreaTypeVO>> listParentForUpdate(@PathVariable Long id,@PathVariable Long parentId) {

        List<AreaTypeDTO> dtoList = areaTypeService.listParentForUpdate(id,parentId);

        List<AreaTypeVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }
}