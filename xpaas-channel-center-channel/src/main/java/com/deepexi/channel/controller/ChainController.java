package com.deepexi.channel.controller;

import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.domain.chain.ChainVO;
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
 * 连锁表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/chain")
@Api("连锁管理")
public class ChainController {
    /**
     * @MethodName: listChainPage
     * @Description: 查询连锁分类列表
     * @Param: [query]
     * @Return: com.deepexi.util.config.Payload<com.deepexi.util.pageHelper.PageBean<com.deepexi.channel.domain.chain.ChainVO>>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @GetMapping()
    @ApiOperation("查询连锁分类列表")
    public Payload<PageBean<ChainVO>> listChainPage(@ApiParam(name = "query", required = true) ChainQuery query){
        List<ChainVO> result = new ArrayList<>();
        result.add(new ChainVO());
        result.add(new ChainVO());
        return new Payload<>(new PageBean<>(result));
    }
    /**
     * @MethodName: getChainById
     * @Description: 根据id获取连锁店详情
     * @Param: [id]
     * @Return: com.deepexi.util.config.Payload<com.deepexi.channel.domain.chain.ChainVO>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取连锁店详情")
    public Payload<ChainVO> getChainById(@PathVariable Long id){
        return new Payload<>(new ChainVO());
    }
    /**
     * @MethodName: saveChain
     * @Description: 保存连锁
     * @Param: [vo]
     * @Return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @PostMapping()
    @ApiOperation(value = "保存连锁")
    public Payload<Boolean> saveChain(@RequestBody ChainVO vo) {
        return new Payload<>(true);
    }
    /**
     * @MethodName: updateChain
     * @Description: 更新连锁
     * @Param: [vo]
     * @Return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @PutMapping()
    @ApiOperation(value = "更新连锁")
    public Payload<Boolean> updateChain(@RequestBody ChainVO vo) {
        return new Payload<>(true);
    }
    /**
     * @MethodName: deleteChains
     * @Description: 批量删除连锁
     * @Param: [ids]
     * @Return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: mumu
     * @Date: 2019/8/26
    **/
    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "批量删除连锁")
    public Payload<Boolean> deleteChains(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

    @GetMapping("/tree")
    public Payload<List<ChainVO>> getTree(){
        List<ChainVO> result = new ArrayList<>();
        List<ChainVO> children = new ArrayList<>();
        children.add(new ChainVO());
        children.add(new ChainVO());
        children.add(new ChainVO());
        ChainVO parent = new ChainVO();
        parent.setChildren(children);
        result.add(parent);
        result.add(new ChainVO());
        result.add(new ChainVO());
        return new Payload<>(result);
    }

    @PutMapping("/tree")
    public Payload<Boolean> updateTree(@RequestBody List<ChainVO> list){
        return new Payload<>(true);
    }

    @GetMapping("/list")
    public Payload<PageBean<ChainVO>> getList(ChainQuery query){
        List<ChainVO> result = new ArrayList<>();
        result.add(new ChainVO());
        result.add(new ChainVO());
        return new Payload<>(new PageBean<>(result));
    }

}
