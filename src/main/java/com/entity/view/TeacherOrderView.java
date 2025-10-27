package com.entity.view;

import com.entity.TeacherOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;

/**
 * 心理咨询预约申请
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("Teacher_order")
public class TeacherOrderView extends TeacherOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 预约时间段的值
		*/
		private String shijianduanValue;
		/**
		* 预约状态的值
		*/
		private String TeacherOrderYesnoValue;



		//级联表 yonghu
			/**
			* 学生姓名
			*/
			private String yonghuName;
			/**
			* 学生手机号
			*/
			private String yonghuPhone;
			/**
			* 学生身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 学生头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;

		//级联表 Teacher
			/**
			* 心理老师姓名
			*/
			private String TeacherName;
			/**
			* 心理老师手机号
			*/
			private String TeacherPhone;
			/**
			* 心理老师头像
			*/
			private String TeacherPhoto;
			/**
			* 电子邮箱
			*/
			private String TeacherEmail;
			/**
			* 擅长
			*/
			private String TeacherShanchang;
			/**
			* 履历
			*/
			private String TeacherLvli;
			/**
			* 心理老师详细介绍
			*/
			private String TeacherContent;

	public TeacherOrderView() {

	}

	public TeacherOrderView(TeacherOrderEntity TeacherOrderEntity) {
		try {
			BeanUtils.copyProperties(this, TeacherOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 预约时间段的值
			*/
			public String getShijianduanValue() {
				return shijianduanValue;
			}
			/**
			* 设置： 预约时间段的值
			*/
			public void setShijianduanValue(String shijianduanValue) {
				this.shijianduanValue = shijianduanValue;
			}
			/**
			* 获取： 预约状态的值
			*/
			public String getTeacherOrderYesnoValue() {
				return TeacherOrderYesnoValue;
			}
			/**
			* 设置： 预约状态的值
			*/
			public void setTeacherOrderYesnoValue(String TeacherOrderYesnoValue) {
				this.TeacherOrderYesnoValue = TeacherOrderYesnoValue;
			}






































				//级联表的get和set yonghu

					/**
					* 获取： 学生姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 学生姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 学生手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 学生手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 学生身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 学生身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 学生头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 学生头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}


				//级联表的get和set Teacher

					/**
					* 获取： 心理老师姓名
					*/
					public String getTeacherName() {
						return TeacherName;
					}
					/**
					* 设置： 心理老师姓名
					*/
					public void setTeacherName(String TeacherName) {
						this.TeacherName = TeacherName;
					}

					/**
					* 获取： 心理老师手机号
					*/
					public String getTeacherPhone() {
						return TeacherPhone;
					}
					/**
					* 设置： 心理老师手机号
					*/
					public void setTeacherPhone(String TeacherPhone) {
						this.TeacherPhone = TeacherPhone;
					}

					/**
					* 获取： 心理老师头像
					*/
					public String getTeacherPhoto() {
						return TeacherPhoto;
					}
					/**
					* 设置： 心理老师头像
					*/
					public void setTeacherPhoto(String TeacherPhoto) {
						this.TeacherPhoto = TeacherPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getTeacherEmail() {
						return TeacherEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setTeacherEmail(String TeacherEmail) {
						this.TeacherEmail = TeacherEmail;
					}

					/**
					* 获取： 擅长
					*/
					public String getTeacherShanchang() {
						return TeacherShanchang;
					}
					/**
					* 设置： 擅长
					*/
					public void setTeacherShanchang(String TeacherShanchang) {
						this.TeacherShanchang = TeacherShanchang;
					}

					/**
					* 获取： 履历
					*/
					public String getTeacherLvli() {
						return TeacherLvli;
					}
					/**
					* 设置： 履历
					*/
					public void setTeacherLvli(String TeacherLvli) {
						this.TeacherLvli = TeacherLvli;
					}

					/**
					* 获取： 心理老师详细介绍
					*/
					public String getTeacherContent() {
						return TeacherContent;
					}
					/**
					* 设置： 心理老师详细介绍
					*/
					public void setTeacherContent(String TeacherContent) {
						this.TeacherContent = TeacherContent;
					}




}
