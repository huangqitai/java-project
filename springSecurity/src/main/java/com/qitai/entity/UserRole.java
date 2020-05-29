package com.qitai.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="security_role")
public class UserRole {

    @Id
    @Column(name = "ID",length = 36)
    private int id;

    @Column(name = "ROLE_CODE")
    private String role_code;

    @Column(name="ROLE_NAME")
    private String role_name = UserRoleType.USER.getUserRoleType();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((role_name == null) ? 0 : role_name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserRole))
            return false;
        UserRole other = (UserRole) obj;
        if (id != other.id)
            return false;
        if (role_name == null) {
            if (other.role_name != null)
                return false;
        } else if (!role_name.equals(other.role_name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + id + ",  type=" + role_name	+ "]";
    }


}
