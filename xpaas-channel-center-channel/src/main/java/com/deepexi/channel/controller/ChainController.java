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

    @GetMapping()
    @ApiOperation("查询连锁分类列表")
    public Payload<PageBean<ChainVO>> listChainPage(@ApiParam(name = "query", required = true) ChainQuery query){
        List<ChainVO> result = new ArrayList<>();
        result.add(new ChainVO());
        result.add(new ChainVO());
        return new Payload<>(new PageBean<>(result));
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取连锁店详情")
    public Payload<ChainVO> getChainById(@PathVariable Integer id){
        return new Payload<>(new ChainVO());
    }

    @PostMapping()
    @ApiOperation(value = "保存连锁")
    public Payload<Boolean> saveChain(@RequestBody ChainVO vo) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "更新连锁")
    public Payload<Boolean> updateChain(@RequestBody ChainVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "批量删除连锁")
    public Payload<Boolean> deleteChains(@PathVariable(value = "id", required = true) List<Long> ids) {
        return new Payload<>(true);
    }


}
