package com.kyn.sequencegeneratebydb.service.impl;

import com.kyn.sequencegeneratebydb.dal.mapper.SequenceMapper;
import com.kyn.sequencegeneratebydb.enums.DecimalTypeEnum;
import com.kyn.sequencegeneratebydb.enums.SeqTypeEnum;
import com.kyn.sequencegeneratebydb.enums.SystemErrorCode;
import com.kyn.sequencegeneratebydb.exception.PayException;
import com.kyn.sequencegeneratebydb.handler.SequenceHandler;
import com.kyn.sequencegeneratebydb.service.SequenceService;
import com.kyn.sequencegeneratebydb.utils.AssertUtils;
import com.kyn.sequencegeneratebydb.utils.HexConvertorUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.kyn.sequencegeneratebydb.utils.DateUtil.getTime14;

/**
 * @author yanan.kang
 * @description ：
 * @date 2020-03-24 15:57
 */
@Service
public class SequenceServiceImpl implements SequenceService , InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(SequenceHandler.class);
    private Map<String, SequenceHandler> seqHandlerMap = new ConcurrentHashMap<>();

    /**
     * 流水数据库操作Mapper
     */
    @Autowired
    private SequenceMapper sequenceMapper;
    @Override
    public String generate(SeqTypeEnum seqType, int seqLen, DecimalTypeEnum decimalType) {
        AssertUtils.isNotNull(seqType, SystemErrorCode.BIZ_PARAM_NULL);
        AssertUtils.isTrue(seqLen > 1, SystemErrorCode.BIZ_PARAM_NULL);
        String seq = generateSeq(seqType, seqLen, decimalType);
        if (StringUtils.isBlank(seq) || seq.length() > seqLen) {
            logger.error("流水({})生成错误.", seq);
            throw new PayException(SystemErrorCode.SEQUENCE_GEN_ERR);
        }
        return seq;
    }

    @Override
    public String generate(SeqTypeEnum seqType) {
        logger.info("开始生成流水号");
        AssertUtils.isNotNull(seqType, SystemErrorCode.BIZ_PARAM_NULL);
        String seq = seqType.getCode() + getTime14() + generate(seqType, 8, DecimalTypeEnum.DECIMAL);
        logger.info("结束生成流水号:{}", seq);
        return seq;
    }
    /**
     * @Description 流水号生成
     * @Params
     * @Return
     * @Exceptions
     */
    private String generateSeq(SeqTypeEnum seqType, int seqLen, DecimalTypeEnum decimalType) {
        long decimal = -1L;
        SequenceHandler handler = seqHandlerMap.get(seqType.code());
        AssertUtils.isNotNull(handler, SystemErrorCode.SYSTEM_ERROR);
        decimal = handler.getSeq();
        String hexStr = HexConvertorUtil.transform(decimal, Integer.valueOf(decimalType.getCode()));
        return StringUtils.leftPad(hexStr, seqLen, "0");
    }

    /**
     * 初始化流水号生成器
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        for (SeqTypeEnum type : SeqTypeEnum.values()) {
            SequenceHandler handler = new SequenceHandler(sequenceMapper, type);
            seqHandlerMap.put(type.code(), handler);
        }
    }

}
