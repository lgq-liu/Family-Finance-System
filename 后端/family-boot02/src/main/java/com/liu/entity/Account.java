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
@TableName("t_account")
public class Account {

    @TableId(value = "aid", type = IdType.AUTO)
    private Long aid;
    private Long uid;
    private String accountDes;
    private BigDecimal accountMoney;
    private String remark;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    private Integer deleted;
    @TableField(exist = false)
    private Users users;
}
