package com.dao;

import com.entity.InfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.InfoView;

/**
 * 通知 Dao 接口
 *
 * @author 
 */
public interface InfoDao extends BaseMapper<InfoEntity> {

   List<InfoView> selectListView(Pagination page, @Param("params")Map<String,Object> params);

}
