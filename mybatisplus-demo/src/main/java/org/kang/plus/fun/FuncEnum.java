package org.kang.plus.fun;

import com.github.yulichang.wrapper.enums.BaseFuncEnum;

/**
 * User:
 * Description:
 * Date: 2024-04-23
 * Time: 16:32
 */
public enum FuncEnum implements BaseFuncEnum {

    DATE_FORMAT("DATE_FORMAT(%s, '%%Y-%%m-%%d')"),
    IF_SEX("IF(%s=1,'男','女')"),                         //if 性别转换
    CASE_SEX("CASE %s WHEN 1 THEN '男' ELSE '女' END"),   //case 性别转换
    LCASE("LCASE(%s)");

    private final String sql;

    FuncEnum(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSql() {
        return this.sql;
    }
}
