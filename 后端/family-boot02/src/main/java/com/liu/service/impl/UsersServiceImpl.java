package com.liu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.entity.Users;
import com.liu.mapper.UsersMapper;
import com.liu.service.UsersService;
import com.liu.vo.UsersVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 刘国强
 */
@Service
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Override
    public Users familyEduById(Long uid) {
        return baseMapper.familyEduById(uid);
    }

    @Override
    public IPage<Users> listUsers(Page<Users> page, UsersVo usersVo) {
        return baseMapper.listUsers(page,usersVo);
    }
}
