package com.dao;

import com.entity.TeacherMesEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TeacherMesView;

/**
 * 心理老师留言 Dao 接口
 *
 * @author 
 */
public interface TeacherMesDao extends BaseMapper<TeacherMesEntity> {

   List<TeacherMesView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
