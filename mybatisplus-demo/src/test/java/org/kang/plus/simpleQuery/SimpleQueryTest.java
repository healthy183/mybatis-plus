package org.kang.plus.simpleQuery;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.entity.FilmActor;
import org.kang.plus.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * User:
 * Description:
 * Date: 2024-05-10
 * Time: 11:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class SimpleQueryTest {


    @Autowired
    private ActorMapper actorMapper;

    @Test
    public void keyMap(){
        LambdaQueryWrapper<Actor> eq =
                Wrappers.<Actor>lambdaQuery().eq(Actor::getId, 2L);
        // 我要这个表里对应条件的用户，用id作为key给我一个map
        Map<Integer, Actor> integerActorMap = SimpleQuery.keyMap(eq, Actor::getId);
        integerActorMap.values().forEach(System.out::println);

        // 如果我只想要id和name组成的map
        Map<Integer, String> idNameMap = SimpleQuery.map(eq, Actor::getId, Actor::getName);
        idNameMap.entrySet().forEach(System.out::println);

        // 简单查询,以FilmActor::getRemark为key输出map
        LambdaQueryWrapper<FilmActor> filmActorWrapper =
                Wrappers.<FilmActor>lambdaQuery().eq(FilmActor::getFilmId, 3);
        Map<String, List<FilmActor>> nameUsersMap =
                SimpleQuery.group(filmActorWrapper, FilmActor::getRemark);
        nameUsersMap.entrySet().forEach(System.out::println);
    }


    @Test
    public void lists(){
        LambdaQueryWrapper<Actor> eq =
                Wrappers.<Actor>lambdaQuery().eq(Actor::getId, 2L);
        List<Integer> list = SimpleQuery.list(eq, Actor::getId);
        list.forEach(System.out::println);
    }


}
