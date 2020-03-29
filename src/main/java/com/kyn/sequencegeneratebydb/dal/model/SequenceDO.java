package com.kyn.sequencegeneratebydb.dal.model;

import java.util.Date;

/**
 * @author yanan.kang
 * @description ：sequence 表对应的实体类
 * @date 2020-03-25 19:02
 */
public class SequenceDO extends BaseEntity{
    /**
     * 流水类型
     */
    private String seqType ;
    /**
     *流水日期
     */
    private String seqDay ;
    /**
     *流水值
     */
    private Long seqValue ;
    /**
     *备注
     */
    private String describes ;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *更新时间
     */
    private Date updateTime;

    public String getSeqType() {
        return seqType;
    }

    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }

    public String getSeqDay() {
        return seqDay;
    }

    public void setSeqDay(String seqDay) {
        this.seqDay = seqDay;
    }

    public long getSeqValue() {
        return seqValue;
    }

    public void setSeqValue(long seqValue) {
        this.seqValue = seqValue;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
