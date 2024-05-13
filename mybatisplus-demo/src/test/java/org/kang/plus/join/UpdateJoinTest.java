package org.kang.plus.join;

import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.UpdateJoinWrapper;
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
 * Date: 2024-04-19
 * Time: 17:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class UpdateJoinTest {

    @Autowired
    private FilmMapper filmMapper;

    /**
     * JoinWrappers中设置更新内容
     */
    @Test
    public void updateJoin(){
        /**
         * UPDATE film t LEFT JOIN film_actor t1
         *         ON (t1.film_id = t.id) SET t.name=?,t1.remark=? WHERE (t.id = ?)
         * */
        UpdateJoinWrapper<Film> wrapper = JoinWrappers.update(Film.class)
                .set(Film::getName,"乱世佳人")
                .set(FilmActor::getRemark,"乱世佳人remark")
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId, 3);
        int i = filmMapper.updateJoin(null,wrapper);
        System.out.println("updateJoin count "+ i);
    }

    /**
     * dto中设置更新内容
     */
    @Test
    public void updateJoinDTO(){
        // UPDATE film t LEFT JOIN film_actor t1
        // ON (t1.film_id = t.id) SET t.name=? WHERE (t.id = ?)
        Film film = new Film();
        film.setName("教父");
        UpdateJoinWrapper<Film> wrapper = JoinWrappers.update(Film.class)
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId, 3);
        int i = filmMapper.updateJoin(film,wrapper);
        System.out.println("updateJoin count "+ i);
    }

    /**
     * JoinWrappers与dto中设置更新内容
     */
    @Test
    public void updateJoinDTO2(){
        //  UPDATE film t LEFT JOIN film_actor t1
        //  ON (t1.film_id = t.id) SET t.name=?, t1.remark=? WHERE (t.id = ?)
        Film film = new Film();
        film.setName("飞越疯人院2");
        UpdateJoinWrapper<Film> wrapper = JoinWrappers.update(Film.class)
                .set(FilmActor::getRemark,"飞越疯人院2")
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId, 3);
        int i = filmMapper.updateJoin(film,wrapper);
        System.out.println("updateJoin count "+ i);
    }

    /**
     * JoinWrappers与dto中设置更新内容
     */
    @Test
    public void updateJoinDTO3(){
        //   UPDATE film t LEFT JOIN film_actor t1
        //   ON (t1.film_id = t.id) SET t.name=?, t1.remark=? WHERE (t.id = ?)
        Film film = new Film();
        film.setName("摩登时代");

        FilmActor filmActor = new FilmActor();
        filmActor.setRemark("摩登时代Remark");

        UpdateJoinWrapper<Film> wrapper = JoinWrappers.update(Film.class)
                .setUpdateEntity(filmActor)
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId, 3);
        int i = filmMapper.updateJoin(film,wrapper);
        System.out.println("updateJoin count "+ i);
    }

}
