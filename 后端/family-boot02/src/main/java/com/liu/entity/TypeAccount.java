package com.liu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 刘国强
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_type_account")
public class TypeAccount {
    @TableId(value = "ttid", type = IdType.AUTO)
    private Long ttid;
    private Long aid;
    private String accountDescription;
    private String accountCode;
    private String accountName;
    private BigDecimal accrualMoney;
    private BigDecimal balanceMoney;
    private String divertName;
    private Date divertTime;
    private Long dvtId;
    private Long outId;
    private String name;
    private Long tid;
    private String remark;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    private Integer deleted;
    private Integer classType;
    @TableField(exist = false)
    private Integer type;
}
