package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TeacherOrderEntity;
import java.util.Map;

/**
 * 心理咨询预约申请 服务类
 */
public interface TeacherOrderService extends IService<TeacherOrderEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}