package com.dao;

import com.entity.TeacherCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TeacherCollectionView;

/**
 * 心理老师收藏 Dao 接口
 *
 * @author 
 */
public interface TeacherCollectionDao extends BaseMapper<TeacherCollectionEntity> {

   List<TeacherCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
