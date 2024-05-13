package org.kang.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author healthy183
 * @since 2024-04-19
 */
@Getter
@Setter
@TableName("film_actor")
public class FilmActor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("film_id")
    private Integer filmId;

    @TableField("actor_id")
    private Integer actorId;

    @TableField("remark")
    private String remark;
}
