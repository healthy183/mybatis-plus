package org.kang.plus.abstractWrapper;

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
import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-05-10
 * Time: 12:35
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class AbstractWrapper {


    @Autowired
    private ActorMapper actorMapper;

    @Test
    public void allEq(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.select("name","update_Time");
        HashMap<String, String> map = new HashMap<>();
        map.put("id","1");
        map.put("name","11");
        queryWrapper.allEq(map);
        // SELECT name,update_Time FROM actor WHERE (name = ? AND id = ?)
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void allEqNull(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.select("name","update_Time");
        HashMap<String, String> map = new HashMap<>();
        map.put("id","1");
        map.put("name",null);  // null的话不加入sql
        queryWrapper.allEq(map,false);
        // SELECT name,update_Time FROM actor WHERE ( id = ?)
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }


    @Test
    public void ne() {
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        queryWrapper.ne("id","1");  // 不等于
        queryWrapper.gt("id","1"); // 大于
        queryWrapper.ge("id","1"); // 大于等于
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void lt() {
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        queryWrapper.lt("id","3");  // 小于
        queryWrapper.le("id","3"); // 小于等于
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void between() {
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        queryWrapper.between("id",1,3);
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void notBetween() {
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        queryWrapper.notBetween("id",1,2);
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void like() {
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        // queryWrapper.like("name","a");    // like %a%
        //queryWrapper.notLike("name","a");  // not like %a%
        //queryWrapper.likeLeft("name","a");  // like %a,也支持not  like
        queryWrapper.likeRight("name","A");  //like a%,也支持not  like
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void nullTest(){
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        queryWrapper.isNull("update_time");
        queryWrapper.isNotNull("name");
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void inTest(){
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        List<String> intList = new ArrayList<>();
        intList.add("1");
        intList.add("2");
        queryWrapper.in("id",intList);

        List<String> noIntList = new ArrayList<>();
        noIntList.add("3");
        noIntList.add("4");
        queryWrapper.notIn("id",noIntList);
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }


    @Test
    public void inSQL(){
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        queryWrapper.inSql("id","1,2");
        queryWrapper.notInSql("id","3,4");
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void sqlTest(){
        QueryWrapper<Actor> queryWrapper = new QueryWrapper();
        //  WHERE (id = (select MAX(id) from actor))
        // queryWrapper.eqSql("id", "select MAX(id) from actor");

        // gtSql  geSql  ltSql  leSql 都支持
        //WHERE (id > (select MAX(id) from actor))
        queryWrapper.leSql("id", "select MAX(id) from actor");
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void groupBy(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.groupBy("id");
        queryWrapper.having("id > 2");
        queryWrapper.orderBy(true,false,"id","name");
        /*queryWrapper.orderByAsc("id");
        queryWrapper.orderByDesc("name");*/
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test   // 不会用
    public  void orTest(){

       /* QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.or(i -> i.eq("name", "李白").ne("status", "活着"));*/
    }

    @Test // 不会用
    public void nested(){

        /*QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        queryWrapper.nested(i -> i.eq("name", "李白").ne("status", "活着"))*/
    }


    @Test // 不会用
    public void apply(){

    }

    /**
     * 无视优化规则直接拼接到 sql 的最后
     * 只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
     */
    @Test
    public void last(){

    }


    @Test
    public void exists(){
        QueryWrapper<Actor> queryWrapper = new QueryWrapper<>();
        // queryWrapper.select(Actor.class,"t");
        queryWrapper.exists("select id from film_actor a where a.actor_id = actor.id ");
        List<Actor> list = actorMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }


    @Test // 不会用
    public void setIncrBy(){

    }
}
