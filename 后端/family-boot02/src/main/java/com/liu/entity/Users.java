package com.liu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.events.EntityDeclaration;
import java.util.Date;

/**
 * @author 刘国强
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_users")
public class Users {
    @TableId(value = "uid",type= IdType.AUTO)
    private Long uid;
    private String  username;
    private String  password;
    private String  name;
    private Integer sex;
    private String occupation;

    private Long eid;
    private String  description;
    private Long fid;
    private String remark;
    private Date createTime;
    private Date  updateTime;
    // 使用假删除

    @TableLogic
    private Integer deleted;
    private Integer isType;

    /**
     * 表示不是数据库当前表中存在的字段
     */
    @TableField(exist = false)
    private Family family;
    @TableField(exist = false)
    private Education education;
}
