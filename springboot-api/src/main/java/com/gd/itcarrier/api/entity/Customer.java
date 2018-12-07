package com.gd.itcarrier.api.entity;

import java.io.Serializable;

/**
 * 实体类
 * @author yuzg
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
    private Long id;
    private Long orgId;
    private String custName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
