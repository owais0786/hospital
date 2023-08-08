package com.astrotalk.hospital.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "user_authorization_mst")
public class UserAuthorisationMST extends BaseModel {
    @NotNull(message = "systemMSTId Required")
    @Column(name = "system_mst_id", insertable = false, updatable = false)
    private Long systemMSTId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "system_mst_id")
    private SystemMST systemMST;

    @NotNull(message = "userRoleMSTId Required")
    @Column(name = "user_role_mst_id", insertable = false, updatable = false)
    private Long userRoleMSTId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role_mst_id")
    private UserRoleMST userRoleMST;

    public Long getSystemMSTId() {
        return systemMSTId;
    }

    public void setSystemMSTId(Long systemMSTId) {
        this.systemMSTId = systemMSTId;
    }

    public SystemMST getSystemMST() {
        return systemMST;
    }

    public void setSystemMST(SystemMST systemMST) {
        this.systemMST = systemMST;
    }

    public Long getUserRoleMSTId() {
        return userRoleMSTId;
    }

    public void setUserRoleMSTId(Long userRoleMSTId) {
        this.userRoleMSTId = userRoleMSTId;
    }

    public UserRoleMST getUserRoleMST() {
        return userRoleMST;
    }

    public void setUserRoleMST(UserRoleMST userRoleMST) {
        this.userRoleMST = userRoleMST;
    }
}