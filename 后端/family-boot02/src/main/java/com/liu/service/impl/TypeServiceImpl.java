package com.liu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.entity.Type;
import com.liu.mapper.TypeMapper;
import com.liu.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 刘国强
 */
@Service
@Transactional
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
}
