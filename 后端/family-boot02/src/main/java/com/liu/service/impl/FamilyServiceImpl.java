package com.liu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.entity.Family;
import com.liu.mapper.FamilyMapper;
import com.liu.service.FamilyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 刘国强
 */
@Service
@Transactional
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {
}
