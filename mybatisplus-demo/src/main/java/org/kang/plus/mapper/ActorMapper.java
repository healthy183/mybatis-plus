package org.kang.plus.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.kang.plus.entity.Actor;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author healthy183
 * @since 2024-04-17
 */
public interface ActorMapper extends MPJBaseMapper<Actor> {

    // mapper方法(方法参数无注解)
    @Insert("insert into actor(id,name) values(#{id},#{name})")
    int myInsertWithoutParam(Actor actor);

    @Insert("insert into actor(id,name) values(#{actor.id},#{actor.name})")
    int myInsertWithParam(@Param("actor") Actor actor);

}
