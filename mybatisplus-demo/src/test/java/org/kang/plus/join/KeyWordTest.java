package org.kang.plus.join;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.dto.FilmDTO;
import org.kang.plus.entity.Actor;
import org.kang.plus.entity.Film;
import org.kang.plus.mapper.ActorMapper;
import org.kang.plus.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-04-22
 * Time: 18:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class KeyWordTest {

    @Autowired
    private ActorMapper actorMapper;


    @Autowired
    private FilmMapper filmMapper;

    @Test
    public void distinct(){

        MPJLambdaWrapper<Actor> objectMPJLambdaWrapper = new MPJLambdaWrapper< Actor>()
                .distinct()
                .select(Actor::getName);
        List<Actor> actors = actorMapper.selectList(objectMPJLambdaWrapper);
        actors.forEach(System.out::println);
    }


    /**
     * 不会用(官网demo都没有这个函数)
     */
    @Deprecated
    @Test
    public void selectAsClass(){
        MPJLambdaWrapper<Film> objectMPJLambdaWrapper = new MPJLambdaWrapper<Film>()
                //.selectAsClass(Film.class,FilmDTO.class)
               .select(Film::getId,Film::getName);
        List<Film> films = filmMapper.selectList(objectMPJLambdaWrapper);
        /**
         * 还不如这么写
         * List<FilmDTO> films = filmMapper.selectJoinList(FilmDTO.class,objectMPJLambdaWrapper);
         * */
        films.forEach(System.out::println);
    }


    /**
     * 有什么意义？
     */
    @Deprecated
    @Test
    public void selectAs(){
        MPJLambdaWrapper<Film> objectMPJLambdaWrapper = new MPJLambdaWrapper<Film>()
                .selectAs(Film::getId, FilmDTO::getId);
        List<Film> films = filmMapper.selectList(objectMPJLambdaWrapper);
        films.forEach(System.out::println);
    }


}
