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
 * Date: 2024-05-07
 * Time: 18:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class SelectSubTest {
    @Autowired
    private FilmMapper filmMapper;

    @Test
    public void selectSub(){

            MPJLambdaWrapper<Film> objectMPJLambdaWrapper = new MPJLambdaWrapper<Film>()
                    .selectAll(Film.class)
                    .selectSub(Film.class,w->w.select(Film::getId)
                            .eq(Film::getId,Film::getId)
                            .last("limit 1"),Film::getId)
                    .leftJoin(FilmActor.class, FilmActor::getFilmId, Film::getId)
                    .eq(Film::getId,3);
            List<FilmDTO> filmDTOS =
                    filmMapper.selectJoinList(FilmDTO.class, objectMPJLambdaWrapper);
            //System.out.println("result:"+filmDTOS.toString());
        filmDTOS.forEach(System.out::println);

    }




}
