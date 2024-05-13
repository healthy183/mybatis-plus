package org.kang.plus.join;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.github.yulichang.wrapper.segments.Fun;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.dto.ActorDTO;
import org.kang.plus.entity.Actor;
import org.kang.plus.fun.FuncEnum;
import org.kang.plus.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-04-23
 * Time: 16:25
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class FuntionTest {

    @Autowired
    private ActorMapper actorMapper;

    @Test
    public void selectFun() {
        MPJLambdaWrapper<Actor> actorMPJLambdaWrapper = new MPJLambdaWrapper<>();
        actorMPJLambdaWrapper
                .selectSum(Actor::getId,ActorDTO::getIdSum)
                .selectCount(Actor::getName, ActorDTO::getNameCount)
                .selectMax(Actor::getId,ActorDTO::getIdMax)
                .selectMin(Actor::getId,ActorDTO::getIdMin)
                .selectAvg(Actor::getId,ActorDTO::getIdAvg);
        ActorDTO actorDTO = actorMapper.selectJoinOne(ActorDTO.class,actorMPJLambdaWrapper);
        log.info("result:"+actorDTO);
    }

    /**
     * mysql没有len只有length，所以这里会报错
     */
    @Test
    @Deprecated
    public void len(){
        MPJLambdaWrapper<Actor> actorMPJLambdaWrapper = new MPJLambdaWrapper<>();
        actorMPJLambdaWrapper.selectLen(Actor::getName,ActorDTO::getNameLength);
        List<ActorDTO> actorDTOS = actorMapper
                .selectJoinList(ActorDTO.class, actorMPJLambdaWrapper);
        actorDTOS.forEach(System.out::println);
    }

    @Test
    public  void funcTest(){
        MPJLambdaWrapper<Actor> objectMPJLambdaWrapper = new MPJLambdaWrapper<Actor>()
                // 自定义的函数枚举
                .selectFunc(FuncEnum.DATE_FORMAT, Actor::getUpdateTime,ActorDTO::getUpdateTimeString)
                .selectFunc(FuncEnum.LCASE, Actor::getName,ActorDTO::getNameLcase)
                // 也可以用lambda自定义
                .selectFunc(() -> "IF(%s=1,'男','女')", Actor::getId,ActorDTO::getSex)
                // 支持多个通配符,参数顺序与arg参数顺序保持一致
                .selectFunc("concat(%s, %s)", arg -> arg.accept(Actor::getName, Actor::getId), ActorDTO::getConcatStr)
                // 自定义字段别名
                .selectFunc("concat(%s, %s)", arg -> arg.accept(
                        Fun.f("t", Actor::getName), //t.name
                        Fun.f("t", Actor::getId)    //t.id
                ), ActorDTO::getConcatStr2);
        List<ActorDTO> dtos = actorMapper.selectJoinList(ActorDTO.class, objectMPJLambdaWrapper);
        dtos.forEach(System.out::println);
    }

    @Test
    public void condition(){

        MPJLambdaWrapper<Actor> actorMPJLambdaWrapper = new MPJLambdaWrapper<>();
        actorMPJLambdaWrapper.like(Actor::getName, "%a%")
                .le(Actor::getId, "1");
        List<ActorDTO> actorDTOS = actorMapper
                .selectJoinList(ActorDTO.class, actorMPJLambdaWrapper);
        actorDTOS.forEach(System.out::println);

    }

}
