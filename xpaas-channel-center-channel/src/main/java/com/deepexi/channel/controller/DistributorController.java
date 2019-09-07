package com.deepexi.channel.controller;

import com.deepexi.channel.businness.DistributorBusinessService;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeVO;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountVO;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        List<DistributorGradeDTO> grades =
                ObjectCloneUtils.convertList(vo.getGrades(), DistributorGradeDTO.class, CloneDirection.FORWARD);

        List<BankAccountDTO> bankAccounts =
                ObjectCloneUtils.convertList(vo.getBankAccounts(), BankAccountDTO.class, CloneDirection.FORWARD);

        DistributorDTO dto = new DistributorDTO();

        BeanUtils.copyProperties(vo, dto);

        dto.setGrades(grades);

        dto.setBankAccounts(bankAccounts);

        return new Payload(distributorBusinessService.create(dto));
    }

    @DeleteMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id批量删除经销商")
    public Payload<Boolean> delete(@PathVariable(value = "id") String ids) {

        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        return new Payload<>(distributorBusinessService.delete(idList));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id和分类id查看区域详情")
    public Payload<DistributorVO> detail(@PathVariable(value = "id", required = true) Long id) {

        DistributorVO vo = new DistributorVO();//distributorBusinessService.detail(id);

        /*
        AreaTypeVO type=dto.getAreaType().clone(AreaTypeVO.class,CloneDirection.OPPOSITE);

        AreaVO vo=new AreaVO();

        BeanUtils.copyProperties(dto,vo);

        vo.setAreaType(type);
*/
        return new Payload<>(vo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long pk, @RequestBody DistributorVO vo) {

        vo.setId(pk);

        distributorBusinessService.update(vo.clone(DistributorDTO.class, CloneDirection.FORWARD));

        return new Payload<>(Boolean.TRUE);
    }

    @GetMapping()
    @ApiOperation("查询经销商列表-分页查询")
    public Payload<PageBean<DistributorVO>> listAreaPage(@ApiParam(name = "query", required = true) DistributorQuery query) {

        List<DistributorDTO> dtoList = distributorBusinessService.findPage(query);

        List<DistributorVO> voList = new ArrayList<>();

        dtoList.forEach(dto -> {

            List<DistributorGradeVO> gradeDTOList = ObjectCloneUtils.convertList(dto.getGrades(), DistributorGradeVO.class, CloneDirection.OPPOSITE);

            List<BankAccountVO> bankAccountDTOList = ObjectCloneUtils.convertList(dto.getBankAccounts(), BankAccountVO.class, CloneDirection.OPPOSITE);
            ;

            DistributorVO vo = new DistributorVO();

            BeanUtils.copyProperties(dto, vo);

            vo.setGrades(gradeDTOList);

            vo.setBankAccounts(bankAccountDTOList);

            voList.add(vo);
        });

        return new Payload<>(new PageBean<>(voList));
    }

    //等级信息查询
    @GetMapping("/grade/{id}")
    @ApiOperation("经销商详情-等级信息查询")
    public Payload<PageBean<DistributorGradeVO>> listGradeInfo(@ApiParam(name = "id", required = true) long id) {

        return new Payload<>(new PageBean<>(new ArrayList<DistributorGradeVO>()));
    }

    //银行信息查询
    @GetMapping("/bankAccount/{id}")
    @ApiOperation("经销商详情-分页查询")
    public Payload<PageBean<BankAccountVO>> listBankAccountInfo(@ApiParam(name = "id", required = true) long id) {

        return new Payload<>(new PageBean<>(new ArrayList<BankAccountVO>()));
    }

    //区域信息查询
    @GetMapping("/area/{id}")
    @ApiOperation("经销商详情-分页查询")
    public Payload<PageBean<AreaVO>> listBankAreaInfo(@ApiParam(name = "id", required = true) long id) {

        return new Payload<>(new PageBean<>(new ArrayList<AreaVO>()));
    }

}


