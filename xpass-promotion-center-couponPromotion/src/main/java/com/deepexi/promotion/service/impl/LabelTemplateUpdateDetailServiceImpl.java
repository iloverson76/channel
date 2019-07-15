package com.deepexi.promotion.service.impl;

import com.deepexi.promotion.domain.CommentLabelTemplateDTO;
import com.deepexi.promotion.service.UpdateDetailService;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhoust
 * @date 2019/5/20
 **/
public class LabelTemplateUpdateDetailServiceImpl implements UpdateDetailService<CommentLabelTemplateDTO> {


    public enum UpdateDetailEnum {
        NAME_UPDATE(0, "NAME_UPDATE", "名称更新");

        private final Integer code;
        private final String enName;
        private final String cnName;

        public Integer getCode() {
            return code;
        }

        public String getEnName() {
            return enName;
        }

        public String getCnName() {
            return cnName;
        }

        UpdateDetailEnum(Integer code, String enName, String cnName) {
            this.code = code;
            this.enName = enName;
            this.cnName = cnName;
        }

        public static UpdateDetailEnum getByCode(Integer code) {
            if (code == null) return null;
            for (UpdateDetailEnum detailEnum : values()) {
                if (detailEnum.getCode().equals(code))
                    return detailEnum;
            }
            return null;
        }
    }


    @Override
    public String getUpdateCodesDetail(CommentLabelTemplateDTO update, CommentLabelTemplateDTO old) {
        StringBuilder builder = new StringBuilder();
        if (!update.getName().equals(old.getName())) {
            builder.append(UpdateDetailEnum.NAME_UPDATE.getCode());
            builder.append(SEPARATOR);
        }
        if (builder.length() == 0){
            return "";
        }
        return builder.substring(0, builder.length() - 1);
    }

    @Override
    public String getUpdateCnNamesByCodes(String codes) {
        if (StringUtils.isEmpty(codes)) return null;
        StringBuilder builder = new StringBuilder();
        String[] allCode = codes.split(SEPARATOR);
        for (int i = 0; i < allCode.length; i++) {
            UpdateDetailEnum detailEnum = UpdateDetailEnum.getByCode(Integer.valueOf(allCode[i]));
            if (detailEnum != null)
                builder.append(detailEnum.getCnName());
            builder.append(SEPARATOR);
        }
        return builder.substring(0, builder.length() - 1);
    }
}
