package org.kang.plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author healthy183
 * @since 2024-05-14
 */
@Getter
@Setter
@TableName(value="mbp_dto",autoResultMap = true)
public class MbpDto extends Model<MbpDto> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField(value="contact",typeHandler = FastjsonTypeHandler.class)  //指定字段类型处理器
    private Map<String,String> contact;  // 添加map类型的参数

    @TableField(value ="create_time",fill = FieldFill.INSERT)  // 指定填充时机
    private Date createTime;

    @TableField(value ="update_time",fill = FieldFill.INSERT_UPDATE) // 指定填充时机
    private Date updateTime;

    @Version
    @TableField("version")
    private Integer version;
}
