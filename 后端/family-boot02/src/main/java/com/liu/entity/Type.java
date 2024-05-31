package com.liu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 刘国强
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_type")
public class Type {
    @TableId(value = "tid",type= IdType.AUTO)
    private Long tid;
    private String  typeCode;
    private String typeName;
    private String  remark;
    private Date createTime;
    private Date  updateTime;
    @TableLogic
    private Integer deleted;
}
