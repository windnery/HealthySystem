package com.entity.view;

import com.entity.TeacherCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;

/**
 * 心理老师收藏
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("Teacher_collection")
public class TeacherCollectionView extends TeacherCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 类型的值
		*/
		private String TeacherCollectionValue;



		//级联表 Student
			/**
			* 学生姓名
			*/
			private String StudentName;
			/**
			* 学生手机号
			*/
			private String StudentPhone;
			/**
			* 学生身份证号
			*/
			private String StudentIdNumber;
			/**
			* 学生头像
			*/
			private String StudentPhoto;
			/**
			* 电子邮箱
			*/
			private String StudentEmail;

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

	public TeacherCollectionView() {

	}

	public TeacherCollectionView(TeacherCollectionEntity TeacherCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, TeacherCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 类型的值
			*/
			public String getTeacherCollectionValue() {
				return TeacherCollectionValue;
			}
			/**
			* 设置： 类型的值
			*/
			public void setTeacherCollectionValue(String TeacherCollectionValue) {
				this.TeacherCollectionValue = TeacherCollectionValue;
			}






































				//级联表的get和set Student

					/**
					* 获取： 学生姓名
					*/
					public String getStudentName() {
						return StudentName;
					}
					/**
					* 设置： 学生姓名
					*/
					public void setStudentName(String StudentName) {
						this.StudentName = StudentName;
					}

					/**
					* 获取： 学生手机号
					*/
					public String getStudentPhone() {
						return StudentPhone;
					}
					/**
					* 设置： 学生手机号
					*/
					public void setStudentPhone(String StudentPhone) {
						this.StudentPhone = StudentPhone;
					}

					/**
					* 获取： 学生身份证号
					*/
					public String getStudentIdNumber() {
						return StudentIdNumber;
					}
					/**
					* 设置： 学生身份证号
					*/
					public void setStudentIdNumber(String StudentIdNumber) {
						this.StudentIdNumber = StudentIdNumber;
					}

					/**
					* 获取： 学生头像
					*/
					public String getStudentPhoto() {
						return StudentPhoto;
					}
					/**
					* 设置： 学生头像
					*/
					public void setStudentPhoto(String StudentPhoto) {
						this.StudentPhoto = StudentPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getStudentEmail() {
						return StudentEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setStudentEmail(String StudentEmail) {
						this.StudentEmail = StudentEmail;
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
