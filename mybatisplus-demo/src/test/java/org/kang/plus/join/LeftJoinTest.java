package org.kang.plus.join;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.dto.FilmDTO;
import org.kang.plus.entity.Film;
import org.kang.plus.entity.FilmActor;
import org.kang.plus.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-04-19
 * Time: 12:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class LeftJoinTest {

    @Autowired
    private FilmMapper filmMapper;

    @Test
    public void leftjoin(){
        // SELECT t.id,t.name,t1.actor_id,t1.remark FROM film t LEFT JOIN film_actor t1 ON (t1.film_id = t.id)
        MPJLambdaWrapper<Film> wrapper = new MPJLambdaWrapper<Film>()
                .selectAll(Film.class)//查询Film表全部字段
                .select(FilmActor::getActorId, FilmActor::getRemark)
                 .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId);
        List<FilmDTO> userList = filmMapper.selectJoinList(FilmDTO.class, wrapper);
        userList.forEach(System.out::println);
    }

}
