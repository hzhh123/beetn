package com.hd.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 休假
 * @Author hzhh123
 */
@Entity
@Table(name = "t_vacation")
// 在Hibernate中可以利用@DynamicInsert和@DynamicUpdate生成动态SQL语句，
//即在插入和修改数据的时候,语句中只包括要插入或者修改的字段。
@DynamicUpdate(true)
@DynamicInsert(true)
public class Vacation extends BaseVO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1495795296316800235L;
    // 带薪假
    public final static int TYPE_PAID = 0;
    // 病假
    public final static int TYPE_SICK = 1;
    // 事假
    public final static int TYPE_MATTER = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    // 休假的工作日
//	@NotNull
//  @NotEmpty(message = "This cannot be null")
    @Column(name = "work_days")
    private Integer days;

    // 请假开始日期
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "begin_date")
    private Date beginDate;

    // 请假结束日期
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private Date endDate;

    // 申请日期
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @Column(name = "apply_date")
    private Date applyDate;

    // 休假类型
    @Column(name = "vac_type")
    private Integer vacationType;
    @Transient
    private String applytime;

    //原因
    @Column(name = "reason")
    private String reason;

    // 对应的流程实例id
    @Column(name = "proc_inst_id")
    private String processInstanceId;

    // 用户id
    @Column(name = "user_id")
    private Long userId;
    @Transient
    private String userName;//用户姓名

    //审批状态
    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getVacationType() {
        return vacationType;
    }

    public void setVacationType(Integer vacationType) {
        this.vacationType = vacationType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }
}
