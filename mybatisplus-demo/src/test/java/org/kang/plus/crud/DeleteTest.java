package org.kang.plus.crud;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * User:
 * Description:
 * Date: 2024-05-09
 * Time: 12:23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class DeleteTest {

    @Autowired
    private ActorMapper actorMapper;

    @Test
    public void delteById(){
        Actor actor = new Actor();
        actor.setId(105496578);
        actor.setName("plusUpdate");
        int i = actorMapper.deleteById(actor);
        log.info("insert count:"+i);
    }

    @Test
    public void delete(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.eq("id", 1);
        int i = actorMapper.delete(queryWrapper);
        log.info("insert count:"+i);
    }

    @Test
    public void deleteBatchIds(){
        ArrayList<Actor> objects = new ArrayList<>();
        Actor actor = new Actor();
        actor.setId(105496578);
        objects.add(actor);
        int i = actorMapper.deleteBatchIds(objects);
        log.info("insert count:"+i);
    }

    @Test
    public  void deleteByMap(){
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("id",105496578);
        int i = actorMapper.deleteByMap(objectObjectHashMap);
        log.info("insert count:"+i);
    }
}
