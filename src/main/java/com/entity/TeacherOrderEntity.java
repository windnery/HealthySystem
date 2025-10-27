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
 * 心理咨询预约申请
 *
 * @author 
 * @email
 */
@TableName("Teacher_order")
public class TeacherOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public TeacherOrderEntity() {

	}

	public TeacherOrderEntity(T t) {
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
     * 预约流水号
     */
    @TableField(value = "Teacher_order_uuid_number")

    private String TeacherOrderUuidNumber;


    /**
     * 心理老师
     */
    @TableField(value = "Teacher_id")

    private Integer TeacherId;


    /**
     * 学生
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "yuyue_time")

    private Date yuyueTime;


    /**
     * 预约时间段
     */
    @TableField(value = "shijianduan_types")

    private Integer shijianduanTypes;


    /**
     * 预约状态
     */
    @TableField(value = "Teacher_order_yesno_types")

    private Integer TeacherOrderYesnoTypes;


    /**
     * 审核意见
     */
    @TableField(value = "Teacher_order_yesno_text")

    private String TeacherOrderYesnoText;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 设置：预约流水号
	 */
    public String getTeacherOrderUuidNumber() {
        return TeacherOrderUuidNumber;
    }
    /**
	 * 获取：预约流水号
	 */

    public void setTeacherOrderUuidNumber(String TeacherOrderUuidNumber) {
        this.TeacherOrderUuidNumber = TeacherOrderUuidNumber;
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
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：学生
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：预约日期
	 */
    public Date getYuyueTime() {
        return yuyueTime;
    }
    /**
	 * 获取：预约日期
	 */

    public void setYuyueTime(Date yuyueTime) {
        this.yuyueTime = yuyueTime;
    }
    /**
	 * 设置：预约时间段
	 */
    public Integer getShijianduanTypes() {
        return shijianduanTypes;
    }
    /**
	 * 获取：预约时间段
	 */

    public void setShijianduanTypes(Integer shijianduanTypes) {
        this.shijianduanTypes = shijianduanTypes;
    }
    /**
	 * 设置：预约状态
	 */
    public Integer getTeacherOrderYesnoTypes() {
        return TeacherOrderYesnoTypes;
    }
    /**
	 * 获取：预约状态
	 */

    public void setTeacherOrderYesnoTypes(Integer TeacherOrderYesnoTypes) {
        this.TeacherOrderYesnoTypes = TeacherOrderYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getTeacherOrderYesnoText() {
        return TeacherOrderYesnoText;
    }
    /**
	 * 获取：审核意见
	 */

    public void setTeacherOrderYesnoText(String TeacherOrderYesnoText) {
        this.TeacherOrderYesnoText = TeacherOrderYesnoText;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "TeacherOrder{" +
            "id=" + id +
            ", TeacherOrderUuidNumber=" + TeacherOrderUuidNumber +
            ", TeacherId=" + TeacherId +
            ", yonghuId=" + yonghuId +
            ", yuyueTime=" + yuyueTime +
            ", shijianduanTypes=" + shijianduanTypes +
            ", TeacherOrderYesnoTypes=" + TeacherOrderYesnoTypes +
            ", TeacherOrderYesnoText=" + TeacherOrderYesnoText +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
