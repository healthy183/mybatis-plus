package org.kang.plus.crud;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * User:
 * Description:
 * Date: 2024-05-09
 * Time: 12:22
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class InsertTest {

    @Autowired
    private ActorMapper actorMapper;


    @Test
    public void insert(){
        Actor actor = new Actor();
        actor.setName("plus");
        actor.setUpdateTime(LocalDateTime.now());
        int insert = actorMapper.insert(actor);
        log.info("insert count:"+insert);
    }

}
