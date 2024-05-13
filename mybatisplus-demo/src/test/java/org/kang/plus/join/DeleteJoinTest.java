package org.kang.plus.join;

import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.DeleteJoinWrapper;
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
 * Time: 17:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class DeleteJoinTest {

    @Autowired
    private FilmMapper filmMapper;

    /**
     * mysql里，这个有什么意义？
     */
    @Test
    public void deleteJoin(){
        // DELETE t FROM film t LEFT JOIN film_actor t1
        // ON (t1.film_id = t.id) WHERE (t.id = ?)
        DeleteJoinWrapper<Film> wrapper = JoinWrappers.delete(Film.class)
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId, 2);
        int i = filmMapper.deleteJoin(wrapper);
        System.out.println("delete count "+ i);
    }

    /**
     * 删除全部的表数据 (主表和副表)
     */
    @Test
    public void deleteAll(){
        // DELETE t,t1 FROM film t LEFT JOIN film_actor t1
        // ON (t1.film_id = t.id) WHERE (t.id = ?)
        DeleteJoinWrapper<Film> wrapper = JoinWrappers.delete(Film.class)
                //删除全部的表数据 (主表和副表)
                .deleteAll()
                //也可以删除指定的表数据,调用 delete() 传要删除的实体类class 如下
                //.delete(Film.class, FilmActor.class)
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId, 1);
        int i = filmMapper.deleteJoin(wrapper);
        System.out.println("delete count "+ i);
    }

}
