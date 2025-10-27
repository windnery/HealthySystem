package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TeacherMesEntity;
import java.util.Map;

/**
 * 心理老师留言 服务类
 */
public interface TeacherMesService extends IService<TeacherMesEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}