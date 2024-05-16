package org.example.crud;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.List;
import java.util.Map;

/**
 * User:
 * Description:
 * Date: 2024-04-18
 * Time: 17:35
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class SelectTest {

    @Autowired
    private ActorMapper actorMapper;

    @Test
    public void selectById(){
        Actor actor = new Actor();
        actor.setId(1);
        Actor actor1 = actorMapper.selectById(actor);
        log.info("selectById:"+actor1);
    }

    @Test
    public void selectOne(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.eq("id",2);
        Actor actor1 = actorMapper.selectOne(queryWrapper);
        log.info("selectById:"+actor1);
    }

    @Test
    public void selectBatchIds(){
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(2);
        idList.add(3);
        List<Actor> actors = actorMapper.selectBatchIds(idList);
        actors.forEach(System.out::println);
    }

    @Test
    public void selectList(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.select("name","update_Time");
        queryWrapper.eq("id",1);
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void selectByMap(){
        HashMap<String, Object> ids = new HashMap<>();
        ids.put("id", 2);
        List<Actor> actors = actorMapper.selectByMap(ids);
        actors.forEach(System.out::println);
    }

    @Test
    public void selectMap(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.eq("id",2);
        List<Map<String, Object>> list = actorMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectObjs(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.select("id");
        List<Integer> list = actorMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectCount() {
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.eq("id", 1);
        Long aLong = actorMapper.selectCount(queryWrapper);
        log.info("aLong:"+aLong);
    }

    /**
     * 需要注入 MybatisPlusInterceptor
     */
    @Test
    public void seletePage(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.eq("name","updateWrapper");
        Page<Actor> page = actorMapper.selectPage(new Page<>(0,2 ), queryWrapper);
        long total = page.getTotal();
        List<Actor> records = page.getRecords();
        records.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void selectMapsPage(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.eq("name","updateWrapper");
        IPage<Map<String, Object>> page =
                actorMapper.selectMapsPage(new Page<>(0, 2), queryWrapper);
        long total = page.getTotal();
        List<Map<String, Object>> records = page.getRecords();
        records.forEach(System.out::println);

    }

}
