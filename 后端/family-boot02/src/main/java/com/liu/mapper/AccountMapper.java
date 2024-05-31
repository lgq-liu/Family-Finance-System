package com.liu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 刘国强
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    List<Account> listWhere();

    Account getAccountById(@Param("aid")Long aid);
}
