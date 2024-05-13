package org.kang.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author healthy183
 * @since 2024-04-17
 */
@Getter
@Setter
@TableName("actor")
@ToString
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value="id") // type= IdType.AUTO表示主键需要手动设置
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
