package com.qitai.springsecurity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "security_permission")
public class Permission {

    @Id
    @Column(name = "ID",length = 36)
    private String id;

    @Column(name = "URL")
    private String url;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "security_role_permission", joinColumns = {
            @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
