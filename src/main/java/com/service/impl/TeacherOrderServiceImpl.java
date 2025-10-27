package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.TeacherOrderDao;
import com.entity.TeacherOrderEntity;
import com.service.TeacherOrderService;
import com.entity.view.TeacherOrderView;

/**
 * 心理咨询预约申请 服务实现类
 */
@Service("TeacherOrderService")
@Transactional
public class TeacherOrderServiceImpl extends ServiceImpl<TeacherOrderDao, TeacherOrderEntity> implements TeacherOrderService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<TeacherOrderView> page =new Query<TeacherOrderView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
