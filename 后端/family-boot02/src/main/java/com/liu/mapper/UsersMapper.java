package com.liu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.entity.Users;
import com.liu.vo.UsersVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author 刘国强
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    Users familyEduById(Long uid);

    IPage<Users> listUsers(Page<Users> page, @Param("usersVo")UsersVo usersVo);
}
