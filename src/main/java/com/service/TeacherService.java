package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TeacherEntity;
import java.util.Map;

/**
 * 心理老师 服务类
 */
public interface TeacherService extends IService<TeacherEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}