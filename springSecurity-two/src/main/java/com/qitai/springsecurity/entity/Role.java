package com.qitai.springsecurity.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="security_role")
public class Role {

    @Id
    @Column(name = "ID",length = 36)
    private String id;

    @Column(name = "ROLE_CODE")
    private String role_code;

    @Column(name="ROLE_NAME")
    private String role_name;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "security_user_role", joinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    private Set<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "security_role_permission", joinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID")})
    private Set<Permission> permissions;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + id + ",  type=" + role_name	+ "]";
    }

}

