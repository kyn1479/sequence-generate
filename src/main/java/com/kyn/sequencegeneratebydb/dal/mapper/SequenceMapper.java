package com.kyn.sequencegeneratebydb.dal.mapper;

import com.kyn.sequencegeneratebydb.dal.model.SequenceDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yanan.kang
 * @description ：流水生成Mapper类
 * @date 2020-03-25 19:25
 */
@Repository
public interface SequenceMapper {
    /**
     * 根据日期获取
     * @param seqType
     * @param seqDay
     * @return
     */
    SequenceDO selectByDay(@Param("seqType")String seqType, @Param("seqDay")String seqDay);

    /**
     * 根据日期更新
     * @param seqType
     * @param seqDay
     * @param oldSeqValue
     * @param newSeqValue
     * @return
     */
    int updateValByDay(@Param("seqType")String seqType, @Param("seqDay")String seqDay, @Param("oldSeqValue")long oldSeqValue, @Param("newSeqValue")long newSeqValue);

    /**
     * 插入数据
     * @param seqType
     * @param seqDay
     * @param seqValue
     * @return
     */
    int insertValue(@Param("seqType")String seqType,@Param("describes")String describes , @Param("seqDay")String seqDay, @Param("seqValue")long seqValue,@Param("version")long version);
}
