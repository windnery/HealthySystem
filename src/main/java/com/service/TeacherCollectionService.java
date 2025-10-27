package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TeacherCollectionEntity;
import java.util.Map;

/**
 * 心理老师收藏 服务类
 */
public interface TeacherCollectionService extends IService<TeacherCollectionEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}