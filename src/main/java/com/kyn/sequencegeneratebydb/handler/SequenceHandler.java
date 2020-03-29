package com.kyn.sequencegeneratebydb.handler;

import com.kyn.sequencegeneratebydb.dal.mapper.SequenceMapper;
import com.kyn.sequencegeneratebydb.dal.model.SequenceDO;
import com.kyn.sequencegeneratebydb.enums.SeqTypeEnum;
import com.kyn.sequencegeneratebydb.enums.SystemErrorCode;
import com.kyn.sequencegeneratebydb.exception.PayException;
import com.kyn.sequencegeneratebydb.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yanan.kang
 * @description ：SequenceHandler
 * @date 2020-03-24 18:35
 */
public class SequenceHandler {
    private static final Logger logger = LoggerFactory.getLogger(SequenceHandler.class);
    /**
     * 从数据库获取的当前值
     */
    private  long dbCount = -1;
    /**
     * 当前步长内计数值
     */
    private  long stepCount = 0;
    /**
     * 步长最大值,默认值
     */
    private  long stepMax = 10000;
    /**
     * 流水号类型
     */
    private SeqTypeEnum type;
    /**
     * 当前日期
     */
    private  String currentDate;
    /**
     * 流水数据库操作Mapper
     */
    @Autowired
    private SequenceMapper sequenceMapper;

    /**
     * 构造方法
     * @param sequenceMapper
     * @param type
     */
    public SequenceHandler(SequenceMapper sequenceMapper, SeqTypeEnum type) {
        this.sequenceMapper = sequenceMapper;
        this.type = type;
        this.stepMax = type.getStep();
        init();
    }
    /**
     * 初始化
     */
    private void init() {
        currentDate=DateUtil.getDate8();
        try {
            SequenceDO sequence = sequenceMapper.selectByDay(type.code(), currentDate);
            if (null == sequence) {
                boolean firstInsert = firstInsert();
                if (!firstInsert) {
                    this.dbCount = getNewDbCount();
                } else {
                    this.dbCount = 0;
                }
            } else {
                this.dbCount = getNewDbCount();
            }
            //重置步长
            if (dbCount != -1){
                resetStepCount();
            }
        } catch (Exception e) {
            logger.error("流水号初始化异常e:{}，参数type：{}", e,type.getCode());
        }
    }

    /**
     * 重置步长
     */
    private void resetStepCount() {
        stepCount = 0;
    }

    /**
     * 当日首次流水号初始化
     * @return
     */
    private boolean firstInsert() {
        try {
            sequenceMapper.insertValue(type.code(),type.getMessage(),currentDate, 0L,1L);
            return true;
        } catch (Exception ex) {
            logger.error("流水号初始化异常,", ex);
        }
        return false;
    }

    /**
     *  更新数据库获取流水号
     * @return
     */
    private long getNewDbCount() {
        for (int i = 0; i < 10; i++) {
            try {
                SequenceDO sequence = sequenceMapper.selectByDay(type.code(), currentDate);
                logger.info("getSeqValue的值为{}",sequence==null?"null":sequence.getSeqValue());
                if (sequence != null) {
                    int result = atomicUpdate(sequence.getSeqValue());
                    logger.info("result的值为{}",result);
                    if (result != 0) {
                        return sequence.getSeqValue() + 1;
                    }
                }
            } catch (Exception e) {
                logger.error("生成流水号获取DbCount异常:{}", e);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                logger.error("{}",e);

            }
        }
        logger.error("尝试更新SeqValue的值10次后还是失败");
        return -1;
    }

    /**
     * 更新数据库
     * @param dbcount
     * @return
     */
    private int atomicUpdate(long dbcount) {
        try {
            logger.info("Sequence更新数据库seqType({}),seqDay({}),oldSeqValue({}),newSeqValue({})",type.getCode(), currentDate, dbcount, dbcount + 1);
            return sequenceMapper.updateValByDay(type.getCode(), currentDate, dbcount, dbcount + 1);
        } catch (Exception ex) {
            logger.error("atomicUpdate更新数据库异常，参数dbcount：{},Exception:{}", dbcount, ex);
        }
        return 0;
    }

    /**
     * 获取序列
     * @return
     */
    public synchronized long getSeq() {
        //1、如果系统启动时初始化失败或者切日；
        if (dbCount == -1 || isDateChange()) {
            init();
        }
        //2、如果仍然初始化失败则抛出异常；
        if (dbCount == -1) {
            logger.error("生成流水号失败:{}", type.getCode());
            throw new PayException(SystemErrorCode.SYSTEM_ERROR);
        }
        //3、判断是否达到最大步长，重新reset;
        if (isMax()) {
            dbCount = getNewDbCount();
            if (dbCount != -1) {
                resetStepCount();
            } else {
                logger.error("生成流水号失败:{} ", type.getCode());
                throw new PayException(SystemErrorCode.SYSTEM_ERROR);
            }
        }
        //4、生成流水号；
        long total = dbCount * stepMax + stepCount;
        stepCount++;
        return total;
    }

    /**
     * 判断是否时间切换日
     * @return
     */
    private boolean isDateChange() {
        return !StringUtils.equalsIgnoreCase(currentDate, DateUtil.getDate8());
    }

    /**
     * 判断是否达到最大
     * @return
     */
    private boolean isMax() {
        if (stepCount >= stepMax) {
            return true;
        } else {
            return false;
        }
    }

    public SeqTypeEnum getType() {
        return this.type;
    }
}
