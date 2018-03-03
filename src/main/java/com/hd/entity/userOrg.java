package com.hd.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户-组织关联表
 */
@Entity
@Table(name = "sys_user_org")
public class userOrg implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userid;
    @Column(name = "org_id")
    private Long orgid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }
}
