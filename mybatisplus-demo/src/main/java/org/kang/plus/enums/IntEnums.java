package org.kang.plus.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * User:
 * Description:
 * Date: 2024-05-13
 * Time: 17:44
 */
public enum IntEnums implements IEnum<Integer> {
    DISABLE(1, "高级"),
    ABLE(2, "中级"),
    LOCK(3, "低级");

    private final Integer code;

    private final String name;

    IntEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return code;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
