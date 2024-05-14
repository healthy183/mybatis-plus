package org.kang.plus.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.entity.MbpDto;
import org.kang.plus.mapper.MbpDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-05-14
 * Time: 12:28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class DTOTest {

    @Autowired
    private MbpDtoMapper mbpDtoMapper;


    @Test
    public void insert(){
        MbpDto mbpDto = new MbpDto();
        mbpDto.setName("efg");
        mbpDto.setVersion(2);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("c","aval");
        stringStringHashMap.put("d","bval");
        mbpDto.setContact(stringStringHashMap);
        mbpDtoMapper.insert(mbpDto);
    }

    @Test
    public void selectList(){
        QueryWrapper queryWrapper = new QueryWrapper<Actor>();
        List<MbpDto> list = mbpDtoMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }

    @Test
    public void updateById(){
        MbpDto mbpDto = new MbpDto();
        mbpDto.setId(1);
        mbpDto.setName("plusUpdate");
        mbpDto.setVersion(1);
        int i = mbpDtoMapper.updateById(mbpDto);
        log.info("update count:"+i);
    }






}
