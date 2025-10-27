package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.TeacherMesDao;
import com.entity.TeacherMesEntity;
import com.service.TeacherMesService;
import com.entity.view.TeacherMesView;

/**
 * 心理老师留言 服务实现类
 */
@Service("TeacherMesService")
@Transactional
public class TeacherMesServiceImpl extends ServiceImpl<TeacherMesDao, TeacherMesEntity> implements TeacherMesService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<TeacherMesView> page =new Query<TeacherMesView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
