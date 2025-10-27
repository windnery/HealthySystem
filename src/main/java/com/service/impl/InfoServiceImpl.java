package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.InfoDao;
import com.entity.InfoEntity;
import com.service.InfoService;
import com.entity.view.InfoView;

/**
 * 通知 服务实现类
 */
@Service("InfoService")
@Transactional
public class InfoServiceImpl extends ServiceImpl<InfoDao, InfoEntity> implements InfoService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<InfoView> page =new Query<InfoView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
