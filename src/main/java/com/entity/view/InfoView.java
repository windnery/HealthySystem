package com.entity.view;

import com.entity.InfoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;

/**
 * 通知
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("Info")
public class InfoView extends InfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 通知类型的值
		*/
		private String InfoValue;



	public InfoView() {

	}

	public InfoView(InfoEntity infoEntity) {
		try {
			BeanUtils.copyProperties(this, infoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 通知类型的值
			*/
			public String getInfoValue() {
				return InfoValue;
			}
			/**
			* 设置： 通知类型的值
			*/
			public void setInfoValue(String InfoValue) {
				this.InfoValue = InfoValue;
			}
















}
