package com.zben.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zben
 * @Description:产品
 * @Date: 17:13 2019/6/11
 */
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = -1035006087178668265L;
    @Id
    private String id;

    private String name;

    //起投金额
    private BigDecimal thresholdAmount;

    //投资步长
    private BigDecimal stepAmount;

    //锁定期
    private Integer lockTerm;

    //收益率 0-100百分比
    private BigDecimal rewardRate;

    //状态，AUDINING:审核中，IN_SELL：销售中，LOCKED：暂停销售，FINISHED：已结束
    @ApiModelProperty(value = "状态", dataType = "com.zben.enums.ProductStatus")
    private String status;

    //备注
    private String memo;

    private String createUser;

    private Date createAt;

    private String updateUser;

    private Date updateAt;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", thresholdAmount=" + thresholdAmount +
                ", stepAmount=" + stepAmount +
                ", lockTerm=" + lockTerm +
                ", rewardRate=" + rewardRate +
                ", status='" + status + '\'' +
                ", memo='" + memo + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createAt=" + createAt +
                ", updateUser='" + updateUser + '\'' +
                ", updateAt=" + updateAt +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public BigDecimal getStepAmount() {
        return stepAmount;
    }

    public void setStepAmount(BigDecimal stepAmount) {
        this.stepAmount = stepAmount;
    }

    public Integer getLockTerm() {
        return lockTerm;
    }

    public void setLockTerm(Integer lockTerm) {
        this.lockTerm = lockTerm;
    }

    public BigDecimal getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(BigDecimal rewardRate) {
        this.rewardRate = rewardRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
