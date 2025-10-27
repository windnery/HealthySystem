package com.dao;

import com.entity.TeacherOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TeacherOrderView;

/**
 * 心理咨询预约申请 Dao 接口
 *
 * @author 
 */
public interface TeacherOrderDao extends BaseMapper<TeacherOrderEntity> {

   List<TeacherOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
