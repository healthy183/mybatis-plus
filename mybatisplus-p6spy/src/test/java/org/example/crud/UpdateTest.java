package org.example.crud;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User:
 * Description:
 * Date: 2024-05-09
 * Time: 12:23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class UpdateTest {

    @Autowired
    private ActorMapper actorMapper;


    @Test
    public void updateById(){
        Actor actor = new Actor();
        actor.setId(1);
        actor.setName("plusUpdate");
        int i = actorMapper.updateById(actor);
        log.info("insert count:"+i);
    }

    @Test
    public void update(){
        UpdateWrapper updateWrapper = new UpdateWrapper<Actor>();
        updateWrapper.set("name","updateWrapper");
        updateWrapper.eq("id",2);
        int i = actorMapper.update(updateWrapper);
        log.info("insert count:"+i);
    }

    @Test
    public void updateDTO(){

        Actor actor = new Actor();
        actor.setName("updateDTO");

        UpdateWrapper updateWrapper = new UpdateWrapper<Actor>();
        updateWrapper.eq("id",2);
        int i = actorMapper.update(actor,updateWrapper);
        log.info("update count:"+i);
    }

    @Test
    @Deprecated
    public void updateDTO2(){
        Actor actor = new Actor();
        actor.setId(3); // 这么设置没用，因为Actor适用于设置需要修改的属性，主键要在UpdateWrapper设置

        UpdateWrapper updateWrapper = new UpdateWrapper<Actor>();
        updateWrapper.set("name","updateWrapper");
        // 语句是 UPDATE actor SET name=?
        int i = actorMapper.update(actor,updateWrapper);
        log.info("update count:"+i);
    }


}
