package org.kang.plus;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.kang.plus.entity.Actor;
import org.kang.plus.service.ActorService;
import org.kang.plus.service.impl.ActorServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-04-17
 * Time: 15:54
 */
@Slf4j
@SpringBootApplication
@MapperScan("org.kang.plus.mapper")
public class AppMain {

   /* @Autowired
    private ActorService actorService;*/

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AppMain.class);
        ActorService actorService = run.getBean(ActorService.class);
        List<Actor> list = actorService.list();
        list.forEach(actor -> {
            System.out.println(actor.toString());
        });

        DruidDataSource druidDataSource = run.getBean(DruidDataSource.class);
        System.out.println(druidDataSource == null);

    }
}
