package org.kang.plus.join;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * Time: 17:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class SelectJoinPageTest {

    @Autowired
    private FilmMapper filmMapper;

    @Test
    public void selectJoinPage(){

        MPJLambdaWrapper<Film> eq = new MPJLambdaWrapper<Film>()
                .selectAll(Film.class)
                .select(FilmActor::getActorId)
                .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                .eq(Film::getId, 3);

        IPage<FilmDTO> page = filmMapper.selectJoinPage
                (new Page<>(1, 2), FilmDTO.class,eq);
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void selectJoinPageSQL(){

        MPJQueryWrapper<Film> eq = new MPJQueryWrapper<Film>()
                .selectAll(Film.class)
                .select("a.actor_id")
                .leftJoin("film_actor a on t.id = a.film_id ")
                .eq("t.id",3);;

        IPage<FilmDTO> page = filmMapper.selectJoinPage
                (new Page<>(1, 2), FilmDTO.class,eq);
        page.getRecords().forEach(System.out::println);
    }



}
