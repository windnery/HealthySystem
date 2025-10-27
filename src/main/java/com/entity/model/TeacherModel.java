package com.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 心理老师
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class TeacherModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


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
     * 性别
     */
    private Integer sexTypes;


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


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：心理老师姓名
	 */
    public String getTeacherName() {
        return TeacherName;
    }


    /**
	 * 设置：心理老师姓名
	 */
    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }
    /**
	 * 获取：心理老师手机号
	 */
    public String getTeacherPhone() {
        return TeacherPhone;
    }


    /**
	 * 设置：心理老师手机号
	 */
    public void setTeacherPhone(String TeacherPhone) {
        this.TeacherPhone = TeacherPhone;
    }
    /**
	 * 获取：心理老师头像
	 */
    public String getTeacherPhoto() {
        return TeacherPhoto;
    }


    /**
	 * 设置：心理老师头像
	 */
    public void setTeacherPhoto(String TeacherPhoto) {
        this.TeacherPhoto = TeacherPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getTeacherEmail() {
        return TeacherEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setTeacherEmail(String TeacherEmail) {
        this.TeacherEmail = TeacherEmail;
    }
    /**
	 * 获取：擅长
	 */
    public String getTeacherShanchang() {
        return TeacherShanchang;
    }


    /**
	 * 设置：擅长
	 */
    public void setTeacherShanchang(String TeacherShanchang) {
        this.TeacherShanchang = TeacherShanchang;
    }
    /**
	 * 获取：履历
	 */
    public String getTeacherLvli() {
        return TeacherLvli;
    }


    /**
	 * 设置：履历
	 */
    public void setTeacherLvli(String TeacherLvli) {
        this.TeacherLvli = TeacherLvli;
    }
    /**
	 * 获取：心理老师详细介绍
	 */
    public String getTeacherContent() {
        return TeacherContent;
    }


    /**
	 * 设置：心理老师详细介绍
	 */
    public void setTeacherContent(String TeacherContent) {
        this.TeacherContent = TeacherContent;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
