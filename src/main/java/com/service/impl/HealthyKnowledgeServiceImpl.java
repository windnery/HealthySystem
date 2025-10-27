package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.HealthyKnowledgeDao;
import com.entity.HealthyKnowledgeEntity;
import com.service.HealthyKnowledgeService;
import com.entity.view.HealthyKnowledgeView;

/**
 * 健康知识 服务实现类
 */
@Service("HealthyKnowledgeService")
@Transactional
public class HealthyKnowledgeServiceImpl extends ServiceImpl<HealthyKnowledgeDao, HealthyKnowledgeEntity> implements HealthyKnowledgeService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<HealthyKnowledgeView> page =new Query<HealthyKnowledgeView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
