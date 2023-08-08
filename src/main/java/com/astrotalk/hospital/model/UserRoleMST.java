package com.astrotalk.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_role_mst")
public class UserRoleMST extends BaseModel{

    @NotNull(message = "roleCode Required")
    @Column(name = "role_code")
    private String roleCode;

    @NotNull(message = "roleName Required")
    @Column(name = "role_name")
    private String roleName;

    @Column(name = "sort_order")
    private Integer sortOrder;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
