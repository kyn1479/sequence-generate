package com.kyn.sequencegeneratebydb.service;

import com.kyn.sequencegeneratebydb.enums.DecimalTypeEnum;
import com.kyn.sequencegeneratebydb.enums.SeqTypeEnum;

/**
 * @author yanan.kang
 * @description ：流水号生成服务接口
 * @date 2020-03-24 14:55
 */
public interface SequenceService {
    /**
     * 生成指定seqLen长度流水号
     * @param seqType
     * @param seqLen
     * @param decimalType
     * @return
     */
    String generate(SeqTypeEnum seqType, int seqLen, DecimalTypeEnum decimalType);

    /**
     *生成缺省24位流水号: 2位类型编码 + yyyyMMddhhmmss + 8位递增流水号
     * @param seqType
     * @return
     */
    String generate(SeqTypeEnum seqType) ;

}
