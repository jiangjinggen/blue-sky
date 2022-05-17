package com.iblue.esky.ecommerce.goods.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <h1>品牌分类</h1>
 * */
public enum BrandCategory {

    BRAND_A("20001", "品牌A"),
    BRAND_B("20002", "品牌B"),
    BRAND_C("20003", "品牌C"),
    BRAND_D("20004", "品牌D"),
    BRAND_E("20005", "品牌E"),
    ;

    /** 品牌分类编码 */
    @EnumValue
    private final String code;

    /** 品牌分类描述信息 */
    @JsonValue
    private final String description;

    BrandCategory(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    /**
     * <h2>根据 code 获取到 BrandCategory</h2>
     * */
    public static BrandCategory of(String code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(code + " not exists")
                );
    }
}
