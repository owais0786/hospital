package com.astrotalk.hospital.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "user_mst")
public class UserMST extends BaseModel {
    @NotNull(message = "userName required")
    @Column(name = "user_name")
    private String userName;

    @NotNull(message = "password required")
    @Column(name = "password")
    private String password;

    @NotNull(message = "userRoleMSTId Required")
    @Column(name = "user_role_mst_id", insertable = false, updatable = false)
    private Long userRoleMSTId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_role_mst_id")
    private UserRoleMST userRoleMST;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
