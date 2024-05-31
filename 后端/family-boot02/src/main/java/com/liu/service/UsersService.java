package com.liu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.entity.Users;
import com.liu.vo.UsersVo;


/**
 * @author 刘国强
 */
public interface UsersService extends IService<Users> {
    Users familyEduById(Long uid);

    IPage<Users> listUsers(Page<Users> page, UsersVo usersVo);
}
