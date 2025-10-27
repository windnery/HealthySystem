package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 心理老师收藏
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("Teacher_collection")
public class TeacherCollectionVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 心理老师
     */

    @TableField(value = "Teacher_id")
    private Integer TeacherId;


    /**
     * 学生
     */

    @TableField(value = "Student_id")
    private Integer StudentId;


    /**
     * 类型
     */

    @TableField(value = "Teacher_collection_types")
    private Integer TeacherCollectionTypes;


    /**
     * 收藏时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：心理老师
	 */
    public Integer getTeacherId() {
        return TeacherId;
    }


    /**
	 * 获取：心理老师
	 */

    public void setTeacherId(Integer TeacherId) {
        this.TeacherId = TeacherId;
    }
    /**
	 * 设置：学生
	 */
    public Integer getStudentId() {
        return StudentId;
    }


    /**
	 * 获取：学生
	 */

    public void setStudentId(Integer StudentId) {
        this.StudentId = StudentId;
    }
    /**
	 * 设置：类型
	 */
    public Integer getTeacherCollectionTypes() {
        return TeacherCollectionTypes;
    }


    /**
	 * 获取：类型
	 */

    public void setTeacherCollectionTypes(Integer TeacherCollectionTypes) {
        this.TeacherCollectionTypes = TeacherCollectionTypes;
    }
    /**
	 * 设置：收藏时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：收藏时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
