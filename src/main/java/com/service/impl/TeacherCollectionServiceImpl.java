package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.TeacherCollectionDao;
import com.entity.TeacherCollectionEntity;
import com.service.TeacherCollectionService;
import com.entity.view.TeacherCollectionView;

/**
 * 心理老师收藏 服务实现类
 */
@Service("TeacherCollectionService")
@Transactional
public class TeacherCollectionServiceImpl extends ServiceImpl<TeacherCollectionDao, TeacherCollectionEntity> implements TeacherCollectionService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<TeacherCollectionView> page =new Query<TeacherCollectionView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
