package org.kang.plus.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * User:
 * Description:
 * Date: 2024-05-13
 * Time: 17:59
 */
public enum StringEnums implements IEnum<String> {

    DISABLE("a", "高级"),  ABLE("b", "中级"),  LOCK("c", "低级");

    private final String code;

    private final String name;

    StringEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getValue() {
        return code;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
