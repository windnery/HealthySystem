package com.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 心理老师收藏
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class TeacherCollectionModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 心理老师
     */
    private Integer TeacherId;


    /**
     * 学生
     */
    private Integer StudentId;


    /**
     * 类型
     */
    private Integer TeacherCollectionTypes;


    /**
     * 收藏时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 photoShow
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
	 * 获取：心理老师
	 */
    public Integer getTeacherId() {
        return TeacherId;
    }


    /**
	 * 设置：心理老师
	 */
    public void setTeacherId(Integer TeacherId) {
        this.TeacherId = TeacherId;
    }
    /**
	 * 获取：学生
	 */
    public Integer getStudentId() {
        return StudentId;
    }


    /**
	 * 设置：学生
	 */
    public void setStudentId(Integer StudentId) {
        this.StudentId = StudentId;
    }
    /**
	 * 获取：类型
	 */
    public Integer getTeacherCollectionTypes() {
        return TeacherCollectionTypes;
    }


    /**
	 * 设置：类型
	 */
    public void setTeacherCollectionTypes(Integer TeacherCollectionTypes) {
        this.TeacherCollectionTypes = TeacherCollectionTypes;
    }
    /**
	 * 获取：收藏时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：收藏时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
