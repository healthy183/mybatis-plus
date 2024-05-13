package org.kang.plus.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-04-17
 * Time: 17:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class DruidTest {

    @Autowired
    private DruidDataSource dataSource;

    @Autowired
    private ActorService actorService;

    @Test
    public void connetCount(){
        System.out.println(dataSource.getClass());
        DruidDataSource druidDataSource = (DruidDataSource)dataSource;
        System.out.println(druidDataSource.getMaxActive());
    }

    @Test
    public void actorList(){
        List<Actor> list = actorService.list();
        list.forEach(actor -> {
            System.out.println(actor.toString());
        });
    }



}
