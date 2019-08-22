package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.TemplateTypeAddVO;
import com.deepexi.promotion.domain.TemplateTypeQuery;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@RestController
@RequestMapping("/api/v1/promotion/coupon/type")
public class PromotionCouponTypeController {


    @PutMapping
    public void addType(@RequestBody TemplateTypeAddVO type) {


    }

    @PutMapping("/{id}")
    public void updateType(@PathVariable(value = "id", required = true) Long id, @RequestBody TemplateTypeAddVO type) {


    }

    @PutMapping("/{id}/enable")
    public void enableType(@PathVariable(value = "id", required = true) Long id) {


    }


    @GetMapping
    public void listType(@RequestBody TemplateTypeQuery query) {


    }
}
