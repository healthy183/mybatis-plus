package org.kang.plus.custom;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-05-17
 * Time: 10:44
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class CustomTest {

    @Autowired
    private ActorMapper actorMapper;

    @Test
    public void selectByMyWrapper(){
        MPJLambdaWrapper<Actor> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.eq(Actor::getName,"plus");
        List<Actor> actors = actorMapper.selectByMyWrapper(queryWrapper);
        actors.forEach(System.out::println);
    }

    @Test
    public void updateByMyWrappers(){
        MPJLambdaWrapper<Actor> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.eq(Actor::getName,"plus");
        Actor actor = new Actor();
        actor.setName("updateByMyWrappers");
        int count = actorMapper.updateByMyWrappers(queryWrapper,actor);
        System.out.println(count);
    }

}
