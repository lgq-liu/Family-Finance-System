package com.liu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.entity.Education;
import com.liu.mapper.EducationMapper;
import com.liu.service.EducationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName:EducationServiceImpl
 * Package:com.liu.service.impl
 * Description:
 *
 * @Author: 贤哥编程
 * @Create: 2023-9-14 - 15:18
 */
@Service
@Transactional
public class EducationServiceImpl extends ServiceImpl<EducationMapper, Education> implements EducationService {
}
