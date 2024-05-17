package org.kang.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.kang.plus.entity.Actor;

import java.util.List;

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

    @Select("SELECT * FROM actor t ${ew.customSqlSegment}")
    List<Actor> selectByMyWrapper(@Param(Constants.WRAPPER) Wrapper<Actor> userWrapper);

    @Update("UPDATE actor t SET t.name = #{actor.name} ${ew.customSqlSegment}")
    int updateByMyWrappers(@Param(Constants.WRAPPER)Wrapper<Actor> userWrapper,
                      @Param("actor") Actor actor);

}
