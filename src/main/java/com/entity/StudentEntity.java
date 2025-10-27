package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 学生
 *
 * @author 
 * @email
 */
@TableName("Student")
public class StudentEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public StudentEntity() {

	}

	public StudentEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 学生姓名
     */
    @TableField(value = "Student_name")

    private String StudentName;


    /**
     * 学生手机号
     */
    @TableField(value = "Student_phone")

    private String StudentPhone;


    /**
     * 学生身份证号
     */
    @TableField(value = "Student_id_number")

    private String StudentIdNumber;


    /**
     * 学生头像
     */
    @TableField(value = "Student_photo")

    private String StudentPhoto;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    @TableField(value = "Student_email")

    private String StudentEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：学生姓名
	 */
    public String getStudentName() {
        return StudentName;
    }
    /**
	 * 获取：学生姓名
	 */

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }
    /**
	 * 设置：学生手机号
	 */
    public String getStudentPhone() {
        return StudentPhone;
    }
    /**
	 * 获取：学生手机号
	 */

    public void setStudentPhone(String StudentPhone) {
        this.StudentPhone = StudentPhone;
    }
    /**
	 * 设置：学生身份证号
	 */
    public String getStudentIdNumber() {
        return StudentIdNumber;
    }
    /**
	 * 获取：学生身份证号
	 */

    public void setStudentIdNumber(String StudentIdNumber) {
        this.StudentIdNumber = StudentIdNumber;
    }
    /**
	 * 设置：学生头像
	 */
    public String getStudentPhoto() {
        return StudentPhoto;
    }
    /**
	 * 获取：学生头像
	 */

    public void setStudentPhoto(String StudentPhoto) {
        this.StudentPhoto = StudentPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getStudentEmail() {
        return StudentEmail;
    }
    /**
	 * 获取：电子邮箱
	 */

    public void setStudentEmail(String StudentEmail) {
        this.StudentEmail = StudentEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", StudentName=" + StudentName +
            ", StudentPhone=" + StudentPhone +
            ", StudentIdNumber=" + StudentIdNumber +
            ", StudentPhoto=" + StudentPhoto +
            ", sexTypes=" + sexTypes +
            ", StudentEmail=" + StudentEmail +
            ", createTime=" + createTime +
        "}";
    }
}
