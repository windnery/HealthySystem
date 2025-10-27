package com.dao;

import com.entity.HealthyKnowledgeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HealthyKnowledgeView;

/**
 * 健康知识 Dao 接口
 *
 * @author 
 */
public interface HealthyKnowledgeDao extends BaseMapper<HealthyKnowledgeEntity> {

   List<HealthyKnowledgeView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
