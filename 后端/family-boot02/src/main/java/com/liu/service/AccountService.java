package com.liu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.entity.Account;

import java.util.List;

/**
 * @author 刘国强
 */
public interface AccountService extends IService<Account> {
    List<Account> listWhere();

    Account getAccountById(Long aid);
}
