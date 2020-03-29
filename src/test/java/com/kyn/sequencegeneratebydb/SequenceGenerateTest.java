package com.kyn.sequencegeneratebydb;

import com.kyn.sequencegeneratebydb.enums.SeqTypeEnum;
import com.kyn.sequencegeneratebydb.service.SequenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yanan.kang
 * @description ：序列号生成测试类
 * @date 2020-03-28 10:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SequenceGenerateTest {
    public final static Logger logger = LoggerFactory.getLogger(SequenceGenerateTest.class);
    @Autowired
    private SequenceService sequenceService;

    @Test
    public void sequenceServiceTest(){
        String sequence=sequenceService.generate(SeqTypeEnum.SEQ_DEDUCT);
        logger.info("生成序列号sequence:{}",sequence);
    }
}
