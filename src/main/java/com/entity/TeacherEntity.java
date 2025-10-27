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
 * 心理老师
 *
 * @author 
 * @email
 */
@TableName("Teacher")
public class TeacherEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public TeacherEntity() {

	}

	public TeacherEntity(T t) {
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
     * 心理老师姓名
     */
    @TableField(value = "Teacher_name")

    private String TeacherName;


    /**
     * 心理老师手机号
     */
    @TableField(value = "Teacher_phone")

    private String TeacherPhone;


    /**
     * 心理老师头像
     */
    @TableField(value = "Teacher_photo")

    private String TeacherPhoto;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    @TableField(value = "Teacher_email")

    private String TeacherEmail;


    /**
     * 擅长
     */
    @TableField(value = "Teacher_shanchang")

    private String TeacherShanchang;


    /**
     * 履历
     */
    @TableField(value = "Teacher_lvli")

    private String TeacherLvli;


    /**
     * 心理老师详细介绍
     */
    @TableField(value = "Teacher_content")

    private String TeacherContent;


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
	 * 设置：心理老师姓名
	 */
    public String getTeacherName() {
        return TeacherName;
    }
    /**
	 * 获取：心理老师姓名
	 */

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }
    /**
	 * 设置：心理老师手机号
	 */
    public String getTeacherPhone() {
        return TeacherPhone;
    }
    /**
	 * 获取：心理老师手机号
	 */

    public void setTeacherPhone(String TeacherPhone) {
        this.TeacherPhone = TeacherPhone;
    }
    /**
	 * 设置：心理老师头像
	 */
    public String getTeacherPhoto() {
        return TeacherPhoto;
    }
    /**
	 * 获取：心理老师头像
	 */

    public void setTeacherPhoto(String TeacherPhoto) {
        this.TeacherPhoto = TeacherPhoto;
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
    public String getTeacherEmail() {
        return TeacherEmail;
    }
    /**
	 * 获取：电子邮箱
	 */

    public void setTeacherEmail(String TeacherEmail) {
        this.TeacherEmail = TeacherEmail;
    }
    /**
	 * 设置：擅长
	 */
    public String getTeacherShanchang() {
        return TeacherShanchang;
    }
    /**
	 * 获取：擅长
	 */

    public void setTeacherShanchang(String TeacherShanchang) {
        this.TeacherShanchang = TeacherShanchang;
    }
    /**
	 * 设置：履历
	 */
    public String getTeacherLvli() {
        return TeacherLvli;
    }
    /**
	 * 获取：履历
	 */

    public void setTeacherLvli(String TeacherLvli) {
        this.TeacherLvli = TeacherLvli;
    }
    /**
	 * 设置：心理老师详细介绍
	 */
    public String getTeacherContent() {
        return TeacherContent;
    }
    /**
	 * 获取：心理老师详细介绍
	 */

    public void setTeacherContent(String TeacherContent) {
        this.TeacherContent = TeacherContent;
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
        return "Teacher{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", TeacherName=" + TeacherName +
            ", TeacherPhone=" + TeacherPhone +
            ", TeacherPhoto=" + TeacherPhoto +
            ", sexTypes=" + sexTypes +
            ", TeacherEmail=" + TeacherEmail +
            ", TeacherShanchang=" + TeacherShanchang +
            ", TeacherLvli=" + TeacherLvli +
            ", TeacherContent=" + TeacherContent +
            ", createTime=" + createTime +
        "}";
    }
}
