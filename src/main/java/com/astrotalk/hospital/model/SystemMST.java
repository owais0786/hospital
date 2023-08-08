package com.astrotalk.hospital.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system_mst")
public class SystemMST extends BaseModel{
    @Column(name = "system_code", unique = true)
    private String systemCode;

    @Column(name = "system_name")
    private String systemName;


    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }


}