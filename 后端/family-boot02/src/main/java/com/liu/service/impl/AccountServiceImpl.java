package com.liu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.entity.Account;
import com.liu.mapper.AccountMapper;
import com.liu.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 刘国强
 */
@Service
@Transactional
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService{
    @Override
    public List<Account> listWhere() {
        return baseMapper.listWhere();
    }

    @Override
    public Account getAccountById(Long aid) {
        return baseMapper.getAccountById(aid);
    }
}
