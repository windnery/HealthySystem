
package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.AdminEntity;

/**
 * 学生
 */
public interface AdminDao extends BaseMapper<AdminEntity> {
	
	List<AdminEntity> selectListView(@Param("ew") Wrapper<AdminEntity> wrapper);

	List<AdminEntity> selectListView(Pagination page, @Param("ew") Wrapper<AdminEntity> wrapper);
	
}
