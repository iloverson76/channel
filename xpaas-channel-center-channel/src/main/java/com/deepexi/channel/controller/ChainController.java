package com.deepexi.channel.controller;

import com.deepexi.channel.businness.ChainBusinessService;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.chain.*;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.ChainService;
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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "/连锁管理", description = "连锁管理")
@RestController
@RequestMapping("/api/v1/chain")
public class ChainController {

    @Autowired
    private ChainService chainService;
    @Autowired
    private ChainBusinessService chainBusinessService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload<PageBean<ChainDetailVO>> findPage(@ApiParam(name = "query", required = true) ChainQuery query) {
        List<ChainDetailDTO> chainDetailDTOS = chainBusinessService.findPage(query);
        List<ChainDetailVO> chainDetailVOS = ObjectCloneUtils.convertList(chainDetailDTOS,ChainDetailVO.class, CloneDirection.OPPOSITE);
        return new Payload(new PageBean<>(chainDetailVOS));
    }

//    @GetMapping("/list")
//    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
//    public Payload findAll(CcChain eo) {
//        return new Payload(chainService.findAll(eo));
//    }
//
    @GetMapping("/{id}")
    @ApiOperation("根据id获取连锁店详情")
    public Payload<ChainDetailVO> detail(@PathVariable(value = "id", required = true) Long id) {
        ChainDetailDTO chainDetailDTO = chainBusinessService.getChain(id);
        if(chainDetailDTO == null){
            return new Payload(null);
        }
        return new Payload(chainDetailDTO.clone(ChainDetailVO.class, CloneDirection.OPPOSITE));
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改", notes = "根据id修改连锁")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long id, @RequestBody ChainDetailVO vo) {
        if(vo == null){
            return new Payload(false);
        }
        vo.setId(id);
        ChainDetailDTO dto = vo.clone(ChainDetailDTO.class);
        //判断编码是否重复
        if(!chainService.isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        List<BankAccountDTO> bankAccountList = ObjectCloneUtils.convertList(vo.getBankAccountList(), BankAccountDTO.class);
        dto.setBankAccountList(bankAccountList);
         return new Payload(chainBusinessService.updateChain(dto));
    }

    @PostMapping
    @ApiOperation(value = "创建连锁", notes = "创建连锁,创建成功返回id")
    public Payload<Boolean> create(@RequestBody ChainDetailVO chainDetailVO) {
        if(chainDetailVO == null){
            return new Payload(false);
        }
        ChainDetailDTO dto = chainDetailVO.clone(ChainDetailDTO.class);
        //校验编码是否重复
        if(!chainService.isCodeUnique(dto)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        List<BankAccountDTO> bankAccountList = ObjectCloneUtils.convertList(chainDetailVO.getBankAccountList(), BankAccountDTO.class);
        dto.setBankAccountList(bankAccountList);
        return new Payload(chainBusinessService.insertChain(dto));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据id批量删除连锁", notes = "根据id删除连锁")
    public Payload<Boolean> delete(@PathVariable(value = "id") String id) {
        List<Long> ids = Arrays.stream(id.split(",")).map(Long::parseLong).collect(Collectors.toList());
        if(chainBusinessService.deleteVerification(ids)){
            return new Payload<>(chainBusinessService.deleteChain(ids));
        }else{
            return new Payload<>(false);
        }
    }

    @PostMapping("/tree")
    @ApiOperation(value = "保存连锁树", notes = "保存连锁树,整颗树一起保存，关键是id与children里的id")
    public Payload<Boolean> saveTree(@RequestBody List<ChainTreeVO> vos){
        if(CollectionUtil.isEmpty(vos)){
            return new Payload<>(false);
        }
        List<ChainTreeDTO> chainTreeDTOS = ObjectCloneUtils.convertList(vos, ChainTreeDTO.class, CloneDirection.OPPOSITE);
        return new Payload<>(chainBusinessService.saveTree(chainTreeDTOS));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取连锁树结构,默认展示三级",notes = "前端无需传参数")
    public Payload<List<ChainTreeVO>> getTree(){
        List<ChainTreeDTO> chainTreeDtos = chainBusinessService.getTree();
        if(CollectionUtil.isEmpty(chainTreeDtos)){
            return new Payload<>(null);
        }else{
            List<ChainTreeVO> chainTreeVOS = ObjectCloneUtils.convertList(chainTreeDtos, ChainTreeVO.class, CloneDirection.OPPOSITE);
            return new Payload<>(chainTreeVOS);
        }
    }

    @GetMapping("/tree/{id}")
    @ApiOperation(value="根据id获取下级节点",notes = "传0时代表查询所有根节点，其他id则查询子节点")
    public Payload<List<ChainTreeVO>> getChildren(@PathVariable(value = "id", required = true) Long id){
        List<ChainTreeDTO> chainTreeDTOS = chainBusinessService.getChildren(id);
        if(CollectionUtil.isEmpty(chainTreeDTOS)){
            return new Payload<>(null);
        }
        List<ChainTreeVO> chainTreeVOS = ObjectCloneUtils.convertList(chainTreeDTOS, ChainTreeVO.class);
        return new Payload<>(chainTreeVOS);
    }

    @PutMapping("/tree/node/{id}")
    @ApiOperation(value="树形结构新增、修改节点接口",notes = "传parentId以及节点的id，自动更新该节点下所有子节点和孙子节点")
    public Payload<Boolean> addTreeNode(@PathVariable(value = "id", required = true) Long id, @RequestBody ChainVO vo){
        vo.setId(id);
        if(vo==null){return new Payload<>(false);}
        ChainDTO chainDTO = vo.clone(ChainDTO.class);
        return new Payload<>(chainBusinessService.addTreeNode(chainDTO));
    }

    @DeleteMapping("/tree/node/{id}")
    @ApiOperation(value="树形结构删除节点接口",notes = "")
    public Payload<Boolean> deleteTreeNode(@PathVariable(value = "id" , required = true)Long id){
        return new Payload<>(chainBusinessService.deleteTreeNode(id));
    }

}