package org.kang.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.kang.plus.enums.IntEnums;
import org.kang.plus.enums.StringEnums;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author healthy183
 * @since 2024-05-13
 */
@Getter
@Setter
@TableName("mbp_sample")
public class MbpSample implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;


    @TableField("login_delete")
    private Integer loginDelete;

    @TableField("enum_int")
    private IntEnums enumInt;

    @TableField("enum_str")
    private StringEnums enumStr;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
