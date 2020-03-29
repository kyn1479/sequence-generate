package com.kyn.sequencegeneratebydb.dal.model;

import java.io.Serializable;

/**
 * @author yanan.kang
 * @description ：基础类
 * @date 2020-03-25 19:22
 */

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5971730583574017341L;
    /**
     * 主键Id
     */
    protected Long id;

    /**
     * 版本
     */
    private Long version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
