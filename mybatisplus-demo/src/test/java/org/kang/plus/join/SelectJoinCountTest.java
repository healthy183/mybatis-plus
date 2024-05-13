package org.kang.plus.join;

import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
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
 * Time: 16:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class SelectJoinCountTest {

    @Autowired
    private FilmMapper filmMapper;

    /**
     *  SELECT COUNT( * ) FROM film t LEFT JOIN film_actor t1
     *  ON (t1.film_id = t.id) WHERE (t.id = ?)
     */
    @Test
    public void selectJoinCount(){
        MPJLambdaWrapper<Film> objectMPJLambdaWrapper = new MPJLambdaWrapper<Film>()
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId,3);
        Long count = filmMapper.selectJoinCount(objectMPJLambdaWrapper);
        System.out.println("result:"+count.toString());

    }


    /**
     * SELECT COUNT( t.id ) FROM film t LEFT JOIN film_actor t1
     * ON (t1.film_id = t.id) WHERE (t.id = ?)
     */
    @Test
    public void CountID(){
        MPJLambdaWrapper<Film> objectMPJLambdaWrapper = new MPJLambdaWrapper<Film>()
                .select(Film::getId)
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId,3);
        Long count = filmMapper.selectJoinCount(objectMPJLambdaWrapper);
        System.out.println("result:"+count.toString());
    }


    /**
     * 这个不好用
     * ELECT COUNT( * ) FROM film t LEFT JOIN film_actor fa
     *  on fa.film_id = t.id WHERE (t.id = ?)
     */
    @Test
    public void selectJoinCountSql(){
        MPJQueryWrapper<Film> objectMPJLambdaWrapper = new MPJQueryWrapper<Film>()
                .leftJoin("film_actor fa on fa.film_id = t.id")
                .eq("t.id",3);
        Long count = filmMapper.selectJoinCount(objectMPJLambdaWrapper);
        System.out.println("result:"+count.toString());
    }

    /**
     * SELECT COUNT( t.id ) FROM film t LEFT JOIN film_actor fa on fa.film_id = t.id WHERE (t.id = ?)
     */
    @Test
    public void selectJoinCountSqlID(){
        MPJQueryWrapper<Film> objectMPJLambdaWrapper = new MPJQueryWrapper<Film>()
                .select("t.id")
                .leftJoin("film_actor fa on fa.film_id = t.id")
                .eq("t.id",1);
        Long count = filmMapper.selectJoinCount(objectMPJLambdaWrapper);
        System.out.println("result:"+count.toString());
    }
}
