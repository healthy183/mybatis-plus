package org.kang.plus.join;

import com.github.yulichang.query.MPJQueryWrapper;
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

/**
 * User:
 * Description:
 * Date: 2024-04-22
 * Time: 16:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class SelectJoinOneTest {

    @Autowired
    private FilmMapper filmMapper;

    /**
     * SELECT t.id,t.name,t1.actor_id FROM film t LEFT JOIN film_actor t1
     *  ON (t1.film_id = t.id) WHERE (t.id = ?)
     */
    @Test
    public void selectJoinOne(){
        MPJLambdaWrapper<Film> objectMPJLambdaWrapper = new MPJLambdaWrapper<Film>()
                .selectAll(Film.class)
                .select(FilmActor::getActorId)
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId,3);
        FilmDTO film = filmMapper.selectJoinOne(FilmDTO.class,objectMPJLambdaWrapper);
        System.out.println("result:"+film.toString());
    }

    /**
     * SELECT t.id,t.name,a.actor_id FROM film t
     *  LEFT JOIN film_actor a on t.id = a.actor_id WHERE (t.id = ?)
     */
    @Test
    public void selectJoinOneSQL(){
        MPJQueryWrapper<Film> objectMPJLambdaWrapper = new MPJQueryWrapper<Film>()
                .selectAll(Film.class)
                .select("a.actor_id")
                .leftJoin("film_actor a on t.id = a.film_id ")
                .eq("t.id",3);
        FilmDTO film = filmMapper.selectJoinOne(FilmDTO.class,objectMPJLambdaWrapper);
        System.out.println("result:"+film.toString());
    }

}
