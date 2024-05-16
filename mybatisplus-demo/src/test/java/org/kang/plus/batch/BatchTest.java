package org.kang.plus.batch;

import com.baomidou.mybatisplus.core.batch.MybatisBatch;
import com.baomidou.mybatisplus.core.toolkit.MybatisBatchUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.*;

/**
 * User:
 * Description:
 * Date: 2024-05-15
 * Time: 10:49
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class BatchTest {

    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    /**
     * 批量更新
     */
    @Test
    public void insertDTO(){
        ArrayList<Actor> actors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Actor actor = new Actor();
            actor.setName("plus"+(i+1));
            actor.setUpdateTime(LocalDateTime.now());
            actors.add(actor);
        }
        MybatisBatch<Actor> mybatisBatch = new MybatisBatch<>(sqlSessionFactory, actors);
        MybatisBatch.Method<Actor> method = new MybatisBatch.Method<>(ActorMapper.class);
        mybatisBatch.execute(method.insert());
    }


    @Test
    public void insertStr(){
        List<String> ids = Arrays.asList("insertStr1", "insertStr2");
        MybatisBatch<String> mybatisBatch = new MybatisBatch<>(sqlSessionFactory, ids);
        MybatisBatch.Method<Actor> method = new MybatisBatch.Method<>(ActorMapper.class);
        mybatisBatch.execute(method.insert(name -> {
            // 将id转换为实体
            Actor h2User = new Actor();
            h2User.setName(name);
            return h2User;
        }));
    }

    @Test
    public void insertWithoutParam(){
        Random random = new Random();
        ArrayList<Actor> actors = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            Actor actor = new Actor();
            actor.setId(random.nextInt());
            actor.setName("insertWithoutParam"+(i+1));
            actor.setUpdateTime(LocalDateTime.now());
            actors.add(actor);
        }
        MybatisBatch<Actor> mybatisBatch = new MybatisBatch<>(sqlSessionFactory, actors);
        MybatisBatch.Method<Actor> method = new MybatisBatch.Method<>(ActorMapper.class);
        mybatisBatch.execute(method.get("myInsertWithoutParam"));
    }

    @Test
    public void myInsertWithParam(){
        Random random = new Random();
        ArrayList<Actor> actors = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            Actor actor = new Actor();
            actor.setId(random.nextInt());
            actor.setName("myInsertWithParam"+(i+1));
            actor.setUpdateTime(LocalDateTime.now());
            actors.add(actor);
        }
        MybatisBatch<Actor> mybatisBatch = new MybatisBatch<>(sqlSessionFactory, actors);
        MybatisBatch.Method<Actor> method = new MybatisBatch.Method<>(ActorMapper.class);
        mybatisBatch.execute(method.get("myInsertWithParam",(actor) ->{
            // 转换成mapper方法参数
            Map<String, Object> map = new HashMap<>();
            map.put("actor", actor);
            return map;
        }));
    }


    /**
     * 跨sqlSession
     */
    @Test
    public void saveORupdate(){
        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor = new Actor();
        actor.setId(1);
        actor.setName("saveORupdate1");
        actor.setUpdateTime(LocalDateTime.now());
        actors.add(actor);

        Actor actor2 = new Actor();
        actor2.setId(2);
        actor2.setName("saveORupdate2");
        actor2.setUpdateTime(LocalDateTime.now());
        actors.add(actor2);

        MybatisBatch.Method<Actor> mybatisBatch = new MybatisBatch.Method<>(ActorMapper.class);
        new MybatisBatch<>(sqlSessionFactory,actors).saveOrUpdate(
                mybatisBatch.insert(), // 指定insert方法
                ((sqlSession, h2User) -> actorMapper.selectById(h2User.getId()) == null),  //判断条件,引用另个mapper方法
                mybatisBatch.updateById()); // 指定update方法
    }

    /**
     * 同sqlSession的saveORupdate
     */
    @Test
    public void sameSession(){

        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor = new Actor();
        actor.setId(886);
        actor.setName("saveORupdate886");
        actor.setUpdateTime(LocalDateTime.now());
        actors.add(actor);

        Actor actor2 = new Actor();
        actor2.setId(2);
        actor2.setName("saveORupdate886");
        actor2.setUpdateTime(LocalDateTime.now());
        actors.add(actor2);

        MybatisBatch.Method<Actor> mapperMethod = new MybatisBatch.Method<>(ActorMapper.class);
        new MybatisBatch<>(sqlSessionFactory,actors).saveOrUpdate(mapperMethod.insert(), // 指定insert方法
                ((sqlSession, h2User) -> sqlSession.selectList(mapperMethod.get("selectById").getStatementId(), h2User.getId()).isEmpty()), //判断条件,共用sqlSession
                mapperMethod.updateById()); // 指定update方法
    }

    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 事务回滚
     */
    @Test
    public void transactionTest(){

        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor = new Actor();
        actor.setId(4545);
        actor.setName("saveORupdate777");
        actor.setUpdateTime(LocalDateTime.now());
        actors.add(actor);

        Actor actor2 = new Actor();
        actor2.setId(34545);
        actor2.setName("saveORupdate777");
        actor2.setUpdateTime(LocalDateTime.now());
        actors.add(actor2);

        transactionTemplate.execute((TransactionCallback<List<BatchResult>>) status -> {
            MybatisBatch.Method<Actor> mapperMethod = new MybatisBatch.Method<>(ActorMapper.class);
            // 执行批量插入
            MybatisBatchUtils.execute(sqlSessionFactory, actors, mapperMethod.insert());
            throw new RuntimeException("出错了");
        });
    }
}
