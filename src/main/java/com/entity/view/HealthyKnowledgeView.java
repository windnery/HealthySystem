package com.entity.view;

import com.entity.HealthyKnowledgeEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;

/**
 * 健康知识
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("HealthyKnowledge")
public class HealthyKnowledgeView extends HealthyKnowledgeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 健康知识类型的值
		*/
		private String HealthyKnowledgeValue;



	public HealthyKnowledgeView() {

	}

	public HealthyKnowledgeView(HealthyKnowledgeEntity HealthyKnowledgeEntity) {
		try {
			BeanUtils.copyProperties(this, HealthyKnowledgeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 健康知识类型的值
			*/
			public String getHealthyKnowledgeValue() {
				return HealthyKnowledgeValue;
			}
			/**
			* 设置： 健康知识类型的值
			*/
			public void setHealthyKnowledgeValue(String HealthyKnowledgeValue) {
				this.HealthyKnowledgeValue = HealthyKnowledgeValue;
			}
















}
