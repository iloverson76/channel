package com.deepexi.channel.controller;

import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.domain.chain.ChainTypeVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 连锁类型表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/chainType")
@Api("连锁类型管理")
public class ChainTypeController {

    /**
     * @MethodName: listChainTypePage
     * @Description: 查询连锁类型列表
     * @Param: [query]
     * @Return: com.deepexi.util.config.Payload<com.deepexi.util.pageHelper.PageBean < com.deepexi.channel.domain.chain.ChainTypeVO>>
     * @Author: mumu
     * @Date: 2019/8/26
     **/
    @GetMapping
    @ApiOperation(value = "查询连锁类型列表")
    public Payload<PageBean<ChainTypeVO>> listChainTypePage(@ApiParam(name = "query", required = true) ChainTypeQuery query) {
        List<ChainTypeVO> result = new ArrayList<>();
        result.add(new ChainTypeVO());
        result.add(new ChainTypeVO());
        return new Payload<>(new PageBean<>(result));
    }

    /**
     * @MethodName: getChainType
     * @Description: 根据id获取连锁类型
     * @Param: [id]
     * @Return: com.deepexi.util.config.Payload<com.deepexi.channel.domain.chain.ChainTypeVO>
     * @Author: mumu
     * @Date: 2019/8/26
     **/
    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id获取连锁类型")
    public Payload<ChainTypeVO> getChainType(@PathVariable(value = "id", required = true) Long id) {


        return new Payload<>(new ChainTypeVO());
    }

    /**
     * @MethodName: saveChainType
     * @Description: 保存连锁分类类型
     * @Param: [vo]
     * @Return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @PostMapping()
    @ApiOperation(value = "保存连锁分类")
    public Payload<Boolean> saveChainType(@RequestBody ChainTypeVO vo) {
        return new Payload<>(true);
    }

    /**
     * @MethodName: updateChainType
     * @Description: 更新连锁分类
     * @Param: [vo]
     * @Return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @PutMapping()
    @ApiOperation(value = "更新连锁分类")
    public Payload<Boolean> updateChainType(@RequestBody ChainTypeVO vo) {
        return new Payload<>(true);
    }

    /**
     * @MethodName: deleteCategorys
     * @Description: 批量删除连锁分类
     * @Param: [ids]
     * @Return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @DeleteMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "批量删除连锁分类")
    public Payload<Boolean> deleteChainTypes(@PathVariable(value = "id", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

    /**
     * @MethodName: getChainTypeList
     * @Description: 查询连锁分类列表，不查询上级分类信息
     * @Param: []
     * @Return: com.deepexi.util.config.Payload<com.deepexi.channel.domain.chain.ChainTypeVO>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @GetMapping("/list")
    @ApiOperation(value = "查询连锁分类列表，不查询上级分类信息")
    public Payload<List<ChainTypeVO>> getChainTypeList(){
        List<ChainTypeVO> result = new ArrayList<>();
        result.add(new ChainTypeVO());
        result.add(new ChainTypeVO());
        return new Payload<>(result);
    }



}