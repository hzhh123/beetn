package com.hd.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户-职位关联
 */
@Entity
@Table(name = "sys_user_position")
public class UserPosition implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userid;
    @Column(name = "position_id")
    private Long positionid;

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

    public Long getPositionid() {
        return positionid;
    }

    public void setPositionid(Long positionid) {
        this.positionid = positionid;
    }
}
