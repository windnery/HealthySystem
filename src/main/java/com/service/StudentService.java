package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.StudentEntity;
import java.util.Map;

/**
 * 学生 服务类
 */
public interface StudentService extends IService<StudentEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}