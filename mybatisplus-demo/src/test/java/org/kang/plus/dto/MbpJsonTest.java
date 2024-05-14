package org.kang.plus.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Contact;
import org.kang.plus.entity.MbpJson;
import org.kang.plus.mapper.MbpJsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * User:
 * Description:
 * Date: 2024-05-14
 * Time: 17:03
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class MbpJsonTest {

    @Autowired
    private MbpJsonMapper mbpJsonMapper;

    @Test
    public void insert(){
        MbpJson mbpJson = new MbpJson();
        mbpJson.setName("efg");
        Contact contact = new Contact();
        contact.setContactID(1);
        contact.setContactName("contactName");
        mbpJson.setContact(contact);
        mbpJsonMapper.insert(mbpJson);
    }

    @Test
    public void selectList(){
        QueryWrapper queryWrapper = new QueryWrapper<MbpJson>();
        List<MbpJson> list = mbpJsonMapper.selectList(queryWrapper);
        list.forEach(actorT -> {
            log.info("select:"+actorT);
        });
    }
}
