package org.kang.plus.enums;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.MbpSample;
import org.kang.plus.mapper.MbpSampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-05-13
 * Time: 17:43
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class EnumTest {


    @Autowired
    private MbpSampleMapper mbpSampleMapper;

    @Test
    public void selectList(){
        QueryWrapper<MbpSample> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("id",1);
        List<MbpSample> list = mbpSampleMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }



}
