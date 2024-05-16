package org.kang.plus.logicDel;

import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.DeleteJoinWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.MbpSample;
import org.kang.plus.enums.IntEnums;
import org.kang.plus.enums.StringEnums;
import org.kang.plus.mapper.MbpSampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * User:
 * Description:
 * Date: 2024-05-13
 * Time: 17:01
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class LogicDelTest {

    @Autowired
    private MbpSampleMapper mbpSampleMapper;


    @Test
    public void insert(){
        MbpSample mbpSample = new  MbpSample();
        mbpSample.setName("plus");
        mbpSample.setEnumInt(IntEnums.ABLE);
        mbpSample.setEnumStr(StringEnums.ABLE);
        mbpSample.setUpdateTime(LocalDateTime.now());
        int insert = mbpSampleMapper.insert(mbpSample);
        log.info("insert count:"+insert);
    }


    @Test
    public void delete(){
        MbpSample mbpSample = new  MbpSample();
        mbpSample.setId(1);
        mbpSampleMapper.deleteById(mbpSample);
    }

    @Test
    @Deprecated
    public void disableLogicDel(){
        DeleteJoinWrapper<MbpSample> wrapper =
                JoinWrappers.delete(MbpSample.class)
                        .eq(MbpSample::getId, 1)
                        .disableLogicDel(); // JoinWrappers支持取消逻辑删除
        mbpSampleMapper.delete(wrapper);
    }

}
