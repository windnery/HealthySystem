package com.dao;

import com.entity.StudentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.StudentView;

/**
 * 学生 Dao 接口
 *
 * @author 
 */
public interface StudentDao extends BaseMapper<StudentEntity> {

   List<StudentView> selectListView(Pagination page, @Param("params")Map<String,Object> params);

}
