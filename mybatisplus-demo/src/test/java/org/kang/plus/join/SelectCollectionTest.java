package org.kang.plus.join;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.dto.ActorDTO;
import org.kang.plus.entity.Actor;
import org.kang.plus.entity.FilmActor;
import org.kang.plus.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-05-07
 * Time: 17:46
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class SelectCollectionTest {

    @Autowired
    private ActorMapper actorMapper;


    /**
     * 一对多
     */
    @Test
    public void selectCollection(){
        MPJLambdaWrapper<Actor> actorMPJLambdaWrapper = new MPJLambdaWrapper<>();
        actorMPJLambdaWrapper
                .selectAll(Actor.class)
                .selectCollection(FilmActor.class, ActorDTO::getFilmActorList)
                .leftJoin(FilmActor.class, FilmActor::getActorId, Actor::getId);
        List<ActorDTO> dtoList = actorMapper.selectJoinList(ActorDTO.class, actorMPJLambdaWrapper);
        dtoList.forEach(System.out::println);
    }

    /**
     * 一对一
     */
    @Test
    public void selectAssociation(){
        MPJLambdaWrapper<Actor> actorMPJLambdaWrapper = new MPJLambdaWrapper<>();
        actorMPJLambdaWrapper
                .selectAll(Actor.class)
                .selectAssociation(FilmActor.class, ActorDTO::getFilmActor)
                .leftJoin(FilmActor.class, FilmActor::getActorId, Actor::getId)
                .eq(Actor::getId,"3");
        List<ActorDTO> dtoList = actorMapper.selectJoinList(ActorDTO.class, actorMPJLambdaWrapper);
        dtoList.forEach(System.out::println);
    }


}
